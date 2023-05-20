package millllionwith4ls.bananabreadmod.core;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class entities {
    static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, "banana_nuke_primed");

    public static final RegistryObject<EntityType<BananaNukePrimed>> BANANA_NUKE_PRIMED = ENTITIES.register("banana_nuke_primed",
            () -> EntityType.Builder.<BananaNukePrimed>of(BananaNukePrimed::new, MobCategory.MISC)
                    .fireImmune().sized(0.98F, 0.98F).clientTrackingRange(10).updateInterval(10).build(null));

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}





