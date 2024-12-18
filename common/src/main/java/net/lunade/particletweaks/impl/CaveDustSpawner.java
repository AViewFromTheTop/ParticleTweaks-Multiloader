package net.lunade.particletweaks.impl;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.lunade.particletweaks.config.ParticleTweaksConfigGetter;
import net.lunade.particletweaks.registry.ParticleTweaksParticleTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.levelgen.Heightmap;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class CaveDustSpawner {

	public static void tick(ClientLevel world) {
		if (ParticleTweaksConfigGetter.trailerCaveDust()) {
			Minecraft minecraft = Minecraft.getInstance();
			BlockPos pos = minecraft.gameRenderer.getMainCamera().getBlockPosition();
			animateTick(world, pos.getX(), pos.getY(), pos.getZ());
		}
	}

	private static void animateTick(@NotNull ClientLevel level, int posX, int posY, int posZ) {
		RandomSource randomSource = level.random;
		BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
		for (int i = 0; i < 20; ++i) {
			spawnCaveDustParticles(level, posX, posY, posZ, 32, randomSource, mutableBlockPos);
		}
		for (int i = 0; i < 20; ++i) {
			spawnCaveDustParticles(level, posX, posY, posZ, 16, randomSource, mutableBlockPos);
		}
	}

	private static void spawnCaveDustParticles(
		@NotNull ClientLevel level, int posX, int posY, int posZ, int range, @NotNull RandomSource random, @NotNull BlockPos.MutableBlockPos blockPos
	) {
		int i = posX + random.nextIntBetweenInclusive(-range, range);
		int j = posY + random.nextIntBetweenInclusive(-range, range);
		int k = posZ + random.nextIntBetweenInclusive(-range, range);
		int heightMapY = level.getHeight(Heightmap.Types.WORLD_SURFACE, i, k);
		blockPos.set(i, j, k);

		if (heightMapY > j + random.nextInt(32)
			&& level.getBlockState(blockPos).isAir()
			&& !level.canSeeSkyFromBelowWater(blockPos)
			&& level.getBrightness(LightLayer.SKY, blockPos) == 0
			&& random.nextInt(Math.max(level.getBrightness(LightLayer.BLOCK, blockPos) - random.nextInt(10), 1)) == 0
		) {
			int levelMin = level.getMinY();
			int levelMax = level.getMaxY();
			int difference = levelMax - levelMin;
			if (random.nextBoolean() && (random.nextFloat() * (posY / difference)) <= 0.0015F) {
				level.addParticle(
					ParticleTweaksParticleTypes.CAVE_DUST.get(),
					i,
					j,
					k,
					0D,
					0D,
					0D
				);
			}
		}
	}
}
