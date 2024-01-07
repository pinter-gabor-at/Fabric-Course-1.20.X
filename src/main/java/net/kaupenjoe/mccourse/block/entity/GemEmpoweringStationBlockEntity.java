package net.kaupenjoe.mccourse.block.entity;

import net.kaupenjoe.mccourse.item.ModItems;
import net.kaupenjoe.mccourse.screen.GemEmpoweringScreenHandler;
import org.jetbrains.annotations.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
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
		final ItemStack outStack = getStack(OUTPUT_SLOT);
		if (outStack.isEmpty() || outStack.getItem() == ModItems.PINK_GARNET) {
			removeStack(INPUT_SLOT, 1);
			setStack(OUTPUT_SLOT, new ItemStack(ModItems.PINK_GARNET,
				outStack.getCount() + 1));
		}
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
		return getStack(INPUT_SLOT).getItem() == ModItems.RAW_PINK_GARNET;
	}

	private boolean canInsertIntoOutputSlot() {
		return getStack(OUTPUT_SLOT).isEmpty() ||
			getStack(OUTPUT_SLOT).getCount() < getStack(OUTPUT_SLOT).getMaxCount();
	}
}
