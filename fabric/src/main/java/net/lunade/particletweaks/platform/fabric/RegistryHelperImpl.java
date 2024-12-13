package net.lunade.particletweaks.platform.fabric;

import java.util.function.Supplier;
import net.lunade.particletweaks.ParticleTweaksConstants;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import org.jetbrains.annotations.NotNull;

public class RegistryHelperImpl {

	public static @NotNull Supplier<SimpleParticleType> register(
		@NotNull String name
	) {
		SimpleParticleType particleType = new SimpleParticleType(false);
		Registry.register(BuiltInRegistries.PARTICLE_TYPE, ParticleTweaksConstants.id(name), particleType);
		return () -> particleType;
	}
}
