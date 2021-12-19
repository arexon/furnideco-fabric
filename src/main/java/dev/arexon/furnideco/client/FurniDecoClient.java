package dev.arexon.furnideco.client;

import dev.arexon.furnideco.FurniDeco;
import dev.arexon.furnideco.content.block.BlockRegistry;
import dev.arexon.furnideco.content.entity.SitEntity;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

@SuppressWarnings("deprecation")
@Environment(EnvType.CLIENT)
public class FurniDecoClient implements ClientModInitializer {

    @Override
    public void onInitializeClient(){

        EntityRendererRegistry.INSTANCE.register(FurniDeco.SIT_ENTITY_TYPE, EmptyRenderer::new);
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.ACACIA_STOOL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.BIRCH_STOOL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.CRIMSON_STOOL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.DARK_OAK_STOOL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.JUNGLE_STOOL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.OAK_STOOL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.SPRUCE_STOOL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.WARPED_STOOL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.STEP_LADDER, RenderLayer.getCutout());

    }

    private static class EmptyRenderer extends EntityRenderer<SitEntity>
    {
        protected EmptyRenderer(EntityRendererFactory.Context ctx)
        {
            super(ctx);
        }

        @Override
        public boolean shouldRender(SitEntity entity, Frustum frustum, double d, double e, double f)
        {
            return false;
        }

        @Override
        public Identifier getTexture(SitEntity entity)
        {
            return null;
        }
    }
}
