package net.lunade.particletweaks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
@Environment(EnvType.CLIENT)
public class ParticleTweaksClient {
	public static boolean areConfigsInit = false;

	public static void onInitialize() {
	}
}
