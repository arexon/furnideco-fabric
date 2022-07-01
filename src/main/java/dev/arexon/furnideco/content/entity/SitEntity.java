package dev.arexon.furnideco.content.entity;

import dev.arexon.furnideco.FurniDeco;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.Arm;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.HashMap;

public class SitEntity extends Entity {
    // Thanks to https://github.com/bl4ckscor3/Sit, this was not a pain to make.

    public static final HashMap<BlockPos, SitEntity> OCCUPIED = new HashMap<>();

    public SitEntity(EntityType<? extends SitEntity> type, World world) {

        super(type, world);
    }


    @Override
    public Vec3d updatePassengerForDismount(LivingEntity passenger) {
        if (passenger instanceof PlayerEntity) {

            OCCUPIED.remove(getBlockPos());
        }

        remove(RemovalReason.DISCARDED);

        Vec3d vec3d = SitEntity.getPassengerDismountOffset(this.getWidth(), passenger.getWidth(), this.getYaw() + (passenger.getMainArm() == Arm.RIGHT ? 90.0f : -90.0f));
        Vec3d vec3d2 = this.locateSafeDismountingPos(vec3d, passenger);
        if (vec3d2 != null) {
            return vec3d2;
        }
        Vec3d vec3d3 = SitEntity.getPassengerDismountOffset(this.getWidth(), passenger.getWidth(), this.getYaw() + (passenger.getMainArm() == Arm.LEFT ? 90.0f : -90.0f));
        Vec3d vec3d4 = this.locateSafeDismountingPos(vec3d3, passenger);
        if (vec3d4 != null) {
            return vec3d4;
        }
        return this.getPos();

    }

    @Override
    public void remove(RemovalReason reason) {

        super.remove(reason);
        OCCUPIED.remove(getBlockPos());
    }

    @Override
    protected void initDataTracker() {}

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {}

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {}

    @Override
    public Packet<?> createSpawnPacket() {

        return new EntitySpawnS2CPacket(this);
    }

    private Vec3d locateSafeDismountingPos(Vec3d offset, LivingEntity passenger) {
        double d = this.getX() + offset.x;
        double e = this.getBoundingBox().minY;
        double f = this.getZ() + offset.z;
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        block0: for (EntityPose entityPose : passenger.getPoses()) {
            mutable.set(d, e, f);
            double g = this.getBoundingBox().maxY + 0.75;
            do {
                Vec3d vec3d;
                double h = this.world.getDismountHeight(mutable);
                if ((double)mutable.getY() + h > g) continue block0;
                if (Dismounting.canDismountInBlock(h) && Dismounting.canPlaceEntityAt(this.world, passenger, (passenger.getBoundingBox(entityPose)).offset(vec3d = new Vec3d(d, (double)mutable.getY() + h, f)))) {
                    passenger.setPose(entityPose);
                    return vec3d;
                }
                mutable.move(Direction.UP);
            } while ((double)mutable.getY() < g);
        }
        return null;
    }
}