package dev.arexon.furnideco;

import net.fabricmc.api.ModInitializer;

import dev.arexon.furnideco.content.block.BlockRegistry;
import dev.arexon.furnideco.content.item.ItemRegistry;

public class FurniDeco implements ModInitializer {

	public static final String MOD_ID = "furnideco";

	@Override
	public void onInitialize() {

		ItemRegistry.register();
		BlockRegistry.register();
	}
}
