package com.terraformersmc.cinderscapes;

import com.terraformersmc.cinderscapes.biomegen.CinderscapesBiolithGeneration;
import com.terraformersmc.cinderscapes.config.CinderscapesConfig;
import com.terraformersmc.cinderscapes.init.*;
import com.terraformersmc.cinderscapes.init.helpers.CinderscapesRegistry;
import com.terraformersmc.cinderscapes.item.CinderscapesCreativeModeTabs;
import com.terraformersmc.cinderscapes.surface.builders.CinderscapesSurfaceBuilders;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.event.level.AlterGroundEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Cinderscapes.MOD_ID)
public final class Cinderscapes {
    public static final String MOD_ID = "cinderscapes";
    public static final Logger LOGGER = LogManager.getLogger(StringUtils.capitalize(MOD_ID));

    public Cinderscapes(IEventBus modBus) {
        CinderscapesConfig.init();
        modBus.addListener(this::onRegister);
        modBus.addListener(this::onCommonSetup);
        modBus.addListener(CinderscapesCreativeModeTabs::onBuildContents);
        modBus.addListener(CinderscapesSpawnRestrictions::onRegisterSpawnPlacements);
        modBus.addListener(CinderscapesBlockEntityTypes::onAddBlocks);
        NeoForge.EVENT_BUS.addListener(CinderscapesTrades::onLootTableLoad);
        NeoForge.EVENT_BUS.addListener(Cinderscapes::onAlterGround);
    }

    private static void onAlterGround(AlterGroundEvent event) {
        AlterGroundEvent.StateProvider original = event.getStateProvider();
        event.setStateProvider((level, random, pos) -> level.getBlockState(pos).is(Blocks.NETHERRACK)
                ? CinderscapesBlocks.NODZOL.defaultBlockState()
                : original.getState(level, random, pos));
    }

    private void onRegister(RegisterEvent event) {
        CinderscapesRegistry.begin(event);
        try {
            if (event.getRegistryKey().equals(Registries.BLOCK)) CinderscapesBlocks.init();
            else if (event.getRegistryKey().equals(Registries.ITEM)) CinderscapesItems.init();
            else if (event.getRegistryKey().equals(Registries.PLACEMENT_MODIFIER_TYPE)) CinderscapesPlacementModifierTypes.init();
            else if (event.getRegistryKey().equals(Registries.FEATURE)) CinderscapesFeatures.init();
            else if (event.getRegistryKey().equals(Registries.SOUND_EVENT)) CinderscapesSoundEvents.init();
            else if (event.getRegistryKey().equals(Registries.CREATIVE_MODE_TAB)) CinderscapesCreativeModeTabs.init();
        } finally {
            CinderscapesRegistry.end();
        }
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            CinderscapesTrades.init();
            CinderscapesRegistryAliases.init();
            if (ModList.get().isLoaded("biolith")) {
                LOGGER.info("Enabling Cinderscapes's native NeoForge Biolith worldgen module.");
                CinderscapesSurfaceBuilders.init();
                CinderscapesBiolithGeneration.init();
            } else {
                LOGGER.warn("Cinderscapes world generation disabled; Biolith is not present.");
            }
        });
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}

