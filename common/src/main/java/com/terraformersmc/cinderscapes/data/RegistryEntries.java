package com.terraformersmc.cinderscapes.data;

import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.PlacedFeature;

/** Loader-neutral registry sink used by the biome/datapack bootstrap code. */
public interface RegistryEntries {
    <T> void add(RegistryKey<T> key, T value);

    <T> RegistryEntry.Reference<T> ref(RegistryKey<T> key);

    RegistryEntryLookup<PlacedFeature> placedFeatures();

    RegistryEntryLookup<ConfiguredCarver<?>> configuredCarvers();

    RegistryWrapper.WrapperLookup getWrapperLookup();
}
