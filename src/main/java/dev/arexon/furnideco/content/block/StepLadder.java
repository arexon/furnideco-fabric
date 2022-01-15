package dev.arexon.furnideco.content.block;

import dev.arexon.furnideco.content.util.FurniDecoMaterials;
import dev.arexon.furnideco.content.util.VoxelShapeUtils;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

@SuppressWarnings("deprecation")
public class StepLadder extends Block {

    private static final VoxelShape MAIN_STEP = VoxelShapes.cuboid(0d, 0d, .125d, 1.d, 1.d, .875d);
    private static final VoxelShape SECOND_STEP = VoxelShapes.cuboid(.1875d, .0d, .0d, .8125d, .5625d, .1875d);
    private static final VoxelShape MAIN_STEP_ROTATED = VoxelShapeUtils.rotateHorizontal(MAIN_STEP, 90);

    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.union(
            MAIN_STEP,
            SECOND_STEP
    );

    protected static final VoxelShape EAST_SHAPE = VoxelShapes.union(
            MAIN_STEP_ROTATED,
            VoxelShapeUtils.rotateHorizontal(SECOND_STEP, 270)
    );

    protected static final VoxelShape SOUTH_SHAPE = VoxelShapes.union(
            MAIN_STEP,
            VoxelShapeUtils.rotateHorizontal(SECOND_STEP, 180)
    );

    protected static final VoxelShape WEST_SHAPE = VoxelShapes.union(
            MAIN_STEP_ROTATED,
            VoxelShapeUtils.rotateHorizontal(SECOND_STEP, 90)
    );


    public StepLadder() {

        super(FabricBlockSettings.of(FurniDecoMaterials.NON_SOLID_MATERIAL).requiresTool().nonOpaque());
        setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        Direction dir = state.get(Properties.HORIZONTAL_FACING);
        return switch (dir) {
            case NORTH, SOUTH -> MAIN_STEP;
            case EAST, WEST -> MAIN_STEP_ROTATED;
            default -> VoxelShapes.fullCube();
        };
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        Direction dir = state.get(Properties.HORIZONTAL_FACING);
        return switch (dir) {
            case NORTH -> NORTH_SHAPE;
            case EAST -> EAST_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            case WEST -> WEST_SHAPE;
            default -> VoxelShapes.fullCube();
        };
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite());
    }

}
