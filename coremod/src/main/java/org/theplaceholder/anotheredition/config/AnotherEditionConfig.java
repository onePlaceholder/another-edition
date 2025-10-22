package org.theplaceholder.anotheredition.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.theplaceholder.utils.PlaceholderUtils;

import java.util.HashSet;
import java.util.Set;

@Config(name = PlaceholderUtils.MODID)
public class AnotherEditionConfig implements ConfigData {
    private NetherPortalConfig netherPortal = new NetherPortalConfig();

    public NetherPortalConfig getNetherPortal() {
        return this.netherPortal;
    }

    public static class NetherPortalConfig {
        private boolean enabled = false;
        private String[] ignitingItems = new String[] {
                "minecraft:flint_and_steel",
                "minecraft:fire_charge"
        };
        private String[] allowedFrameBlocks = new String[] {
                "minecraft:obsidian"
        };

        public boolean isEnabled() {
            return this.enabled;
        }

        public Set<Item> getIgnitingItems() {
            Set<Item> items = new HashSet<>();
            for (String ignitingItem : this.ignitingItems) {
                items.add(BuiltInRegistries.ITEM.get(ResourceLocation.parse(ignitingItem)));
            }
            return items;
        }

        public Set<Block> getAllowedFrameBlocks() {
            Set<Block> blocks = new HashSet<>();
            for (String allowedFrameBlock : this.allowedFrameBlocks) {
                blocks.add(BuiltInRegistries.BLOCK.get(ResourceLocation.parse(allowedFrameBlock)));
            }
            return blocks;
        }
    }

    public static void register() {
        AutoConfig.register(PlaceholderUtilsConfig.class, JanksonConfigSerializer::new);
    }
}
