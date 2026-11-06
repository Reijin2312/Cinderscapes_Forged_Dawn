package com.terraformersmc.cinderscapes.mixin;

import com.google.common.collect.Lists;
import com.terraformersmc.cinderscapes.loottables.CinderscapesLootPoolAccessor;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.provider.number.LootNumberProvider;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(LootPool.class)
public class LootPoolMixin implements CinderscapesLootPoolAccessor {
    @Invoker("<init>")
    private static LootPool cinderscapes$create(LootPoolEntry[] entries, LootCondition[] conditions,
                                                LootFunction[] functions, LootNumberProvider rolls,
                                                LootNumberProvider bonusRolls, String name) {
        throw new AssertionError();
    }

    @Shadow @Final private LootPoolEntry[] entries;
    @Shadow @Final private LootCondition[] conditions;
    @Shadow @Final private LootFunction[] functions;
    @Shadow @Final private LootNumberProvider rolls;
    @Shadow @Final private LootNumberProvider bonusRolls;
    @Shadow @Final private String name;

    @Override
    public LootPool cinderscapes$withEntries(List<LootPoolEntry> newEntries) {
        List<LootPoolEntry> merged = Lists.newArrayList(entries);
        merged.addAll(newEntries);
        return cinderscapes$create(merged.toArray(new LootPoolEntry[0]), conditions, functions, rolls, bonusRolls, name);
    }
}
