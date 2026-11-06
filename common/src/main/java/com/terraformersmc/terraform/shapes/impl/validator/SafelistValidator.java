package com.terraformersmc.terraform.shapes.impl.validator;

import com.terraformersmc.terraform.shapes.api.Position;
import com.terraformersmc.terraform.shapes.api.validator.AllMeetValidator;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import java.util.Arrays;
import java.util.List;

public class SafelistValidator extends AllMeetValidator {
    private final List<BlockState> safeStates;
    private final LevelSimulatedReader world;
    public SafelistValidator(LevelSimulatedReader world, List<BlockState> safeStates) { this.world = world; this.safeStates = safeStates; }
    public SafelistValidator(LevelSimulatedReader world, BlockState... safeStates) { this(world, Arrays.asList(safeStates)); }
    public static SafelistValidator of(LevelSimulatedReader world, List<BlockState> safeStates) { return new SafelistValidator(world, safeStates); }
    public static SafelistValidator of(LevelSimulatedReader world, BlockState... safeStates) { return new SafelistValidator(world, safeStates); }
    @Override public boolean test(Position position) { return world.isStateAtPosition(position.toBlockPos(), state -> state.isAir() || safeStates.contains(state)); }
}
