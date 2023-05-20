package millllionwith4ls.bananabreadmod.core;

import millllionwith4ls.bananabreadmod.BananaBreadMod;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import java.util.Optional;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BannerPatternTags;
import net.minecraft.tags.InstrumentTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.food.Foods;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;

import static millllionwith4ls.bananabreadmod.core.blocks.BANANA_NUKE;

public class items{
    static float oiledSat = 0.41666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666667f;
    static float friedSat = 0.42857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857143f;
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BananaBreadMod.MODID);
    public static final RegistryObject<Item> BANANA_BREAD = ITEMS.register("banana_bread", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(10).saturationMod(0.7f).build()).tab(BananaBreadMod.bananaTAB)));
    public static final RegistryObject<Item> BANANA_DOUGH = ITEMS.register("banana_dough", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(1f).effect(() -> {
        return new MobEffectInstance(MobEffects.HUNGER, 1200, 0);
    }, 1.0F).build()).tab(BananaBreadMod.bananaTAB)));
    public static final RegistryObject<Item> BANANA_PIE = ITEMS.register("banana_pie", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(12).saturationMod(0.2f).build()).tab(BananaBreadMod.bananaTAB)));
    public static final RegistryObject<Item> BANANA_OILED = ITEMS.register("banana_oiled", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationMod(oiledSat).effect(() -> {
        return new MobEffectInstance(MobEffects.HUNGER, 1200, 0);
    }, 1.0F).build()).tab(BananaBreadMod.bananaTAB)));
    public static final RegistryObject<Item> BANANA_FRIED = ITEMS.register("banana_fried", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationMod(friedSat).build()).tab(BananaBreadMod.bananaTAB)));
    public static final RegistryObject<Item> BANANA_DONUT = ITEMS.register("banana_donut", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(14).saturationMod(0.2f).build()).tab(BananaBreadMod.bananaTAB)));
    public static final RegistryObject<Item> BANANA_COOKIE = ITEMS.register("banana_cookie", () -> new Item(new Item.Properties().tab(BananaBreadMod.bananaTAB).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.25f).effect(() -> {
        return new MobEffectInstance(MobEffects.REGENERATION, 40, 1);
    }, 2000F).build()).tab(BananaBreadMod.bananaTAB)));
    public static final RegistryObject<Item> BANANA_PUDDING = ITEMS.register("banana_pudding", () -> new Item(new Item.Properties().tab(BananaBreadMod.bananaTAB).food(new FoodProperties.Builder().nutrition(8).saturationMod(0.5f).effect(() -> {
        return new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 1);
    }, 2000F).build()).tab(BananaBreadMod.bananaTAB)));
    public static final RegistryObject<Item> BANANA_SMOOTHIE = ITEMS.register("banana_smoothie", () -> new Item(new Item.Properties().tab(BananaBreadMod.bananaTAB).food(new FoodProperties.Builder().alwaysEat().effect(() -> {
        return new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300, 1);
    }, 2000F).build()).tab(BananaBreadMod.bananaTAB)));
    public static final RegistryObject<Item> BANANA_PIE_SLICE = ITEMS.register("banana_pie_slice", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(0.2f).build()).tab(BananaBreadMod.bananaTAB)));
    public static final RegistryObject<Item> VANILLA_BEAN_SEEDS = ITEMS.register("vanilla_bean_seeds", () -> new ItemNameBlockItem(blocks.VANILLA_BEAN_CROP.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC).tab(BananaBreadMod.bananaTAB)));
    public static final RegistryObject<Item> VANILLA_BEAN = ITEMS.register("vanilla_bean", () -> new Item(new Item.Properties().tab(BananaBreadMod.bananaTAB).food(new FoodProperties.Builder().nutrition(1).saturationMod(0.5f).build())));
    public static final RegistryObject<Item> VANILLA_EXTRACT = ITEMS.register("vanilla_extract", () -> new Item(new Item.Properties().tab(BananaBreadMod.bananaTAB).food(new FoodProperties.Builder().nutrition(1).saturationMod(0.5f).alwaysEat().effect(() -> {
        return new MobEffectInstance(MobEffects.HUNGER, 1200, 0);
    }, 1.0F).build()).tab(BananaBreadMod.bananaTAB)));
    public static final RegistryObject<Item> BANANA_CHOCOLATE = ITEMS.register("banana_chocolate", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationMod(friedSat).build()).tab(BananaBreadMod.bananaTAB)));
    public static final RegistryObject<Item> CHOCOLATE_BUCKET = ITEMS.register("chocolate_bucket", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().build()).tab(BananaBreadMod.bananaTAB)));
    public static final RegistryObject<Item> BANANA_SEEDS = ITEMS.register("banana_seeds", () -> new ItemNameBlockItem(blocks.BANANA_CROP.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC).tab(BananaBreadMod.bananaTAB)));
    public static final RegistryObject<Item> BANANA = ITEMS.register("banana", () -> new Item(new Item.Properties().tab(BananaBreadMod.bananaTAB).food(new FoodProperties.Builder().nutrition(1).saturationMod(0.5f).build()).tab(CreativeModeTab.TAB_FOOD).tab(BananaBreadMod.bananaTAB)));
    public static final RegistryObject<Item> BANANA_NUKE_ITEM = registerBlockItem("banana_nuke_item", BANANA_NUKE, BananaBreadMod.bananaTAB);


    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return BananaBreadMod.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(BananaBreadMod.bananaTAB)));
    }
}
