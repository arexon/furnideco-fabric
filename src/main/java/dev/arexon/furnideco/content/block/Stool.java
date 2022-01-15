package dev.arexon.furnideco.content.block;

import dev.arexon.furnideco.FurniDeco;
import dev.arexon.furnideco.content.entity.SitEntity;
import dev.arexon.furnideco.content.item.ItemRegistry;
import dev.arexon.furnideco.content.util.FurniDecoMaterials;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

@SuppressWarnings("deprecation")
public class Stool extends Block {
    // TODO: Fix stool dismounting when broken.

    protected static final VoxelShape SHAPE = VoxelShapes.cuboid(1.d/16, .0d/16, 1.d/16, 15.d/16, 11.d/16, 15.d/16);
    public static final IntProperty COLOR = IntProperty.of("color", 0, 15);

    public Stool() {

        super(FabricBlockSettings.of(FurniDecoMaterials.NON_SOLID_MATERIAL).requiresTool().nonOpaque());
        setDefaultState(getStateManager().getDefaultState().with(COLOR, 0));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        if (player.getMainHandStack().getItem() == ItemRegistry.PAINT_BRUSH) {

            int color = state.get(COLOR);
            if (color >= 15) {
                color = 0;
            } else {
                color++;
            }

            world.setBlockState(pos, state.with(COLOR, color));
        } else {
            Vec3d position = new Vec3d(pos.getX()+ .5d, pos.getY()+ .45d, pos.getZ()+ .5d);
            SitEntity sit = FurniDeco.SIT_ENTITY_TYPE.create(world);


            SitEntity.OCCUPIED.put(position, player.getBlockPos());
            assert sit != null;
            sit.updatePosition(position.getX(), position.getY(), position.getZ());

            world.spawnEntity(sit);
            player.startRiding(sit);
        }
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
