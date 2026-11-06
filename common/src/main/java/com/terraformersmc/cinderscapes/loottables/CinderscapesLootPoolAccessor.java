package com.terraformersmc.cinderscapes.loottables;

import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.LootPoolEntry;

import java.util.List;

public interface CinderscapesLootPoolAccessor {
    LootPool cinderscapes$withEntries(List<LootPoolEntry> newEntries);
}
