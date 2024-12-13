package net.lunade.particletweaks.fabric.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.lunade.particletweaks.ParticleTweaksConstants;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public final class ParticleTweaksModMenuIntegration implements ModMenuApi {

    @Contract(pure = true)
    @Override
    public @NotNull ConfigScreenFactory<Screen> getModConfigScreenFactory() {
        if (ParticleTweaksConstants.CLOTH_CONFIG) {
            return ParticleTweaksConfigScreenBuilder.buildScreen();
        }
        return (screen -> null);
    }

}
