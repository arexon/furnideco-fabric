package dev.arexon.furnideco.content.sound;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static dev.arexon.furnideco.FurniDeco.MOD_ID;

public class SoundRegistry {

    public static final Identifier PAINT_SOUND = new Identifier(MOD_ID, "customization.paint");

    public static SoundEvent PAINT_SOUND_EVENT = new SoundEvent(PAINT_SOUND);

    public static void register() {
        Registry.register(Registry.SOUND_EVENT, PAINT_SOUND, PAINT_SOUND_EVENT);
    }
}
