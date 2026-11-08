package com.terraformersmc.cinderscapes.init;

import net.minecraft.world.level.block.state.properties.BlockSetType;

public final class CinderscapesBlockSetTypes {
    public static final BlockSetType SCORCHED = copy("cinderscapes:scorched");
    public static final BlockSetType UMBRAL = copy("cinderscapes:umbral");

    private static BlockSetType copy(String name) {
        BlockSetType source = BlockSetType.CRIMSON;
        return BlockSetType.register(new BlockSetType(name, source.canOpenByHand(), source.canOpenByWindCharge(),
                source.canButtonBeActivatedByArrows(), source.pressurePlateSensitivity(), source.soundType(),
                source.doorClose(), source.doorOpen(), source.trapdoorClose(), source.trapdoorOpen(),
                source.pressurePlateClickOff(), source.pressurePlateClickOn(), source.buttonClickOff(), source.buttonClickOn()));
    }
}

