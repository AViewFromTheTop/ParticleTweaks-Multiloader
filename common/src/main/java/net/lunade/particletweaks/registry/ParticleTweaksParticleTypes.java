package net.lunade.particletweaks.registry;

import java.util.function.Supplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.lunade.particletweaks.platform.RegistryHelper;
import net.minecraft.core.particles.SimpleParticleType;

@Environment(EnvType.CLIENT)
public class ParticleTweaksParticleTypes {
	public static final Supplier<SimpleParticleType> FLOWING_LAVA = RegistryHelper.register("flowing_lava");
	public static final Supplier<SimpleParticleType> FLOWING_WATER = RegistryHelper.register("flowing_water");
	public static final Supplier<SimpleParticleType> SMALL_BUBBLE = RegistryHelper.register("small_bubble");
	public static final Supplier<SimpleParticleType> SMALL_CASCADE = RegistryHelper.register("small_cascade");
	public static final Supplier<SimpleParticleType> CASCADE_A = RegistryHelper.register("cascade_a");
	public static final Supplier<SimpleParticleType> CASCADE_B = RegistryHelper.register("cascade_b");
	public static final Supplier<SimpleParticleType> SPLASH = RegistryHelper.register("splash");
	public static final Supplier<SimpleParticleType> RIPPLE = RegistryHelper.register("ripple");
	public static final Supplier<SimpleParticleType> WAVE_OUTLINE = RegistryHelper.register("wave_outline");
	public static final Supplier<SimpleParticleType> WAVE = RegistryHelper.register("wave");
	public static final Supplier<SimpleParticleType> WAVE_SEED = RegistryHelper.register("wave_seed");
	public static final Supplier<SimpleParticleType> CAVE_DUST = RegistryHelper.register("cave_dust");
	public static final Supplier<SimpleParticleType> POOF = RegistryHelper.register("poof");
	public static final Supplier<SimpleParticleType> FLARE = RegistryHelper.register("flare");
	public static final Supplier<SimpleParticleType> SOUL_FLARE = RegistryHelper.register("soul_flare");
	public static final Supplier<SimpleParticleType> CAMPFIRE_FLARE = RegistryHelper.register("campfire_flare");
	public static final Supplier<SimpleParticleType> SOUL_CAMPFIRE_FLARE = RegistryHelper.register("soul_campfire_flare");
	public static final Supplier<SimpleParticleType> COMFY_SMOKE_A = RegistryHelper.register("comfy_smoke_a");
	public static final Supplier<SimpleParticleType> COMFY_SMOKE_B = RegistryHelper.register("comfy_smoke_b");

	public static void init() {
	}

}
