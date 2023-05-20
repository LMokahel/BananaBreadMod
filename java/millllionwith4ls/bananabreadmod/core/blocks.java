package millllionwith4ls.bananabreadmod.core;

import millllionwith4ls.bananabreadmod.BananaBreadMod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class blocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, BananaBreadMod.MODID);

    public static final RegistryObject<Block> VANILLA_BEAN_CROP = BLOCKS.register("vanilla_bean_crop",
            () -> new VanillaBeanCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));
    public static final RegistryObject<Block> BANANA_CROP = BLOCKS.register("banana_crop",
            () -> new BananaCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));
    public static final RegistryObject<Block> BANANA_NUKE = BLOCKS.register("banana_nuke", () -> new BananaNuke(BlockBehaviour.Properties.copy(Blocks.TNT).instabreak().noOcclusion().sound(SoundType.GRASS).lightLevel((p_50872_) -> {return 15;})));



    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}