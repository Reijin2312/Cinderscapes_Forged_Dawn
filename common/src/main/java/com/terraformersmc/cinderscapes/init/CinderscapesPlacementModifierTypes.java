package com.terraformersmc.cinderscapes.init;

import com.terraformersmc.cinderscapes.placementmodifier.SafelistRangeCeilingPlacementModifier;
import com.terraformersmc.cinderscapes.placementmodifier.SafelistRangeFloorPlacementModifier;
import com.terraformersmc.cinderscapes.init.helpers.CinderscapesRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

public class CinderscapesPlacementModifierTypes {
    public static final PlacementModifierType<SafelistRangeCeilingPlacementModifier> COUNT_CEILING = () -> SafelistRangeCeilingPlacementModifier.UNIFORM_CODEC;
    public static final PlacementModifierType<SafelistRangeFloorPlacementModifier> COUNT_FLOOR  = () -> SafelistRangeFloorPlacementModifier.UNIFORM_CODEC;

    public static void init() {
        CinderscapesRegistry.register(RegistryKeys.PLACEMENT_MODIFIER_TYPE, new Identifier("cinderscapes", "safelist_range_ceiling"), COUNT_CEILING);
        CinderscapesRegistry.register(RegistryKeys.PLACEMENT_MODIFIER_TYPE, new Identifier("cinderscapes", "safelist_range_floor"), COUNT_FLOOR);
    }
}
