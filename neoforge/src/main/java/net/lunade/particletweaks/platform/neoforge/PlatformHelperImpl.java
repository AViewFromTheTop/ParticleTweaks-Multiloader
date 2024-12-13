package net.lunade.particletweaks.platform.neoforge;

import java.lang.management.ManagementFactory;
import java.nio.file.Path;
import java.util.List;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.LoadingModList;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class PlatformHelperImpl {

	@Contract(pure = true)
	public static @NotNull String getPlatformName() {
		return "NeoForge";
	}

	public static boolean isFabric() {
		return false;
	}

	public static boolean isNeoForge() {
		return true;
	}

	public static boolean isModLoaded(String modId) {
		for (var mod : LoadingModList.get().getMods()) {
			if (mod.getModId().equals(modId)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isDevelopmentEnvironment() {
		return !FMLLoader.isProduction();
	}

	public static boolean isDatagen() {
		var runtimeMXBean = ManagementFactory.getRuntimeMXBean();
		List<String> jvmArgs = runtimeMXBean.getInputArguments();
		jvmArgs.forEach(System.out::println);
		return jvmArgs.stream().anyMatch(string -> string.contains("--output"));
	}

	public static Path getGameDir() {
		return FMLLoader.getGamePath();
	}

	public static @NotNull Path getConfigDir() {
		return FMLLoader.getGamePath().resolve("config");
	}

	public static Object envType() {
		return FMLLoader.getDist();
	}
}
