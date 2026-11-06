package com.terraformersmc.cinderscapes;

import com.terraformersmc.cinderscapes.config.CinderscapesConfig;
import com.terraformersmc.cinderscapes.init.*;
import com.terraformersmc.cinderscapes.init.helpers.CinderscapesRegistry;
import com.terraformersmc.cinderscapes.item.CinderscapesItemGroups;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

@Mod(Cinderscapes.MOD_ID)
public class Cinderscapes {
	public static final String MOD_ID = "cinderscapes";
	public static final Logger LOGGER = LogManager.getLogger(StringUtils.capitalize(MOD_ID));

	private static Boolean initialized = false;
	private static final ArrayList<Runnable> runnables = new ArrayList<>(1);

	public Cinderscapes(IEventBus modBus) {
		CinderscapesConfig.init();
		modBus.addListener(this::onRegister);
		modBus.addListener(this::onCommonSetup);
		modBus.addListener(CinderscapesItemGroups::onBuildContents);
		modBus.addListener(CinderscapesSpawnRestrictions::onRegisterSpawnPlacements);
		NeoForge.EVENT_BUS.addListener(CinderscapesNativeEvents::onBlockToolModification);
		NeoForge.EVENT_BUS.addListener(CinderscapesNativeEvents::onAlterGround);
	}

	private void onRegister(RegisterEvent event) {
		CinderscapesRegistry.begin(event);
		try {
			if (event.getRegistryKey().equals(Registries.BLOCK)) CinderscapesBlocks.init();
			else if (event.getRegistryKey().equals(Registries.ITEM)) CinderscapesItems.init();
			else if (event.getRegistryKey().equals(Registries.PLACEMENT_MODIFIER_TYPE)) CinderscapesPlacementModifierTypes.init();
			else if (event.getRegistryKey().equals(Registries.FEATURE)) CinderscapesFeatures.init();
			else if (event.getRegistryKey().equals(Registries.SOUND_EVENT)) CinderscapesSoundEvents.init();
			else if (event.getRegistryKey().equals(Registries.CREATIVE_MODE_TAB)) CinderscapesItemGroups.init();
		} finally {
			CinderscapesRegistry.end();
		}
	}

	private void onCommonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			CinderscapesTrades.init();
			CinderscapesWorldgen.initialize();
			initialized = true;
			runnables.forEach(Runnable::run);
			runnables.clear();
		});
	}

	public static ResourceLocation id(String path) { return ResourceLocation.fromNamespaceAndPath(MOD_ID, path); }

	public static void callbackWhenInitialized(Runnable callback) {
		if (initialized) {
			callback.run();
		} else {
			runnables.add(callback);
		}
	}
}
