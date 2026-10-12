package net.astralya.yeastnfeast.screen;

import dev.architectury.registry.menu.MenuRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.screen.custom.KegMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(YeastNFeastMod.MODID, Registries.MENU);

    public static final RegistrySupplier<MenuType<KegMenu>> KEG_MENU =
            MENUS.register("keg_menu", () -> MenuRegistry.ofExtended(KegMenu::new));

    public static void init() {
        MENUS.register();
    }
}
