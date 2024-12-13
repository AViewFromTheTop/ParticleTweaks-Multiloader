package net.lunade.particletweaks;

import java.nio.file.Path;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class was created to fix issue #289: <a href="https://github.com/FrozenBlock/WilderWild/issues/289">...</a>.
 */
public final class ParticleTweaksPreLoadConstants {
	public static final String PROJECT_ID = "Particle Tweaks";
	public static final String MOD_ID = "particletweaks";
	public static final Logger LOGGER = LoggerFactory.getLogger(PROJECT_ID);

	@Contract(pure = true)
	public static @NotNull Path configPath(String name, boolean json5) {
		return Path.of("./config/" + MOD_ID + "/" + name + "." + (json5 ? "json5" : "json"));
	}
}
