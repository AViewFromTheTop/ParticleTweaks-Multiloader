package net.lunade.particletweaks.platform.neoforge;

import java.util.function.Supplier;
import net.lunade.particletweaks.ParticleTweaksConstants;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

public class RegistryHelperImpl {
	public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, ParticleTweaksConstants.MOD_ID);

	public static @NotNull Supplier<SimpleParticleType> register(
		String name
	) {
		return PARTICLE_TYPES.register(name, () -> new SimpleParticleType(false));
	}
}
