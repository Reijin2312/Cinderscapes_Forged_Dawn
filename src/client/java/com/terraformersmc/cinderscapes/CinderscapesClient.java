package com.terraformersmc.cinderscapes;

import com.terraformersmc.cinderscapes.init.CinderscapesBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@EventBusSubscriber(modid = Cinderscapes.MOD_ID, value = Dist.CLIENT)
public final class CinderscapesClient {
    private CinderscapesClient() {}

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        if (event.getItemStack().is(CinderscapesBlocks.NODZOL.asItem())) {
            event.getToolTip().add(Component.translatable("block.cinderscapes.nodzol.description")
                    .setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)));
        }
    }
}
