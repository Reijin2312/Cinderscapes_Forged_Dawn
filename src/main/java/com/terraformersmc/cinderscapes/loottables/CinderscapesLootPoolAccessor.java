package com.terraformersmc.cinderscapes.loottables;

import java.util.List;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;

public interface CinderscapesLootPoolAccessor {
    LootPool cinderscapes$withEntries(List<LootPoolEntryContainer> additions);
}
