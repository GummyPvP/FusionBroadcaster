package fusionbroadcaster.main;

import org.bukkit.plugin.java.JavaPlugin;

import fusionbroadcaster.utils.BroadcastManager;
import fusionbroadcaster.utils.ConfigManager;
import fusionbroadcaster.utils.Settings;

public class Main extends JavaPlugin {

	private static Main instance;
	private ConfigManager config;

	@Override
	public void onEnable() {

		instance = this;

		config = new ConfigManager("config", null);

		Settings.get().initSettings();

		BroadcastManager.get().registerBroadcasts();
	}

	@Override
	public void onDisable() {
		instance = null;
	}

	public static Main getInstance() {
		return instance;
	}

	public ConfigManager getConfiguration() {
		return config;
	}
}