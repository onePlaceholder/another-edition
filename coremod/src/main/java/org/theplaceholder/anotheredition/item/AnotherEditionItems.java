package org.theplaceholder.anotheredition.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.theplaceholder.anotheredition.AnotherEdition;

import java.util.function.Supplier;

public class AnotherEditionItems {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, AnotherEdition.MOD_ID);

    public static final Supplier<NetherIgniterItem> NETHER_IGNITER = register(() -> new NetherIgniterItem(new Item.Settings().maxCount(1).maxDamage(4)), "nether_igniter");
    public static final Supplier<Item> ENCYCLOPEDIA_UNIVERSALIS = register(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON), "encyclopedia_universalis");
    public static final Supplier<Item> ENDER_COMPASS = register(new Item.Settings().maxCount(1), "ender_compass");

    private static Supplier<Item> register(Item.Settings settings, String id) {
        return register(() -> new Item(settings), id);
    }

    private static <T extends Item> Supplier<T> register(Supplier<T> item, String id) {
        return ITEMS.register(id, item);
    }

    public static void register(IEventBus eventBus) {
        DeferredRegister<ItemGroup> itemGroups = DeferredRegister.create(Registries.ITEM_GROUP, AnotherEdition.MOD_ID);
        itemGroups.register("main", () -> ItemGroup.builder().displayName(Text.translatable("itemGroup.another_edition.main")).displayItems(ITEMS.getEntries()).icon(() -> NETHER_IGNITER.get().getDefaultStack()).build());
        itemGroups.register(eventBus);

        ITEMS.register(eventBus);
    }
}
