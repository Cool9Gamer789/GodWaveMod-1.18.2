package net.Jack_Black12.wave.item;

import com.google.common.eventbus.EventBus;
import net.Jack_Black12.wave.GodWaveMod;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, GodWaveMod.MOD_ID);

    public static final RegistryObject<Item> Godwave = ITEMS.register("godwave",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_BREWING)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}