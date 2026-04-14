package net.cosmicapiary.vivid_spirit;

import com.klikli_dev.modonomicon.book.page.BookRecipePage;
import com.klikli_dev.modonomicon.client.render.page.PageRendererRegistry;
import com.sammy.malum.common.recipe.SpiritFocusingRecipe;
import com.sammy.malum.common.recipe.SpiritInfusionRecipe;
import net.cosmicapiary.vivid_spirit.custom.malum_compat.BookSpiritFocusingPageRenderer;
import net.cosmicapiary.vivid_spirit.custom.malum_compat.BookSpiritInfusionPageRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.EntityRenderers;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.util.Identifier;

public class VividClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FluidRenderHandlerRegistry.INSTANCE.register(VividSpirit.STILL_SPIRIT, VividSpirit.FLOWING_SPIRIT, new SimpleFluidRenderHandler(
                new Identifier("vivid_spirit:block/spirit_still"),
                new Identifier("vivid_spirit:block/spirit_flow"),
                0x0000ffff
        ));
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getSolid(), VividSpirit.STILL_SPIRIT, VividSpirit.FLOWING_SPIRIT);
        BlockRenderLayerMap.INSTANCE.putBlock(VividSpirit.BLACK_ICE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(VividSpirit.SAPPHIRE_ICE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(VividSpirit.SMALL_RUBY_BUD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VividSpirit.LARGE_RUBY_BUD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VividSpirit.RUBY_CLUSTER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VividSpirit.SMALL_SILVER_BUD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VividSpirit.LARGE_SILVER_BUD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VividSpirit.SILVER_CLUSTER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VividSpirit.SMALL_ALLURITE_BUD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VividSpirit.MEDIUM_ALLURITE_BUD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VividSpirit.LARGE_ALLURITE_BUD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VividSpirit.ALLURITE_CLUSTER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VividSpirit.LUMIERE_CLUSTER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VividSpirit.SMALL_LUMIERE_BUD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VividSpirit.MEDIUM_LUMIERE_BUD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VividSpirit.LARGE_LUMIERE_BUD, RenderLayer.getCutout());

        EntityRendererRegistry.register(VividSpirit.BIOME_EYE, (context) -> new FlyingItemEntityRenderer<>(context, 1.0F, true));

        PageRendererRegistry.registerPageRenderer(new Identifier("vivid_spirit:malum/spirit_infusion"), p -> new BookSpiritInfusionPageRenderer((BookRecipePage<SpiritInfusionRecipe>) p));
        PageRendererRegistry.registerPageRenderer(new Identifier("vivid_spirit:malum/spirit_focusing"), p -> new BookSpiritFocusingPageRenderer((BookRecipePage<SpiritFocusingRecipe>) p));

    }


}
