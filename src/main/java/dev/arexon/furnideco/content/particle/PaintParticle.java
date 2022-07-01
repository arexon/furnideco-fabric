package dev.arexon.furnideco.content.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

public class PaintParticle extends SpriteBillboardParticle {

    private boolean isShrinking = false;

    protected PaintParticle(ClientWorld level, double xCoord, double yCoord, double zCoord,
                            SpriteProvider spriteSet, double xd, double yd, double zd) {
        super(level, xCoord, yCoord, zCoord, xd, yd, zd);

        this.velocityMultiplier = (this.random.nextInt(4)+4)/10F;
        this.x = xd;
        this.y = yd;
        this.z = zd;
        this.scale *= 0.75F;
        this.maxAge = 20;
        this.setSpriteForAge(spriteSet);

        this.velocityX = (this.random.nextInt(4)-2)/10F;
        this.velocityY = 0.2F;
        this.velocityZ = (this.random.nextInt(4)-2)/10F;

        this.colorRed = this.random.nextInt(9)/10f;
        this.colorGreen = this.random.nextInt(9)/10f;
        this.colorBlue = this.random.nextInt(9)/10f;
    }

    @Override
    public void tick() {
        super.tick();
        shrinkOut();
        rotate();
    }

    private void shrinkOut() {
        int n = this.random.nextInt(10);
        n += 10;

        if (age > n) {
            isShrinking = true;
        }

        if (isShrinking) {
            this.scale *= 0.8F;
        }
    }

    private void rotate() {
        this.prevAngle = this.angle;
        this.angle += 0.1F;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider sprites;

        public Factory(SpriteProvider spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(DefaultParticleType particleType, ClientWorld level, double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new PaintParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }
}