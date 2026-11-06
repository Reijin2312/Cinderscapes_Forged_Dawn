package com.terraformersmc.cinderscapes;

import com.terraformersmc.cinderscapes.init.CinderscapesBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.minecraft.world.level.block.Block;

@SuppressWarnings("unused")
@EventBusSubscriber(modid = Cinderscapes.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class CinderscapesClient {
	private CinderscapesClient() { }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {

		for (Block block : new Block[] {
                // Ashy Shoals

                // Blackstone Shales

                // Luminous Grove
                CinderscapesBlocks.GHASTLY_ECTOPLASM,
                CinderscapesBlocks.UMBRAL_WART_BLOCK,

                // Quartz Cavern
                CinderscapesBlocks.CRYSTALLINE_QUARTZ,
                CinderscapesBlocks.CRYSTALLINE_ROSE_QUARTZ,
                CinderscapesBlocks.CRYSTALLINE_SMOKY_QUARTZ,
                CinderscapesBlocks.CRYSTALLINE_SULFUR_QUARTZ
		}) ItemBlockRenderTypes.setRenderLayer(block, RenderType.translucent());

		for (Block block : new Block[] {
                // Ashy Shoals
                CinderscapesBlocks.BRAMBLE_BERRY_BUSH,
                CinderscapesBlocks.POTTED_PYRACINTH,
                CinderscapesBlocks.POTTED_SCORCHED_SHRUB,
                CinderscapesBlocks.POTTED_SCORCHED_TENDRILS,
                CinderscapesBlocks.PYRACINTH,
                CinderscapesBlocks.SCORCHED_SHRUB,
                CinderscapesBlocks.SCORCHED_SPROUTS,
                CinderscapesBlocks.SCORCHED_TENDRILS,

                // Blackstone Shales

                // Luminous Grove
                CinderscapesBlocks.LUMINOUS_POD,
                CinderscapesBlocks.PHOTOFERN,
                CinderscapesBlocks.POTTED_LUMINOUS_POD,
                CinderscapesBlocks.POTTED_PHOTOFERN,
                CinderscapesBlocks.POTTED_TWILIGHT_TENDRILS,
                CinderscapesBlocks.POTTED_UMBRAL_FUNGUS,
                CinderscapesBlocks.TALL_PHOTOFERN,
                CinderscapesBlocks.TWILIGHT_FESCUES,
                CinderscapesBlocks.TWILIGHT_TENDRILS,
                CinderscapesBlocks.UMBRAL_FUNGUS,
                CinderscapesBlocks.UMBRAL_DOOR,

                // Quartz Cavern
                CinderscapesBlocks.CRYSTINIUM,
                CinderscapesBlocks.POTTED_CRYSTINIUM,
                CinderscapesBlocks.POLYPITE_QUARTZ,
                CinderscapesBlocks.POLYPITE_ROSE_QUARTZ,
                CinderscapesBlocks.POLYPITE_SMOKY_QUARTZ,
                CinderscapesBlocks.POLYPITE_SULFUR_QUARTZ,
                CinderscapesBlocks.POTTED_POLYPITE_QUARTZ,
                CinderscapesBlocks.POTTED_POLYPITE_ROSE_QUARTZ,
                CinderscapesBlocks.POTTED_POLYPITE_SMOKY_QUARTZ,
                CinderscapesBlocks.POTTED_POLYPITE_SULFUR_QUARTZ
		}) ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutout());

		Sheets.addWoodType(CinderscapesBlocks.SCORCHED_WOOD_TYPE);
		Sheets.addWoodType(CinderscapesBlocks.UMBRAL_WOOD_TYPE);

		NeoForge.EVENT_BUS.addListener(CinderscapesClient::onTooltip);
		});
    }

	private static void onTooltip(ItemTooltipEvent event) {
		if (event.getItemStack().is(CinderscapesBlocks.NODZOL.asItem())) {
			event.getToolTip().add(Component.translatable("block.cinderscapes.nodzol.description").setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)));
		}
	}
}
