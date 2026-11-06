package com.terraformersmc.cinderscapes;

import com.terraformersmc.cinderscapes.init.CinderscapesBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public final class CinderscapesClient {
    public CinderscapesClient() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        MinecraftForge.EVENT_BUS.addListener(this::tooltip);
    }

    private void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            TexturedRenderLayers.addWoodType(CinderscapesBlocks.SCORCHED_WOOD_TYPE);
            TexturedRenderLayers.addWoodType(CinderscapesBlocks.UMBRAL_WOOD_TYPE);
            add(RenderLayer.getTranslucent(), CinderscapesBlocks.GHASTLY_ECTOPLASM,
                    CinderscapesBlocks.UMBRAL_WART_BLOCK, CinderscapesBlocks.CRYSTALLINE_QUARTZ,
                    CinderscapesBlocks.CRYSTALLINE_ROSE_QUARTZ, CinderscapesBlocks.CRYSTALLINE_SMOKY_QUARTZ,
                    CinderscapesBlocks.CRYSTALLINE_SULFUR_QUARTZ);
            add(RenderLayer.getCutout(), CinderscapesBlocks.BRAMBLE_BERRY_BUSH, CinderscapesBlocks.POTTED_PYRACINTH,
                    CinderscapesBlocks.POTTED_SCORCHED_SHRUB, CinderscapesBlocks.POTTED_SCORCHED_TENDRILS,
                    CinderscapesBlocks.PYRACINTH, CinderscapesBlocks.SCORCHED_SHRUB, CinderscapesBlocks.SCORCHED_SPROUTS,
                    CinderscapesBlocks.SCORCHED_TENDRILS, CinderscapesBlocks.LUMINOUS_POD, CinderscapesBlocks.PHOTOFERN,
                    CinderscapesBlocks.POTTED_LUMINOUS_POD, CinderscapesBlocks.POTTED_PHOTOFERN,
                    CinderscapesBlocks.POTTED_TWILIGHT_TENDRILS, CinderscapesBlocks.POTTED_UMBRAL_FUNGUS,
                    CinderscapesBlocks.TALL_PHOTOFERN, CinderscapesBlocks.TWILIGHT_FESCUES,
                    CinderscapesBlocks.TWILIGHT_TENDRILS, CinderscapesBlocks.UMBRAL_FUNGUS,
                    CinderscapesBlocks.UMBRAL_DOOR, CinderscapesBlocks.CRYSTINIUM, CinderscapesBlocks.POTTED_CRYSTINIUM,
                    CinderscapesBlocks.POLYPITE_QUARTZ, CinderscapesBlocks.POLYPITE_ROSE_QUARTZ,
                    CinderscapesBlocks.POLYPITE_SMOKY_QUARTZ, CinderscapesBlocks.POLYPITE_SULFUR_QUARTZ,
                    CinderscapesBlocks.POTTED_POLYPITE_QUARTZ, CinderscapesBlocks.POTTED_POLYPITE_ROSE_QUARTZ,
                    CinderscapesBlocks.POTTED_POLYPITE_SMOKY_QUARTZ, CinderscapesBlocks.POTTED_POLYPITE_SULFUR_QUARTZ);
        });
    }

    private void tooltip(ItemTooltipEvent event) {
        if (event.getItemStack().isOf(CinderscapesBlocks.NODZOL.asItem())) {
            event.getToolTip().add(Text.translatable("block.cinderscapes.nodzol.description")
                    .setStyle(Style.EMPTY.withColor(Formatting.GRAY)));
        }
    }

    private static void add(RenderLayer layer, Block... blocks) {
        for (Block block : blocks) RenderLayers.setRenderLayer(block, layer);
    }
}
