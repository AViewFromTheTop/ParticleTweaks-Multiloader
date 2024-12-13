package net.lunade.particletweaks.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;
import java.util.function.Supplier;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.NotNull;

public class RegistryHelper {

	@ExpectPlatform
	public static @NotNull Supplier<SimpleParticleType> register(String name) {
		throw new AssertionError();
	}
}
