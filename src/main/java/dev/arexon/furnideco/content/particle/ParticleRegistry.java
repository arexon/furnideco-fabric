package dev.arexon.furnideco.content.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static dev.arexon.furnideco.FurniDeco.MOD_ID;

public class ParticleRegistry {

    public static final DefaultParticleType PAINT_PARTICLE = FabricParticleTypes.simple();

    public static void register() {
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(MOD_ID, "paint"), PAINT_PARTICLE);
    }


}
