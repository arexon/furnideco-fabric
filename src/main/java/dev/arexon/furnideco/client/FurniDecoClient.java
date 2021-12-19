package dev.arexon.furnideco.client;

import dev.arexon.furnideco.content.block.BlockRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class FurniDecoClient implements ClientModInitializer {

    @Override
    public void onInitializeClient(){

        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.ACACIA_STOOL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.BIRCH_STOOL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.CRIMSON_STOOL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.DARK_OAK_STOOL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.JUNGLE_STOOL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.OAK_STOOL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.SPRUCE_STOOL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.WARPED_STOOL, RenderLayer.getCutout());
    }
}
