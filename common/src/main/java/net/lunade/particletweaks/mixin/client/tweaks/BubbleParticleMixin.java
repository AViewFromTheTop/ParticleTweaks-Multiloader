package net.lunade.particletweaks.mixin.client.tweaks;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.lunade.particletweaks.impl.FlowingFluidParticleUtil;
import net.lunade.particletweaks.impl.ParticleTweakInterface;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.BubbleParticle;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = BubbleParticle.class, priority = 1001)
public abstract class BubbleParticleMixin extends TextureSheetParticle implements ParticleTweakInterface {

	@Unique
	private boolean particleTweaks$hasSetMaxLifetime;
	@Unique
	private int particleTweaks$maxLifetime;

	protected BubbleParticleMixin(ClientLevel clientLevel, double d, double e, double f) {
		super(clientLevel, d, e, f);
	}

	@Inject(method = "<init>*", at = @At("TAIL"))
	private void particleTweaks$init(CallbackInfo info) {
		this.particleTweaks$setNewSystem(true);
		this.particleTweaks$setScaler(0.35F);
		this.particleTweaks$setScalesToZero();
		this.particleTweaks$setMovesWithFluid(true);
		this.particleTweaks$setCanBurn(true);
	}

	@Inject(method = "getRenderType", at = @At("HEAD"), cancellable = true)
	public void particleTweaks$getRenderType(CallbackInfoReturnable<ParticleRenderType> info) {
		info.setReturnValue(ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT);
	}

	@Inject(method = "tick", at = @At("HEAD"), cancellable = true)
	public void particleTweaks$runScaling(CallbackInfo info) {
		if (this.particleTweaks$usesNewSystem()) {
			if (!this.particleTweaks$hasSetMaxLifetime) {
				this.particleTweaks$hasSetMaxLifetime = true;
				this.particleTweaks$maxLifetime = this.lifetime;
			}
			this.particleTweaks$calcScale();
			this.lifetime = Math.min(this.lifetime + 1, this.particleTweaks$maxLifetime);
			if (this.particleTweaks$getScale(0F) < 0.5F && !this.particleTweaks$hasSwitchedToShrinking()) {
				this.lifetime = Math.min(this.lifetime + 1, this.particleTweaks$maxLifetime);
			}
			if (this.particleTweaks$runScaleRemoval()) {
				this.level.addParticle(ParticleTypes.BUBBLE_POP, this.x, this.y, this.z, 0, 0, 0);
				this.remove();
				info.cancel();
				return;
			}

			Vec3 fluidMovement = FlowingFluidParticleUtil.handleFluidInteraction(
				this.level,
				new Vec3(this.x, this.y, this.z),
				new Vec3(this.xd, this.yd, this.zd),
				this,
				!this.particleTweaks$canBurn(),
				this.particleTweaks$slowsInFluid(),
				this.particleTweaks$movesWithFluid(),
				this.particleTweaks$getFluidMovementScale()
			);

			if (fluidMovement != null) {
				this.xd = fluidMovement.x;
				this.yd = fluidMovement.y;
				this.zd = fluidMovement.z;
			} else {
				info.cancel();
			}
		}
	}

	@WrapOperation(
		method = "tick",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/particle/BubbleParticle;remove()V"
		)
	)
	public void particleTweaks$outOfWater(BubbleParticle instance, Operation<Void> original) {
		if (this.particleTweaks$usesNewSystem()) {
			this.lifetime = 0;
		}
	}

	@Override
	public boolean particleTweaks$runScaleRemoval() {
		if (this.particleTweaks$usesNewSystem()) {
			this.lifetime -= 1;
			if (this.lifetime <= 0 || this.particleTweaks$hasSwitchedToShrinking()) {
				this.particleTweaks$setSwitchedToShrinking(true);
				return true;
			} else {
				this.particleTweaks$setTargetScale(1F);
			}
		}
		return false;
	}

}
