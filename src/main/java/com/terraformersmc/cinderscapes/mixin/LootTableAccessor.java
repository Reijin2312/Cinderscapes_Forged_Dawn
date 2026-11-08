package com.terraformersmc.cinderscapes.mixin;

import com.terraformersmc.cinderscapes.loottables.CinderscapesLootTableAccessor;
import java.util.List;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LootTable.class)
public abstract class LootTableAccessor implements CinderscapesLootTableAccessor {
    @Shadow @Final private List<LootPool> pools;
    @Override public List<LootPool> cinderscapes$getPools() { return pools; }
}

