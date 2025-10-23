package org.theplaceholder.anotheredition.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.theplaceholder.anotheredition.AnotherEdition;

import java.util.HashSet;
import java.util.Set;

@Config(name = AnotherEdition.MOD_ID)
public class AnotherEditionConfig implements ConfigData {
    private final String[] netherPortalFrameBlocks = new String[] {
            "minecraft:obsidian",
            "minecraft:crying_obsidian"
    };

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

    public static AnotherEditionConfig register() {
        return AutoConfig.register(AnotherEditionConfig.class, JanksonConfigSerializer::new).getConfig();
    }
}
