package com.terraformersmc.cinderscapes;

import com.terraformersmc.cinderscapes.config.CinderscapesConfig;
import com.terraformersmc.cinderscapes.init.*;
import com.terraformersmc.cinderscapes.item.CinderscapesItemGroups;
import com.terraformersmc.cinderscapes.init.helpers.CinderscapesRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.ZoglinEntity;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.util.Identifier;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

@Mod(Cinderscapes.NAMESPACE)
public class Cinderscapes {
    public static final String NAMESPACE = "cinderscapes";
    public static final Logger LOGGER = LogManager.getLogger(StringUtils.capitalize(NAMESPACE));

    private static boolean initialized;
    private static final ArrayList<Runnable> callbacks = new ArrayList<>(1);

    public Cinderscapes() {
        var bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::register);
        bus.addListener(this::commonSetup);
        bus.addListener(CinderscapesItemGroups::onCreativeTabPopulate);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> CinderscapesClient::new);
    }

    private void register(RegisterEvent event) {
        CinderscapesRegistry.begin(event);
        try {
            if (event.getRegistryKey() == ForgeRegistries.Keys.BLOCKS) {
                CinderscapesConfig.init();
                CinderscapesBlocks.init();
            } else if (event.getRegistryKey() == ForgeRegistries.Keys.ITEMS) {
                CinderscapesItems.init();
            } else if (event.getRegistryKey() == ForgeRegistries.Keys.FEATURES) {
                CinderscapesFeatures.init();
            } else if (event.getRegistryKey() == RegistryKeys.PLACEMENT_MODIFIER_TYPE) {
                CinderscapesPlacementModifierTypes.init();
            } else if (event.getRegistryKey() == ForgeRegistries.Keys.SOUND_EVENTS) {
                CinderscapesSoundEvents.init();
            } else if (event.getRegistryKey() == RegistryKeys.ITEM_GROUP) {
                CinderscapesItemGroups.init();
            } else if (event.getRegistryKey() == RegistryKeys.LOOT_POOL_ENTRY_TYPE) {
                CinderscapesTrades.register();
            }
        } finally {
            CinderscapesRegistry.end();
        }
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            try {
                SpawnRestriction.register(EntityType.ZOGLIN, SpawnRestriction.Location.ON_GROUND,
                        Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ZoglinEntity::canMobSpawn);
                SpawnRestriction.register(EntityType.ZOMBIFIED_PIGLIN, SpawnRestriction.Location.ON_GROUND,
                        Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ZombifiedPiglinEntity::canMobSpawn);
            } catch (IllegalStateException ignored) {
            }
            CinderscapesConfiguredFeatures.init();
            CinderscapesPlacedFeatures.init();
            CinderscapesBiomes.init();
            CinderscapesTrades.init();
            new CinderscapesWorldgen().initialize();
            initialized = true;
            callbacks.forEach(Runnable::run);
            callbacks.clear();
        });
    }

    public static void callbackWhenInitialized(Runnable callback) {
        if (initialized) callback.run();
        else callbacks.add(callback);
    }

    public static Identifier id(String path) {
        return new Identifier(NAMESPACE, path);
    }
}
