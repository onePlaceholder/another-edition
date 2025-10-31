package org.theplaceholder.anotheredition.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.world.GameRules;
import org.theplaceholder.anotheredition.AnotherEdition;

import java.util.*;

@Config(name = AnotherEdition.MOD_ID)
public class AnotherEditionConfig implements ConfigData {
    private List<String> netherPortalFrameBlocks = new ArrayList<>(List.of(new String[]{"minecraft:obsidian", "minecraft:crying_obsidian"}));
    private Map<String, Object> defaultGameRules = new HashMap<>(Map.of("announceAdvancements", false, "reducedDebugInfo", true));
    private String modpackVersion = "0.1.0";
    private List<String> disabledKeybinds = new ArrayList<>(List.of(new String[]{"key.advancements"}));
    private int[] loadingColor = new int[]{255, 255, 255};
    private List<String> disabledResources = new ArrayList<>();
    private Map<String, Map<String, String>> replacedBlocks = new HashMap<>();

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
        return AutoConfig.register(AnotherEditionConfig.class, GsonConfigSerializer::new).getConfig();
    }

    public int getLoadingColor() {
        return ColorHelper.Argb.getArgb(255, this.loadingColor[0], this.loadingColor[1], this.loadingColor[2]);
    }

    public List<String> getDisabledResources() {
        return this.disabledResources;
    }

    public Map<String, Map<String, String>> getReplacedBlocks() {
        return this.replacedBlocks;
    }
}
