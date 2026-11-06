package com.terraformersmc.cinderscapes.init;

import com.terraformersmc.cinderscapes.Cinderscapes;
import com.terraformersmc.cinderscapes.loottables.IntegratedEntry;
import com.terraformersmc.cinderscapes.loottables.CinderscapesLootPoolAccessor;
import com.terraformersmc.cinderscapes.init.helpers.CinderscapesRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.entry.LootPoolEntryType;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;

import java.util.List;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class CinderscapesTrades {
    private static final Identifier BARTERING_LOOT_TABLE_ID = new Identifier("minecraft", "gameplay/piglin_bartering");

    public static LootPoolEntryType INTEGRATED;

    public static void register() {
        INTEGRATED = CinderscapesRegistry.register(net.minecraft.registry.RegistryKeys.LOOT_POOL_ENTRY_TYPE,
                Cinderscapes.id("integrated"), new LootPoolEntryType(new IntegratedEntry.Serializer()));
    }

    public static void init() {
        MinecraftForge.EVENT_BUS.addListener(CinderscapesTrades::addVillagerTrades);
        MinecraftForge.EVENT_BUS.addListener(CinderscapesTrades::modifyLootTables);
    }

    private static void addVillagerTrades(VillagerTradesEvent event) {
        if (event.getType() != VillagerProfession.MASON) return;

        List trades = event.getTrades().get(4);
        trades.add((net.minecraft.village.TradeOffers.Factory) (entity, random) -> new TradeOffer(new ItemStack(CinderscapesItems.ROSE_QUARTZ, 12), new ItemStack(Items.EMERALD), 12, 30, 0.05F));
        trades.add((net.minecraft.village.TradeOffers.Factory) (entity, random) -> new TradeOffer(new ItemStack(CinderscapesItems.SULFUR_QUARTZ, 12), new ItemStack(Items.EMERALD), 12, 30, 0.05F));
        trades.add((net.minecraft.village.TradeOffers.Factory) (entity, random) -> new TradeOffer(new ItemStack(CinderscapesItems.SMOKY_QUARTZ, 12), new ItemStack(Items.EMERALD), 12, 30, 0.05F));

        trades = event.getTrades().get(5);
        trades.add((net.minecraft.village.TradeOffers.Factory) (entity, random) -> new TradeOffer(new ItemStack(Items.EMERALD), new ItemStack(CinderscapesBlocks.ROSE_QUARTZ_PILLAR), 12, 30, 0.05F));
        trades.add((net.minecraft.village.TradeOffers.Factory) (entity, random) -> new TradeOffer(new ItemStack(Items.EMERALD), new ItemStack(CinderscapesBlocks.ROSE_QUARTZ_BLOCK), 12, 30, 0.05F));
        trades.add((net.minecraft.village.TradeOffers.Factory) (entity, random) -> new TradeOffer(new ItemStack(Items.EMERALD), new ItemStack(CinderscapesBlocks.SULFUR_QUARTZ_PILLAR), 12, 30, 0.05F));
        trades.add((net.minecraft.village.TradeOffers.Factory) (entity, random) -> new TradeOffer(new ItemStack(Items.EMERALD), new ItemStack(CinderscapesBlocks.SULFUR_QUARTZ_BLOCK), 12, 30, 0.05F));
        trades.add((net.minecraft.village.TradeOffers.Factory) (entity, random) -> new TradeOffer(new ItemStack(Items.EMERALD), new ItemStack(CinderscapesBlocks.SMOKY_QUARTZ_PILLAR), 12, 30, 0.05F));
        trades.add((net.minecraft.village.TradeOffers.Factory) (entity, random) -> new TradeOffer(new ItemStack(Items.EMERALD), new ItemStack(CinderscapesBlocks.SMOKY_QUARTZ_BLOCK), 12, 30, 0.05F));
    }

    private static void modifyLootTables(LootTableLoadEvent event) {
        if (!BARTERING_LOOT_TABLE_ID.equals(event.getName())) return;
        ArrayList<net.minecraft.loot.entry.LootPoolEntry> additions = new ArrayList<>();
        additions.add(IntegratedEntry.builder(CinderscapesItems.ROSE_QUARTZ).weight(20).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(5, 12))).build());
        additions.add(IntegratedEntry.builder(CinderscapesItems.SMOKY_QUARTZ).weight(20).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(5, 12))).build());
        additions.add(IntegratedEntry.builder(CinderscapesItems.SULFUR_QUARTZ).weight(20).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(5, 12))).build());

        try {
            for (Field field : event.getTable().getClass().getDeclaredFields()) {
                if (!List.class.isAssignableFrom(field.getType())) continue;
                field.setAccessible(true);
                List<?> value = (List<?>) field.get(event.getTable());
                if (value == null || value.isEmpty() || !(value.get(0) instanceof net.minecraft.loot.LootPool)) continue;
                @SuppressWarnings("unchecked")
                List<net.minecraft.loot.LootPool> pools = (List<net.minecraft.loot.LootPool>) value;
                pools.set(0, ((CinderscapesLootPoolAccessor) pools.get(0)).cinderscapes$withEntries(additions));
                return;
            }
        } catch (ReflectiveOperationException exception) {
            Cinderscapes.LOGGER.error("Unable to extend the piglin bartering loot table", exception);
        }
        Cinderscapes.LOGGER.error("Unable to locate the piglin bartering loot pool");
    }
}
