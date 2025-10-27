package org.theplaceholder.anotheredition.client.event;

import net.minecraft.client.item.CompassAnglePredicateProvider;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.GlobalPos;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import org.theplaceholder.anotheredition.AnotherEdition;
import org.theplaceholder.anotheredition.item.AnotherEditionItems;

@EventBusSubscriber(modid = AnotherEdition.MOD_ID, value = Dist.CLIENT)
public class AnotherEditionClientEventHandler {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        ModelPredicateProviderRegistry.register(
                AnotherEditionItems.ENDER_COMPASS.get(), Identifier.ofVanilla("angle"),
                new CompassAnglePredicateProvider((world, stack, entity) -> GlobalPos.create(world.getRegistryKey(), world.getSpawnPos()))
        );
    }
}
