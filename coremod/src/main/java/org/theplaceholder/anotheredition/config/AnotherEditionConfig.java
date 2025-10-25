package org.theplaceholder.anotheredition.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;
import org.theplaceholder.anotheredition.AnotherEdition;

import java.util.*;

@Config(name = AnotherEdition.MOD_ID)
public class AnotherEditionConfig implements ConfigData {
    private final List<String> netherPortalFrameBlocks = new ArrayList<>(List.of(new String[]{"minecraft:obsidian", "minecraft:crying_obsidian"}));
    private final Map<String, Object> defaultGameRules = new HashMap<>(Map.of("announceAdvancements", false, "reducedDebugInfo", true));
    private final String modpackVersion = "0.1.0";
    private final List<String> disabledKeybinds = new ArrayList<>(List.of(new String[]{"key.advancements"}));

    private transient Set<Block> cachedNetherPortalFrameBlocks = null;
    public Set<Block> getNetherPortalFrameBlocks() {
        if (this.cachedNetherPortalFrameBlocks == null) {
            Set<Block> blocks = new HashSet<>();
            for (String allowedFrameBlock : this.netherPortalFrameBlocks) {
                blocks.add(Registries.BLOCK.get(Identifier.of(allowedFrameBlock)));
            }
            this.cachedNetherPortalFrameBlocks = blocks;
        }
        return this.cachedNetherPortalFrameBlocks;
    }

    public GameRules.Type<?> getDefaultGameRule(String name, GameRules.Type<?> vanillaDefaultGameRule) {
        Object defaultGameRule = this.defaultGameRules.getOrDefault(name, null);
        GameRules.Type<?> result = vanillaDefaultGameRule;

        if (defaultGameRule instanceof Boolean b) result = GameRules.BooleanRule.create(b);
        else if (defaultGameRule instanceof Integer i) result = GameRules.IntRule.create(i);

        return result;
    }

    public String getModpackVersion() {
        return this.modpackVersion;
    }

    public List<String> getDisabledKeybinds() {
        return this.disabledKeybinds;
    }

    public static AnotherEditionConfig register() {
        return AutoConfig.register(AnotherEditionConfig.class, JanksonConfigSerializer::new).getConfig();
    }
}
