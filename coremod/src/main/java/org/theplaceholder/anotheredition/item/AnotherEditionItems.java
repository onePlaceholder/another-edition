package org.theplaceholder.anotheredition.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.theplaceholder.anotheredition.AnotherEdition;

import java.util.function.Supplier;

public class AnotherEditionItems {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, AnotherEdition.MOD_ID);

    public static final Supplier<NetherIgniterItem> NETHER_IGNITER = register(() -> new NetherIgniterItem(new Item.Settings().maxCount(1).maxDamage(4)), "nether_igniter");

    private static <T extends Item> Supplier<T> register(Supplier<T> item, String id) {
        return ITEMS.register(id, item);
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
