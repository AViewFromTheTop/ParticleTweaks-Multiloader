package net.lunade.particletweaks.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientChunkEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.lunade.particletweaks.ParticleTweaksClient;
import net.lunade.particletweaks.impl.CaveDustSpawner;
import net.lunade.particletweaks.impl.FlowingFluidParticleUtil;
import net.lunade.particletweaks.impl.TorchParticleUtil;
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
import net.lunade.particletweaks.registry.ParticleTweaksParticleTypes;

public class ParticleTweaksFabricClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ParticleTweaksClient.onInitialize();

		ParticleTweaksParticleTypes.init();

		ClientChunkEvents.CHUNK_UNLOAD.register((clientLevel, levelChunk) -> {
			FlowingFluidParticleUtil.clearCascadesInChunk(levelChunk.getPos());
			TorchParticleUtil.clearTorchesInChunk(levelChunk.getPos());
		});

		ClientLifecycleEvents.CLIENT_STOPPING.register((clientLevel) -> {
			FlowingFluidParticleUtil.clearCascades();
			TorchParticleUtil.clearTorches();
		});

		ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> {
			FlowingFluidParticleUtil.clearCascades();
			TorchParticleUtil.clearTorches();
		});

		ClientTickEvents.START_WORLD_TICK.register((clientLevel) -> {
			FlowingFluidParticleUtil.tickCascades(clientLevel);
			TorchParticleUtil.tickTorches(clientLevel);
			CaveDustSpawner.tick(clientLevel);
		});

		ParticleFactoryRegistry registry = ParticleFactoryRegistry.getInstance();
		registry.register(ParticleTweaksParticleTypes.FLOWING_LAVA.get(), FluidFlowParticle.LavaFactory::new);
		registry.register(ParticleTweaksParticleTypes.FLOWING_WATER.get(), FluidFlowParticle.WaterFactory::new);
		registry.register(ParticleTweaksParticleTypes.SMALL_BUBBLE.get(), SmallBubbleParticle.Factory::new);
		registry.register(ParticleTweaksParticleTypes.SMALL_CASCADE.get(), FluidFlowParticle.SmallCascadeFactory::new);
		registry.register(ParticleTweaksParticleTypes.CASCADE_A.get(), FluidFlowParticle.CascadeFactory::new);
		registry.register(ParticleTweaksParticleTypes.CASCADE_B.get(), FluidFlowParticle.CascadeFactory::new);
		registry.register(ParticleTweaksParticleTypes.SPLASH.get(), FluidFlowParticle.SplashFactory::new);
		registry.register(ParticleTweaksParticleTypes.RIPPLE.get(), RippleParticle.Factory::new);
		registry.register(ParticleTweaksParticleTypes.WAVE_OUTLINE.get(), WaveParticle.OutlineFactory::new);
		registry.register(ParticleTweaksParticleTypes.WAVE.get(), WaveParticle.Factory::new);
		registry.register(ParticleTweaksParticleTypes.WAVE_SEED.get(), WaveSeedParticle.Factory::new);
		registry.register(ParticleTweaksParticleTypes.CAVE_DUST.get(), CaveDustParticle.Factory::new);
		registry.register(ParticleTweaksParticleTypes.POOF.get(), PoofParticle.Factory::new);
		registry.register(ParticleTweaksParticleTypes.FLARE.get(), FlareParticle.Factory::new);
		registry.register(ParticleTweaksParticleTypes.SOUL_FLARE.get(), FlareParticle.SoulFactory::new);
		registry.register(ParticleTweaksParticleTypes.CAMPFIRE_FLARE.get(), CampfireFlareParticle.Factory::new);
		registry.register(ParticleTweaksParticleTypes.SOUL_CAMPFIRE_FLARE.get(), CampfireFlareParticle.SoulFactory::new);
		registry.register(ParticleTweaksParticleTypes.COMFY_SMOKE_A.get(), ComfySmokeParticle.Factory::new);
		registry.register(ParticleTweaksParticleTypes.COMFY_SMOKE_B.get(), ComfySmokeParticle.Factory::new);
	}
}
