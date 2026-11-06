package com.terraformersmc.cinderscapes.loottables;

import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import java.util.List;

public interface CinderscapesLootPoolAccessor {
    LootPool cinderscapes$withEntries(List<LootPoolEntryContainer> additions);
}
