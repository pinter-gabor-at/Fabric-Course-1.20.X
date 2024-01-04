package net.kaupenjoe.mccourse.item.custom;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MetalDetectorItem extends Item {
	public MetalDetectorItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		PlayerEntity player = context.getPlayer();
		if (!context.getWorld().isClient()) {
			BlockPos positionClicked = context.getBlockPos();
			boolean foundBlock = false;
			for (int i = 0; i <= positionClicked.getY() + 64; i++) {
				BlockState blockState = context.getWorld().getBlockState(positionClicked.down(i));
				Block block = blockState.getBlock();
				if (isValuableBlock(blockState) && player != null) {
					outputValuableCoordinates(positionClicked.down(i), player, block);
					foundBlock = true;
					break;
				}
			}
			if (!foundBlock && player != null) {
				player.sendMessage(Text.translatable("item.mccourse.metal_detector.no_valuables"));
			}
		}
		if (player != null) {
			context.getStack().damage(1, player,
				playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
		}
		return ActionResult.SUCCESS;
	}

	private void outputValuableCoordinates(BlockPos position, PlayerEntity player, Block block) {
		player.sendMessage(Text.literal("Valuable Found: " + block.getName().getString() + " at " +
			"(" + position.getX() + ", " + position.getY() + ", " + position.getZ() + ")"));
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		if (Screen.hasShiftDown()) {
			tooltip.add(Text.translatable("tooltip.mccourse.metal_detector.tooltip.shift"));
		} else {
			tooltip.add(Text.translatable("tooltip.mccourse.metal_detector.tooltip"));
		}
	}

	private boolean isValuableBlock(BlockState blockState) {
		return blockState.getBlock() == Blocks.IRON_ORE || blockState.getBlock() == Blocks.GOLD_ORE ||
			blockState.getBlock() == Blocks.DIAMOND_ORE || blockState.getBlock() == Blocks.REDSTONE_ORE;
	}
}
