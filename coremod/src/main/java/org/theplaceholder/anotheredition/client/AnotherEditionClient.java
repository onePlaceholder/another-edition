package org.theplaceholder.anotheredition.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.theplaceholder.anotheredition.AnotherEdition;

@Mod(value = AnotherEdition.MOD_ID, dist = Dist.CLIENT)
public class AnotherEditionClient {
    public AnotherEditionClient(IEventBus modEventBus) {
    }
}
