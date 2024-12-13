package net.lunade.particletweaks.neoforge;

import net.lunade.particletweaks.ParticleTweaksClient;
import net.lunade.particletweaks.ParticleTweaksConstants;
import net.lunade.particletweaks.ParticleTweaksPreLoadConstants;
import net.lunade.particletweaks.config.cloth.ParticleTweaksConfig;
import net.lunade.particletweaks.neoforge.event.impl.ParticleTweaksNeoForgeClientEvents;
import net.lunade.particletweaks.particle.CampfireFlareParticle;
import net.lunade.particletweaks.particle.CaveDustParticle;
import net.lunade.particletweaks.particle.ComfySmokeParticle;
import net.lunade.particletweaks.particle.FlareParticle;
import net.lunade.particletweaks.particle.FluidFlowParticle;
import net.lunade.particletweaks.particle.PoofParticle;
import net.lunade.particletweaks.particle.RippleParticle;
import net.lunade.particletweaks.particle.SmallBubbleParticle;
import net.lunade.particletweaks.particle.WaveParticle;
import net.lunade.particletweaks.particle.WaveSeedParticle;
import net.lunade.particletweaks.platform.neoforge.RegistryHelperImpl;
import net.lunade.particletweaks.registry.ParticleTweaksParticleTypes;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.jetbrains.annotations.NotNull;

@Mod(value = ParticleTweaksPreLoadConstants.MOD_ID, dist = Dist.CLIENT)
public class ParticleTweaksNeoForgeClient {

	public ParticleTweaksNeoForgeClient(@NotNull IEventBus eventBus, ModContainer container) {
		RegistryHelperImpl.PARTICLE_TYPES.register(eventBus);
		eventBus.register(this);
		NeoForge.EVENT_BUS.register(ParticleTweaksNeoForgeClientEvents.class);
		createConfigScreen(container);
		ParticleTweaksClient.onInitialize();
	}

	private static void createConfigScreen(ModContainer container) {
		if (ParticleTweaksConstants.CLOTH_CONFIG) {
			container.registerExtensionPoint(IConfigScreenFactory.class, (modContainer, screen) -> ParticleTweaksConfig.buildScreen(screen));
		}
	}

	@SubscribeEvent
	public void register(@NotNull RegisterEvent event) {
		ParticleTweaksParticleTypes.init();
	}

	@SubscribeEvent
	public void registerParticleProviders(@NotNull RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(ParticleTweaksParticleTypes.FLOWING_LAVA.get(), FluidFlowParticle.LavaFactory::new);
		event.registerSpriteSet(ParticleTweaksParticleTypes.FLOWING_WATER.get(), FluidFlowParticle.WaterFactory::new);
		event.registerSpriteSet(ParticleTweaksParticleTypes.SMALL_BUBBLE.get(), SmallBubbleParticle.Factory::new);
		event.registerSpriteSet(ParticleTweaksParticleTypes.SMALL_CASCADE.get(), FluidFlowParticle.SmallCascadeFactory::new);
		event.registerSpriteSet(ParticleTweaksParticleTypes.CASCADE_A.get(), FluidFlowParticle.CascadeFactory::new);
		event.registerSpriteSet(ParticleTweaksParticleTypes.CASCADE_B.get(), FluidFlowParticle.CascadeFactory::new);
		event.registerSpriteSet(ParticleTweaksParticleTypes.SPLASH.get(), FluidFlowParticle.SplashFactory::new);
		event.registerSpriteSet(ParticleTweaksParticleTypes.RIPPLE.get(), RippleParticle.Factory::new);
		event.registerSpriteSet(ParticleTweaksParticleTypes.WAVE_OUTLINE.get(), WaveParticle.OutlineFactory::new);
		event.registerSpriteSet(ParticleTweaksParticleTypes.WAVE.get(), WaveParticle.Factory::new);
		event.registerSpriteSet(ParticleTweaksParticleTypes.WAVE_SEED.get(), WaveSeedParticle.Factory::new);
		event.registerSpriteSet(ParticleTweaksParticleTypes.CAVE_DUST.get(), CaveDustParticle.Factory::new);
		event.registerSpriteSet(ParticleTweaksParticleTypes.POOF.get(), PoofParticle.Factory::new);
		event.registerSpriteSet(ParticleTweaksParticleTypes.FLARE.get(), FlareParticle.Factory::new);
		event.registerSpriteSet(ParticleTweaksParticleTypes.SOUL_FLARE.get(), FlareParticle.SoulFactory::new);
		event.registerSpriteSet(ParticleTweaksParticleTypes.CAMPFIRE_FLARE.get(), CampfireFlareParticle.Factory::new);
		event.registerSpriteSet(ParticleTweaksParticleTypes.SOUL_CAMPFIRE_FLARE.get(), CampfireFlareParticle.SoulFactory::new);
		event.registerSpriteSet(ParticleTweaksParticleTypes.COMFY_SMOKE_A.get(), ComfySmokeParticle.Factory::new);
		event.registerSpriteSet(ParticleTweaksParticleTypes.COMFY_SMOKE_B.get(), ComfySmokeParticle.Factory::new);
	}
}
