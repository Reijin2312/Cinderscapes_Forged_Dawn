package com.terraformersmc.terraform.shapes.impl.validator;

import com.terraformersmc.terraform.shapes.api.Position;
import com.terraformersmc.terraform.shapes.api.validator.AllMeetValidator;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LevelSimulatedReader;

public class AirValidator extends AllMeetValidator {
    private final LevelReader levelReader;
    private final LevelSimulatedReader simulatedReader;
    public AirValidator(LevelReader world) { levelReader = world; simulatedReader = null; }
    public AirValidator(LevelSimulatedReader world) { levelReader = null; simulatedReader = world; }
    public static AirValidator of(LevelReader world) { return new AirValidator(world); }
    public static AirValidator of(LevelSimulatedReader world) { return new AirValidator(world); }
    @Override public boolean test(Position position) {
        if (levelReader != null) return levelReader.isEmptyBlock(position.toBlockPos());
        return simulatedReader != null && simulatedReader.isStateAtPosition(position.toBlockPos(), state -> state.isAir());
    }
}
