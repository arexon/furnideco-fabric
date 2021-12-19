package dev.arexon.furnideco;

import dev.arexon.furnideco.content.block.BlockRegistry;
import dev.arexon.furnideco.content.entity.SitEntity;
import dev.arexon.furnideco.content.item.ItemRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FurniDeco implements ModInitializer {

	public static final String MOD_ID = "furnideco";
	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
			new Identifier(MOD_ID, MOD_ID),
			() -> new ItemStack(ItemRegistry.PAINT_BRUSH));
	public static final EntityType<SitEntity> SIT_ENTITY_TYPE = Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier(MOD_ID, "sitable"),
			FabricEntityTypeBuilder.<SitEntity>create(SpawnGroup.MISC, SitEntity::new).dimensions(EntityDimensions.fixed(0.001F, 0.001F)).build()
	);

	@Override
	public void onInitialize() {

		BlockRegistry.register();
		ItemRegistry.register();
	}
}
