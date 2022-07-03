package dev.arexon.furnideco.content.block;

import dev.arexon.furnideco.content.block.enums.TableShape;
import dev.arexon.furnideco.content.util.FurniDecoMaterials;
import dev.arexon.furnideco.content.util.VoxelShapeUtils;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class Table extends Block {

    public static final EnumProperty<TableShape> SHAPE = EnumProperty.of("shape", TableShape.class);

    public static final VoxelShape NW_SHAPE = VoxelShapes.cuboid(0.125d, 0d, 0.125d, 0.25d, 0.8125d, .25d);
    public static final VoxelShape SW_SHAPE = VoxelShapeUtils.rotateHorizontal(NW_SHAPE, 90);
    public static final VoxelShape SE_SHAPE = VoxelShapeUtils.rotateHorizontal(NW_SHAPE, 180);
    public static final VoxelShape NE_SHAPE = VoxelShapeUtils.rotateHorizontal(NW_SHAPE, 270);

    public static final VoxelShape TOP_SHAPE = VoxelShapes.cuboid(0d, 0.8125d, 0d, 1d, 1d, 1d);

    public Table() {

        super(FabricBlockSettings.of(FurniDecoMaterials.NON_SOLID_MATERIAL).requiresTool().nonOpaque());
        setDefaultState(getStateManager().getDefaultState().with(SHAPE, TableShape.ALL));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {

        switch(state.get(SHAPE)) {
            case NORTH_END -> {
                return VoxelShapes.union(NW_SHAPE, NE_SHAPE, TOP_SHAPE);
            } case EAST_END -> {
                return VoxelShapes.union(NE_SHAPE, SE_SHAPE, TOP_SHAPE);
            } case SOUTH_END -> {
                return VoxelShapes.union(SE_SHAPE, SW_SHAPE, TOP_SHAPE);
            } case WEST_END -> {
                return VoxelShapes.union(SW_SHAPE, NW_SHAPE, TOP_SHAPE);
            } case NE_CORNER -> {
                return VoxelShapes.union(NE_SHAPE, TOP_SHAPE);
            } case SE_CORNER -> {
                return VoxelShapes.union(SE_SHAPE, TOP_SHAPE);
            } case SW_CORNER -> {
                return VoxelShapes.union(SW_SHAPE, TOP_SHAPE);
            } case NW_CORNER -> {
                return VoxelShapes.union(NW_SHAPE, TOP_SHAPE);
            } case NONE -> {
                return TOP_SHAPE;
            } default -> {
                return VoxelShapes.union(TOP_SHAPE, NW_SHAPE, NE_SHAPE, SE_SHAPE, SW_SHAPE);
            }
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {

        stateManager.add(SHAPE);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {

        if(!world.isClient()){
            updateShape(state, world, pos);
        }

        return state;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {

        updateShape(state, world, pos);
    }

    private void updateShape(BlockState state, WorldAccess world, BlockPos pos) {

        if(world.isClient()) {
            return;
        }

        Boolean north = false;
        Boolean east = false;
        Boolean south = false;
        Boolean west = false;

        if (world.getBlockState(pos.north()).getBlock() instanceof Table) {
            north = true;
        } if (world.getBlockState(pos.east()).getBlock() instanceof Table) {
            east = true;
        } if (world.getBlockState(pos.south()).getBlock() instanceof Table) {
            south = true;
        } if (world.getBlockState(pos.west()).getBlock() instanceof Table) {
            west = true;
        }

        if(north && east && south && west) {
            world.setBlockState(pos, state.with(SHAPE, TableShape.NONE), 3);
        } else if (north && south) {
            world.setBlockState(pos, state.with(SHAPE, TableShape.NONE), 3);
        } else if (east && west) {
            world.setBlockState(pos, state.with(SHAPE, TableShape.NONE), 3);
        } else if (north && east) {
            world.setBlockState(pos, state.with(SHAPE, TableShape.SW_CORNER), 3);
        } else if (east && south) {
            world.setBlockState(pos, state.with(SHAPE, TableShape.NW_CORNER), 3);
        } else if (south && west) {
            world.setBlockState(pos, state.with(SHAPE, TableShape.NE_CORNER), 3);
        } else if (west && north) {
            world.setBlockState(pos, state.with(SHAPE, TableShape.SE_CORNER), 3);
        } else if (north) {
            world.setBlockState(pos, state.with(SHAPE, TableShape.SOUTH_END), 3);
        } else if (east) {
            world.setBlockState(pos, state.with(SHAPE, TableShape.WEST_END), 3);
        } else if (south) {
            world.setBlockState(pos, state.with(SHAPE, TableShape.NORTH_END), 3);
        } else if (west) {
            world.setBlockState(pos, state.with(SHAPE, TableShape.EAST_END), 3);
        } else {
            world.setBlockState(pos, state.with(SHAPE, TableShape.ALL), 3);
        }
    }
}
