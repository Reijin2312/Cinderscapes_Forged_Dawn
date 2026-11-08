package com.terraformersmc.cinderscapes.init;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Zoglin;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

public final class CinderscapesSpawnRestrictions {
    private CinderscapesSpawnRestrictions() {}

    public static void onRegisterSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(EntityType.ZOGLIN, Zoglin::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
    }
}

