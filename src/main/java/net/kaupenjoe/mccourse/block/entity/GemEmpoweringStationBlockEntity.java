package net.kaupenjoe.mccourse.block.entity;

import java.util.Optional;

import net.kaupenjoe.mccourse.block.custom.GemEmpoweringStationBlock;
import net.kaupenjoe.mccourse.item.ModItems;
import net.kaupenjoe.mccourse.recipe.GemEmpoweringRecipe;
import net.kaupenjoe.mccourse.screen.GemEmpoweringScreenHandler;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.base.SimpleEnergyStorage;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
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
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;

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

	protected PropertyDelegate propertyDelegate;
	private int progress = 0;
	private int maxProgress = 72;

	public GemEmpoweringStationBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.GEM_EMPOWERING_STATION_BE, pos, state);
		propertyDelegate = new PropertyDelegate() {
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

	public final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(64000, 200, 200) {
		@Override
		protected void onFinalCommit() {
			markDirty();
			World world = getWorld();
			if (world != null) {
				world.updateListeners(pos, getCachedState(), getCachedState(), 3);
			}
		}
	};

	public final SingleVariantStorage<FluidVariant> fluidStorage = new SingleVariantStorage<>() {
		@Override
		protected FluidVariant getBlankVariant() {
			return FluidVariant.blank();
		}

		@Override
		protected long getCapacity(FluidVariant variant) {
			return (FluidConstants.BUCKET / 81) * 64; // 1 Bucket = 81000 Droplets = 1000mB || *64 ==> 64,000mB = 64 Buckets
		}

		@Override
		protected void onFinalCommit() {
			markDirty();
			World world = getWorld();
			if (world != null) {
				world.updateListeners(pos, getCachedState(), getCachedState(), 3);
			}
		}
	};

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
		nbt.putLong(("gem_empowering_station.energy"), energyStorage.amount);
		nbt.put("gem_empowering_station.variant", fluidStorage.variant.toNbt());
		nbt.putLong("gem_empowering_station.fluid_amount", fluidStorage.amount);
	}

	@Override
	public void readNbt(NbtCompound nbt) {
		Inventories.readNbt(nbt, inventory);
		progress = nbt.getInt("gem_empowering_station.progress");
		energyStorage.amount = nbt.getLong("gem_empowering_station.energy");
		fluidStorage.variant = FluidVariant.fromNbt((NbtCompound) nbt.get("gem_empowering_station.variant"));
		fluidStorage.amount = nbt.getLong("gem_empowering_station.fluid_amount");
		super.readNbt(nbt);
	}

	public void tick(World world, BlockPos pos, BlockState state) {
		fillUpOnEnergy(); // until we have machines/other mods that give us Energy
		fillUpOnFluid();
		if (canInsertIntoOutputSlot() && hasRecipe()) {
			increaseCraftingProgress();
			extractEnergy();
			markDirty(world, pos, state);
			if (hasCraftingFinished()) {
				craftItem();
				extractFluid();
				resetProgress();
			}
		} else {
			resetProgress();
		}
	}

	private void extractFluid() {
		try (Transaction transaction = Transaction.openOuter()) {
			this.fluidStorage.extract(FluidVariant.of(Fluids.WATER), 500, transaction);
			transaction.commit();
		}
	}

	private void fillUpOnFluid() {
		if (hasFluidSourceItemInFluidSlot(FLUID_ITEM_SLOT)) {
			transferItemFluidToTank(FLUID_ITEM_SLOT);
		}
	}

	@SuppressWarnings("SameParameterValue")
	private void transferItemFluidToTank(int fluidItemSlot) {
		try (Transaction transaction = Transaction.openOuter()) {
			this.fluidStorage.insert(FluidVariant.of(Fluids.WATER),
				(FluidConstants.BUCKET / 81), transaction);
			transaction.commit();

			this.setStack(fluidItemSlot, new ItemStack(Items.BUCKET));
		}
	}

	@SuppressWarnings("SameParameterValue")
	private boolean hasFluidSourceItemInFluidSlot(int fluidItemSlot) {
		return this.getStack(fluidItemSlot).getItem() == Items.WATER_BUCKET;
	}

	private void extractEnergy() {
		try (Transaction transaction = Transaction.openOuter()) {
			this.energyStorage.extract(32L, transaction);
			transaction.commit();
		}
	}

	private void fillUpOnEnergy() {
		if (hasEnergyItemInEnergySlot(ENERGY_ITEM_SLOT)) {
			try (Transaction transaction = Transaction.openOuter()) {
				this.energyStorage.insert(64, transaction);
				transaction.commit();
			}
		}
	}

	@SuppressWarnings("SameParameterValue")
	private boolean hasEnergyItemInEnergySlot(int energyItemSlot) {
		return this.getStack(energyItemSlot).getItem() == ModItems.CAULIFLOWER;
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
			&& canInsertItemIntoOutputSlot(output) && hasEnoughEnergyToCraft() && hasEnoughFluidToCraft();
	}

	private boolean hasEnoughFluidToCraft() {
		return this.fluidStorage.amount >= 500; // mB amount!
	}

	private boolean hasEnoughEnergyToCraft() {
		return this.energyStorage.amount >= 32L * this.maxProgress;
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

	@Nullable
	@Override
	public Packet<ClientPlayPacketListener> toUpdatePacket() {
		return BlockEntityUpdateS2CPacket.create(this);
	}

	@Override
	public NbtCompound toInitialChunkDataNbt() {
		return createNbt();
	}
}
