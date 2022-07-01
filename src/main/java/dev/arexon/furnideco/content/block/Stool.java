package dev.arexon.furnideco.content.block;

import dev.arexon.furnideco.content.entity.SitEntity;
import dev.arexon.furnideco.content.item.ItemRegistry;
import dev.arexon.furnideco.content.particle.ParticleRegistry;
import dev.arexon.furnideco.content.sound.SoundRegistry;
import dev.arexon.furnideco.content.util.FurniDecoMaterials;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import static dev.arexon.furnideco.FurniDeco.SIT_ENTITY_TYPE;
import static dev.arexon.furnideco.content.entity.SitEntity.OCCUPIED;

@SuppressWarnings("deprecation")
public class Stool extends Block {

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

                world.playSound(null, pos, SoundRegistry.PAINT_SOUND_EVENT, SoundCategory.BLOCKS, 1f, 1f);

                if (world instanceof ServerWorld serverWorld) {
                    spawnPaintParticles(serverWorld, pos);
                }

            } else {

                if (!OCCUPIED.containsKey(pos) && world.getBlockState(pos.up()).isAir() && !player.hasVehicle()) {
                    SitEntity sit = SIT_ENTITY_TYPE.create(world);

                    OCCUPIED.put(pos, sit);

                    assert sit != null;
                    sit.updatePosition(pos.getX() + .5d, pos.getY() + .45d, pos.getZ() + .5d);

                    world.spawnEntity(sit);
                    player.startRiding(sit);
                    return ActionResult.SUCCESS;
                }
            }
        }
        return ActionResult.SUCCESS;
    }

    private void spawnPaintParticles(ServerWorld world, BlockPos pos) {
        world.spawnParticles(
                    ParticleRegistry.PAINT_PARTICLE,
                    pos.getX() + 0.5D,
                    pos.getY() + 0.5D,
                    pos.getZ() + 0.5D,
                    20,
                    0.3D,
                    0.25D,
                    0.3D,
                    0D
            );
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {

        if (OCCUPIED.containsKey(pos)) {
          OCCUPIED.get(pos).remove(Entity.RemovalReason.DISCARDED);
          OCCUPIED.remove(pos);
        }

        this.spawnBreakParticles(world, player, pos, state);
        world.emitGameEvent(player, GameEvent.BLOCK_DESTROY, pos);
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
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        super.onLandedUpon(world, state, pos, entity, fallDistance * 0.5f);
    }

    @Override
    public void onEntityLand(BlockView world, Entity entity) {
        if (entity.bypassesLandingEffects()) {
            super.onEntityLand(world, entity);
        } else {
            this.bounceEntity(entity);
        }
    }

    private void bounceEntity(Entity entity) {
        Vec3d vec3d = entity.getVelocity();
        if (vec3d.y < 0.0) {
            double d = entity instanceof LivingEntity ? 1.0 : 0.8;
            entity.setVelocity(vec3d.x, -vec3d.y * (double)0.66f * d, vec3d.z);
        }
    }

}
