package net.lunade.particletweaks.neoforge.event.impl;

import net.lunade.particletweaks.impl.CaveDustSpawner;
import net.lunade.particletweaks.impl.FlowingFluidParticleUtil;
import net.lunade.particletweaks.impl.TorchParticleUtil;
import net.minecraft.client.multiplayer.ClientLevel;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.event.level.ChunkEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class ParticleTweaksNeoForgeClientEvents {

	@SubscribeEvent
	public static void chunkUnload(ChunkEvent.@NotNull Unload event) {
		FlowingFluidParticleUtil.clearCascadesInChunk(event.getChunk().getPos());
		TorchParticleUtil.clearTorchesInChunk(event.getChunk().getPos());
	}

	@SubscribeEvent
	public static void disconnect(ClientPlayerNetworkEvent.LoggingOut event) {
		FlowingFluidParticleUtil.clearCascades();
		TorchParticleUtil.clearTorches();
	}

	@SubscribeEvent
	public static void startWorldTick(LevelTickEvent.@NotNull Pre event) {
		if (event.getLevel() instanceof ClientLevel clientLevel) {
			FlowingFluidParticleUtil.tickCascades(clientLevel);
			TorchParticleUtil.tickTorches(clientLevel);
			CaveDustSpawner.tick(clientLevel);
		}
	}
}
