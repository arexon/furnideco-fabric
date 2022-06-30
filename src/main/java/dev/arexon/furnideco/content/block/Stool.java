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
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import static dev.arexon.furnideco.FurniDeco.SIT_ENTITY_TYPE;
import static dev.arexon.furnideco.content.entity.SitEntity.OCCUPIED;

@SuppressWarnings("deprecation")
public class Stool extends Block {
    // TODO: Fix stool dismounting when broken.

    public static final IntProperty COLOR = IntProperty.of("color", 0, 15);
    protected static final VoxelShape SHAPE = VoxelShapes.cuboid(0.0625d, 0.d, 0.0625d, 0.9375d, 0.6875d, 0.9375d);

    public Stool() {

        super(FabricBlockSettings.of(FurniDecoMaterials.NON_SOLID_MATERIAL).requiresTool().nonOpaque());
        setDefaultState(getStateManager().getDefaultState().with(COLOR, 0));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        if(!world.isClient){
            if (player.getMainHandStack().getItem() == ItemRegistry.PAINT_BRUSH) {

                int color = state.get(COLOR);
                if (color >= 15) {
                    color = 0;
                } else {
                    color++;
                }

                world.setBlockState(pos, state.with(COLOR, color));
            } else {

                if (!OCCUPIED.containsKey(pos) && world.getBlockState(pos.up()).isAir()) {
                  SitEntity sit = SIT_ENTITY_TYPE.create(world);

                  OCCUPIED.put(pos, player.getPos());
                  sit.updatePosition(pos.getX() + .5d, pos.getY() + .45d, pos.getZ() + .5d);

                  world.spawnEntity(sit);
                  player.startRiding(sit);
                  return ActionResult.SUCCESS;
                }
            }
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

}
