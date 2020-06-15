package com.fluidbug;

import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(FluidBugMod.MOD_ID)
public class FluidBugMod {

    public static final String MOD_ID = "fluid_bug";

    public static final DeferredRegister<Fluid> FLUIDS = new DeferredRegister<>(ForgeRegistries.FLUIDS, FluidBugMod.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, FluidBugMod.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, FluidBugMod.MOD_ID);

    public static final ResourceLocation BUGGY_FLUID_FLUID_STILL_TEXTURE = new ResourceLocation("block/water_still");
    public static final ResourceLocation BUGGY_FLUID_FLUID_FLOWING_TEXTURE = new ResourceLocation("block/water_flow");

    public static final RegistryObject<FlowingFluid> BUGGY_FLUID_FLUID_STILL = FLUIDS.register("buggy_fluid",
            () -> new ForgeFlowingFluid.Source(FluidBugMod.BUGGY_FLUID_PROPERTIES));

    public static final RegistryObject<FlowingFluid> BUGGY_FLUID_FLUID_FLOWING = FLUIDS.register("buggy_fluid_flow",
            () -> new ForgeFlowingFluid.Flowing(FluidBugMod.BUGGY_FLUID_PROPERTIES));

    public static final RegistryObject<BucketItem> BUGGY_FLUID_BUCKET = ITEMS.register(
            "buggy_fluid_bucket",
            () -> new BucketItem(
                    BUGGY_FLUID_FLUID_STILL,
                    new Item.Properties()
                            .containerItem(Items.BUCKET)
                            .maxStackSize(1)
                            .group(ItemGroup.MISC)
            )
    );

    public static final RegistryObject<FlowingFluidBlock> BUGGY_FLUID_BLOCK = BLOCKS.register("buggy_fluid_block",
            () -> new FlowingFluidBlock(
                    BUGGY_FLUID_FLUID_STILL,
                    Block.Properties.create(Material.WATER, MaterialColor.BROWN)
                            .doesNotBlockMovement()
                            .hardnessAndResistance(100.0F)
                            .speedFactor(0.75F)
                            .noDrops()
            )
    );

    public static final ForgeFlowingFluid.Properties BUGGY_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            BUGGY_FLUID_FLUID_STILL,
            BUGGY_FLUID_FLUID_FLOWING,
            FluidAttributes.builder(BUGGY_FLUID_FLUID_STILL_TEXTURE, BUGGY_FLUID_FLUID_FLOWING_TEXTURE)
    )
            .tickRate(20)
            .block(BUGGY_FLUID_BLOCK)
            .bucket(BUGGY_FLUID_BUCKET);

    public FluidBugMod() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(modEventBus);
        FLUIDS.register(modEventBus);
        BLOCKS.register(modEventBus);
        modEventBus.register(this);
    }

}
