package com.terraformersmc.cinderscapes.init;

import com.terraformersmc.cinderscapes.placementmodifier.SafelistRangeCeilingPlacementModifier;
import com.terraformersmc.cinderscapes.placementmodifier.SafelistRangeFloorPlacementModifier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class CinderscapesPlacementModifierTypes {
    public static final MapCodec<SafelistRangeCeilingPlacementModifier> COUNT_CEILING = SafelistRangeCeilingPlacementModifier.MODIFIER_CODEC;
    public static final MapCodec<SafelistRangeFloorPlacementModifier> COUNT_FLOOR = SafelistRangeFloorPlacementModifier.MODIFIER_CODEC;

    public static void init() {
        Registry.register(BuiltInRegistries.PLACEMENT_MODIFIER_TYPE, Identifier.fromNamespaceAndPath("cinderscapes", "safelist_range_ceiling"), COUNT_CEILING);
        Registry.register(BuiltInRegistries.PLACEMENT_MODIFIER_TYPE, Identifier.fromNamespaceAndPath("cinderscapes", "safelist_range_floor"), COUNT_FLOOR);
    }
}
