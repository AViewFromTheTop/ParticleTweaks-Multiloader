package net.lunade.particletweaks.mixin.client.tweaks.wilderwild;

import net.frozenblock.wilderwild.particle.MesogleaDripParticle;
import net.lunade.particletweaks.impl.ParticleTweakInterface;
import net.minecraft.client.particle.Particle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin({
	MesogleaDripParticle.BMesogleaHangProvider.class,
	MesogleaDripParticle.BPMesogleaHangProvider.class,
	MesogleaDripParticle.LMesogleaHangProvider.class,
	MesogleaDripParticle.PMesogleaHangProvider.class,
	MesogleaDripParticle.PPMesogleaHangProvider.class,
	MesogleaDripParticle.RMesogleaHangProvider.class,
	MesogleaDripParticle.YMesogleaHangProvider.class,
})
public class MesogleaHangParticleMixin {

	@Inject(method = "createParticle*", at = @At("TAIL"))
	public void particleTweaks$mesogleaHanging(CallbackInfoReturnable<Particle> info) {
		if (info.getReturnValue() instanceof ParticleTweakInterface particleTweakInterface) {
			particleTweakInterface.particleTweaks$setNewSystem(true);
			particleTweakInterface.particleTweaks$setScaler(0.15F);
			particleTweakInterface.particleTweaks$setScalesToZero();
			particleTweakInterface.particleTweaks$setCanShrink(false);
			particleTweakInterface.particleTweaks$setCanBurn(true);
		}
	}

}
