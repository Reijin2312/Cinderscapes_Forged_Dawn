package com.terraformersmc.cinderscapes.init;

import com.terraformersmc.cinderscapes.Cinderscapes;
import com.terraformersmc.cinderscapes.loottables.CinderscapesLootPoolAccessor;
import com.terraformersmc.cinderscapes.loottables.CinderscapesLootTableAccessor;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import java.util.List;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.entity.npc.villager.VillagerTrades.ItemListing;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.event.LootTableLoadEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.common.BasicItemListing;

public final class CinderscapesTrades {
    private CinderscapesTrades() {}
    public static void init() {}

    public static void onVillagerTrades(VillagerTradesEvent event) {
        if (event.getType().equals(VillagerProfession.MASON)) addMasonTrades(event.getTrades());
    }

    public static void addMasonTrades(Int2ObjectMap<List<ItemListing>> trades) {
        List<ItemListing> levelFour = trades.get(4);
        levelFour.add(buyQuartz(CinderscapesItems.ROSE_QUARTZ));
        levelFour.add(buyQuartz(CinderscapesItems.SULFUR_QUARTZ));
        levelFour.add(buyQuartz(CinderscapesItems.SMOKY_QUARTZ));

        List<ItemListing> levelFive = trades.get(5);
        levelFive.add(sellBlock(CinderscapesBlocks.ROSE_QUARTZ_PILLAR));
        levelFive.add(sellBlock(CinderscapesBlocks.ROSE_QUARTZ_BLOCK));
        levelFive.add(sellBlock(CinderscapesBlocks.SULFUR_QUARTZ_PILLAR));
        levelFive.add(sellBlock(CinderscapesBlocks.SULFUR_QUARTZ_BLOCK));
        levelFive.add(sellBlock(CinderscapesBlocks.SMOKY_QUARTZ_PILLAR));
        levelFive.add(sellBlock(CinderscapesBlocks.SMOKY_QUARTZ_BLOCK));
    }

    private static ItemListing buyQuartz(net.minecraft.world.level.ItemLike quartz) {
        return new BasicItemListing(new ItemStack(quartz, 12), new ItemStack(Items.EMERALD), 12, 30, 0.05F);
    }

    private static ItemListing sellBlock(net.minecraft.world.level.ItemLike block) {
        return new BasicItemListing(1, new ItemStack(block), 12, 30, 0.05F);
    }

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
