package com.terraformersmc.cinderscapes.init;

import com.terraformersmc.cinderscapes.Cinderscapes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;

public class CinderscapesArmorTrimMaterials {
    public static final ResourceKey<TrimMaterial> ROSE_QUARTZ = createRegistryKey("rose_quartz");
    public static final ResourceKey<TrimMaterial> SMOKY_QUARTZ = createRegistryKey("smoky_quartz");
    public static final ResourceKey<TrimMaterial> SULFUR_QUARTZ = createRegistryKey("sulfur_quartz");

    public static void bootstrap(BootstrapContext<TrimMaterial> registerable) {
        TrimMaterials.register(registerable, ROSE_QUARTZ, CinderscapesItems.ROSE_QUARTZ, Style.EMPTY.withColor(0xE77391), 0.40666f);
        TrimMaterials.register(registerable, SMOKY_QUARTZ, CinderscapesItems.SMOKY_QUARTZ, Style.EMPTY.withColor(0x5a4b46), 0.30666f);
        TrimMaterials.register(registerable, SULFUR_QUARTZ, CinderscapesItems.SULFUR_QUARTZ, Style.EMPTY.withColor(0xbaa938), 0.60666f);
    }

    private static ResourceKey<TrimMaterial> createRegistryKey(String id) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, ResourceLocation.fromNamespaceAndPath(Cinderscapes.MOD_ID, id));
    }
}
