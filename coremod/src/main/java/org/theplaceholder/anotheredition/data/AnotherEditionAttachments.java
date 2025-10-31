package org.theplaceholder.anotheredition.data;

import com.mojang.serialization.Codec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.theplaceholder.anotheredition.AnotherEdition;

import java.util.function.Supplier;

public class AnotherEditionAttachments {
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, AnotherEdition.MOD_ID);

    public static final Supplier<AttachmentType<Boolean>> STARTING_INV = ATTACHMENT_TYPES.register(
            "starting_inv", () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL.fieldOf("starting_inv").codec()).copyOnDeath().build()
    );

    public static void register(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }
}
