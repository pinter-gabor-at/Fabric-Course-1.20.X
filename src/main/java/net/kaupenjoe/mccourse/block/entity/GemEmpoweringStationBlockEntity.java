package net.kaupenjoe.mccourse.block.entity;

import java.util.Optional;

import net.kaupenjoe.mccourse.block.custom.GemEmpoweringStationBlock;
import net.kaupenjoe.mccourse.recipe.GemEmpoweringRecipe;
import net.kaupenjoe.mccourse.screen.GemEmpoweringScreenHandler;
import org.jetbrains.annotations.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;

public class GemEmpoweringStationBlockEntity extends BlockEntity
	implements ExtendedScreenHandlerFactory, ImplementedInventory {
	private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);

	@SuppressWarnings("unused")
	private static final int INPUT_SLOT = 0;
	@SuppressWarnings("unused")
	private static final int FLUID_ITEM_SLOT = 1;
	@SuppressWarnings("unused")
	private static final int OUTPUT_SLOT = 2;
	@SuppressWarnings("unused")
	private static final int ENERGY_ITEM_SLOT = 3;

	protected final PropertyDelegate propertyDelegate;
	private int progress = 0;
	private int maxProgress = 72;

	public GemEmpoweringStationBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.GEM_EMPOWERING_STATION_BE, pos, state);
		this.propertyDelegate = new PropertyDelegate() {
			@Override
			public int get(int index) {
				return switch (index) {
					case 0 -> progress;
					case 1 -> maxProgress;
					default -> 0;
				};
			}

			@Override
			public void set(int index, int value) {
				switch (index) {
					case 0:
						progress = value;
					case 1:
						maxProgress = value;
				}
			}

			@Override
			public int size() {
				return 2;
			}
		};
	}

	@Override
	public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side) {
		if (side == null) {
			return false;
		}
		World world = getWorld();
		if (world == null) {
			return false;
		}
		Direction localDir = world.getBlockState(pos).get(GemEmpoweringStationBlock.FACING);
		if (side == Direction.DOWN) {
			return false;
		}
		if (side == Direction.UP) {
			return slot == INPUT_SLOT;
		}
		return switch (localDir) {
			default -> // case NORTH ->
				side.getOpposite() == Direction.NORTH && slot == INPUT_SLOT ||
					side.getOpposite() == Direction.WEST && slot == INPUT_SLOT;
			case EAST -> side.rotateYClockwise() == Direction.NORTH && slot == INPUT_SLOT ||
				side.rotateYClockwise() == Direction.WEST && slot == INPUT_SLOT;
			case SOUTH -> side == Direction.NORTH && slot == INPUT_SLOT ||
				side == Direction.WEST && slot == INPUT_SLOT;
			case WEST -> side.rotateYCounterclockwise() == Direction.NORTH && slot == INPUT_SLOT ||
				side.rotateYCounterclockwise() == Direction.WEST && slot == INPUT_SLOT;
		};
	}

	@Override
	public boolean canExtract(int slot, ItemStack stack, Direction side) {
		if (side == null) {
			return false;
		}
		World world = getWorld();
		if (world == null) {
			return false;
		}
		Direction localDir = world.getBlockState(this.pos).get(GemEmpoweringStationBlock.FACING);
		if (side == Direction.UP) {
			return false;
		}
		// Down extract 2
		if (side == Direction.DOWN) {
			return slot == OUTPUT_SLOT;
		}
		// Bottom extract 2
		// Right extract 2
		return switch (localDir) {
			default -> side.getOpposite() == Direction.SOUTH && slot == OUTPUT_SLOT ||
				side.getOpposite() == Direction.EAST && slot == OUTPUT_SLOT;
			case EAST -> side.rotateYClockwise() == Direction.SOUTH && slot == OUTPUT_SLOT ||
				side.rotateYClockwise() == Direction.EAST && slot == OUTPUT_SLOT;
			case SOUTH -> side == Direction.SOUTH && slot == OUTPUT_SLOT ||
				side == Direction.EAST && slot == OUTPUT_SLOT;
			case WEST -> side.rotateYCounterclockwise() == Direction.SOUTH && slot == OUTPUT_SLOT ||
				side.rotateYCounterclockwise() == Direction.EAST && slot == OUTPUT_SLOT;
		};
	}

	@Override
	public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
		buf.writeBlockPos(this.pos);
	}

	@Override
	public Text getDisplayName() {
		return Text.translatable("block.mccourse.gem_empowering_station");
	}

	@Nullable
	@Override
	public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
		return new GemEmpoweringScreenHandler(syncId, playerInventory, this, propertyDelegate);
	}

	@Override
	public DefaultedList<ItemStack> getItems() {
		return this.inventory;
	}

	@Override
	protected void writeNbt(NbtCompound nbt) {
		super.writeNbt(nbt);
		Inventories.writeNbt(nbt, inventory);
		nbt.putInt("gem_empowering_station.progress", progress);
	}

	@Override
	public void readNbt(NbtCompound nbt) {
		Inventories.readNbt(nbt, inventory);
		progress = nbt.getInt("gem_empowering_station.progress");
		super.readNbt(nbt);
	}

	public void tick(World world, BlockPos pos, BlockState state) {
		if (canInsertIntoOutputSlot() && hasRecipe()) {
			increaseCraftingProgress();
			markDirty(world, pos, state);
			if (hasCraftingFinished()) {
				craftItem();
				resetProgress();
			}
		} else {
			resetProgress();
		}
	}

	private void craftItem() {
		Optional<RecipeEntry<GemEmpoweringRecipe>> recipe = getCurrentRecipe();
		if (recipe.isEmpty()) {
			return;
		}
		this.removeStack(INPUT_SLOT, 1);
		ItemStack resultStack = recipe.get().value().getResult(null);
		this.setStack(OUTPUT_SLOT, new ItemStack(resultStack.getItem(),
			this.getStack(OUTPUT_SLOT).getCount() + resultStack.getCount()));
	}

	private void resetProgress() {
		progress = 0;
	}

	private boolean hasCraftingFinished() {
		return progress >= maxProgress;
	}

	private void increaseCraftingProgress() {
		progress++;
	}

	private boolean hasRecipe() {
		Optional<RecipeEntry<GemEmpoweringRecipe>> recipe = getCurrentRecipe();
		if (recipe.isEmpty()) {
			return false;
		}
		ItemStack output = recipe.get().value().getResult(null);
		return canInsertAmountIntoOutputSlot(output.getCount())
			&& canInsertItemIntoOutputSlot(output);
	}

	private boolean canInsertItemIntoOutputSlot(ItemStack output) {
		return this.getStack(OUTPUT_SLOT).isEmpty() || getStack(OUTPUT_SLOT).getItem() == output.getItem();
	}

	private boolean canInsertAmountIntoOutputSlot(int count) {
		return this.getStack(OUTPUT_SLOT).getMaxCount() >= getStack(OUTPUT_SLOT).getCount() + count;
	}

	private Optional<RecipeEntry<GemEmpoweringRecipe>> getCurrentRecipe() {
		SimpleInventory inventory = new SimpleInventory(size());
		for (int i = 0; i < size(); i++) {
			inventory.setStack(i, getStack(i));
		}
		World world = getWorld();
		return world != null ?
			world.getRecipeManager().getFirstMatch(GemEmpoweringRecipe.Type.INSTANCE, inventory, world) :
			Optional.empty();
	}

	private boolean canInsertIntoOutputSlot() {
		return getStack(OUTPUT_SLOT).isEmpty() ||
			getStack(OUTPUT_SLOT).getCount() < getStack(OUTPUT_SLOT).getMaxCount();
	}
}
