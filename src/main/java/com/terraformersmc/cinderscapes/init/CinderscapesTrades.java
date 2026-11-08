package com.terraformersmc.cinderscapes.init;

import com.terraformersmc.cinderscapes.Cinderscapes;
import com.terraformersmc.cinderscapes.loottables.CinderscapesLootPoolAccessor;
import com.terraformersmc.cinderscapes.loottables.CinderscapesLootTableAccessor;
import java.util.List;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.event.LootTableLoadEvent;

public final class CinderscapesTrades {
    private CinderscapesTrades() {}
    public static void init() {}

    public static void onLootTableLoad(LootTableLoadEvent event) {
        if (!BuiltInLootTables.PIGLIN_BARTERING.identifier().equals(event.getName())) return;
        List<LootPool> pools = ((CinderscapesLootTableAccessor) event.getTable()).cinderscapes$getPools();
        if (pools.isEmpty()) {
            Cinderscapes.LOGGER.error("Piglin bartering loot table has no pools");
            return;
        }
        List<LootPoolEntryContainer> additions = List.of(
                entry(CinderscapesItems.ROSE_QUARTZ), entry(CinderscapesItems.SMOKY_QUARTZ),
                entry(CinderscapesItems.SULFUR_QUARTZ));
        pools.set(0, ((CinderscapesLootPoolAccessor) pools.getFirst()).cinderscapes$withEntries(additions));
    }

    private static LootPoolEntryContainer entry(net.minecraft.world.level.ItemLike item) {
        return LootItem.lootTableItem(item).setWeight(20).setQuality(0)
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 12))).build();
    }
}

