package dev.arexon.furnideco;

import net.fabricmc.api.ModInitializer;

import dev.arexon.furnideco.content.block.BlockRegistry;
import dev.arexon.furnideco.content.item.ItemRegistry;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class FurniDeco implements ModInitializer {

	public static final String MOD_ID = "furnideco";
	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
			new Identifier(MOD_ID, MOD_ID),
			() -> new ItemStack(ItemRegistry.PAINT_BRUSH));

	@Override
	public void onInitialize() {

		BlockRegistry.register();
		ItemRegistry.register();
	}
}
