package fusionbroadcaster.utils;

import org.bukkit.configuration.file.YamlConfiguration;

import fusionbroadcaster.main.Main;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Settings {

	private Settings() {

	}

	static Settings instance = new Settings();

	public static Settings get() {
		return instance;
	}

	public String PLAY_SOUND;
	
	public String PLAY_SHARP_INSTRUMENT;
	public String PLAY_SHARP_TONE;
	public int PLAY_SHARP_OCTAVE;
	
	public String PLAY_NATURAL_INSTRUMENT;
	public String PLAY_NATURAL_TONE;
	public int PLAY_NATURAL_OCTAVE;

	public String PLAY_FLAT_INSTRUMENT;
	public String PLAY_FLAT_TONE;
	public int PLAY_FLAT_OCTAVE;

	
	public boolean PLAYER_AMOUNT;

	public int MESSAGE_DELAY;

	public List<String> MESSAGES;

	public void initSettings() {

		PLAYER_AMOUNT = initSetting("player-amount-check");

		PLAY_SOUND = initSetting("play-mc-sound");
		
		PLAY_SHARP_INSTRUMENT = initSetting("play-sharp-instrument.instrument");
		PLAY_SHARP_TONE = initSetting("play-sharp-instrument.tone");
		PLAY_SHARP_OCTAVE = initSetting("play-sharp-instrument.octave");
		
		PLAY_NATURAL_INSTRUMENT = initSetting("play-natural-instrument.instrument");
		PLAY_NATURAL_TONE = initSetting("play-natural-instrument.tone");
		PLAY_NATURAL_OCTAVE = initSetting("play-natural-instrument.octave");
		
		PLAY_FLAT_INSTRUMENT = initSetting("play-flat-instrument.instrument");
		PLAY_FLAT_TONE = initSetting("play-flat-instrument.tone");
		PLAY_FLAT_OCTAVE = initSetting("play-flat-instrument.octave");

		
		MESSAGE_DELAY = initSetting("message-delay");
		MESSAGES = initSetting("broadcast.messages");

	}

	@SuppressWarnings("deprecation")
	private static <T> T initSetting(String path) {

		if (!Main.getInstance().getConfiguration().contains(path)) { // check the config file directly from the jar in
																		// case there were any updates

			InputStream tempStream = Main.getInstance().getResource("config.yml");

			YamlConfiguration tempConfig = YamlConfiguration.loadConfiguration(tempStream);

			if (!tempConfig.contains(path)) {
				throw new NullPointerException("Could not find the path specified");
			}

			Main.getInstance().getConfiguration().set(path, tempConfig.get(path));
			initSetting(path);

			try {
				tempStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return Main.getInstance().getConfiguration().get(path);
	}
}
