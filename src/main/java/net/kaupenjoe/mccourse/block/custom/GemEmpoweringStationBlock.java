package net.kaupenjoe.mccourse.block.custom;

import com.mojang.serialization.MapCodec;
import net.kaupenjoe.mccourse.block.entity.GemEmpoweringStationBlockEntity;
import net.kaupenjoe.mccourse.block.entity.ModBlockEntities;

import net.minecraft.block.BrewingStandBlock;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class GemEmpoweringStationBlock extends BlockWithEntity implements BlockEntityProvider {
	public static final MapCodec<GemEmpoweringStationBlock> CODEC = createCodec(GemEmpoweringStationBlock::new);
	public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
	private static final VoxelShape SHAPE =
		Block.createCuboidShape(0, 0, 0, 16, 12, 16);

	public GemEmpoweringStationBlock(Settings settings) {
		super(settings);
	}

	@Override
	protected MapCodec<GemEmpoweringStationBlock> getCodec() {
		return CODEC;
	}

	// Overriding the methods is an expected usage and is not deprecated in any way.
	@SuppressWarnings("deprecation")
	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return SHAPE;
	}

	@Nullable
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}


	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new GemEmpoweringStationBlockEntity(pos, state);
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	// Overriding the methods is an expected usage and is not deprecated in any way.
	@SuppressWarnings("deprecation")
	@Override
	public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
		if (state.getBlock() != newState.getBlock()) {
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof GemEmpoweringStationBlockEntity) {
				ItemScatterer.spawn(world, pos, (GemEmpoweringStationBlockEntity) blockEntity);
				world.updateComparators(pos, this);
			}
			super.onStateReplaced(state, world, pos, newState, moved);
		}
	}

	// Overriding the methods is an expected usage and is not deprecated in any way.
	@SuppressWarnings("deprecation")
	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (!world.isClient) {
			NamedScreenHandlerFactory screenHandlerFactory = ((GemEmpoweringStationBlockEntity) world.getBlockEntity(pos));
			if (screenHandlerFactory != null) {
				player.openHandledScreen(screenHandlerFactory);
			}
		}

		return ActionResult.SUCCESS;
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
		if (type == ModBlockEntities.GEM_EMPOWERING_STATION_BE) {
			return (world1, pos, state1, blockEntity) ->
				((GemEmpoweringStationBlockEntity) blockEntity).tick(world1, pos, state1);
		}
		return null;
	}
}
