package net.lunade.particletweaks.fabric.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.lunade.particletweaks.config.cloth.ParticleTweaksConfig;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class ParticleTweaksConfigScreenBuilder {

    @Contract(pure = true)
    public static @NotNull ConfigScreenFactory<Screen> buildScreen() {
        return ParticleTweaksConfig::buildScreen;
    }

}
