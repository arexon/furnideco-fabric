package dev.arexon.furnideco.content.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class Stool extends Block {

    public static final Material STOOL_MATERIAL = new Material.Builder(MapColor.OAK_TAN).notSolid().build();
    protected static final VoxelShape SHAPE = Block.createCuboidShape(1.d, .0d, 1.d, 15.d, 11.d, 15.d);
    public static final IntProperty COLOR = IntProperty.of("color", 0, 15);

    public Stool() {

        super(FabricBlockSettings.of(STOOL_MATERIAL, DyeColor.ORANGE).requiresTool().nonOpaque());
    }

    public Stool(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(COLOR, 0));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        int color = state.get(COLOR);

        if (color >= 15) {
            color = 0;
        } else {
            color++;
        }

        world.setBlockState(pos, state.with(COLOR, color));
        return ActionResult.SUCCESS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(COLOR);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).getMaterial().isSolid();
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        return direction == Direction.DOWN && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState()
                : super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
    }

}
