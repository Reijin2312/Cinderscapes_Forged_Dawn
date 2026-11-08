package com.terraformersmc.cinderscapes.init;

import net.minecraft.world.level.block.state.properties.WoodType;

public final class CinderscapesWoodTypes {
    public static final WoodType SCORCHED = copy("cinderscapes:scorched", CinderscapesBlockSetTypes.SCORCHED);
    public static final WoodType UMBRAL = copy("cinderscapes:umbral", CinderscapesBlockSetTypes.UMBRAL);

    private static WoodType copy(String name, net.minecraft.world.level.block.state.properties.BlockSetType setType) {
        WoodType source = WoodType.CRIMSON;
        return WoodType.register(new WoodType(name, setType, source.soundType(), source.hangingSignSoundType(),
                source.fenceGateClose(), source.fenceGateOpen()));
    }
}
