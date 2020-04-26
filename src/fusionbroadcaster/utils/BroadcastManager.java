package fusionbroadcaster.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.Sound;
import org.bukkit.Note.Tone;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fusionbroadcaster.main.Main;

public class BroadcastManager {

	private BroadcastManager() {
		
	}
	
	static BroadcastManager instance = new BroadcastManager();
	
	public static BroadcastManager get() {
		return instance;
	}
	
	public void registerBroadcasts() {

		new BukkitRunnable() {

			int interval = 0;

			public void run() {

				if (interval == Settings.get().MESSAGES.size()) {

					interval = 0;
				}

				if (interval < (Settings.get().MESSAGES.size() + 1)) {

					if (Settings.get().PLAYER_AMOUNT) {

						if (Bukkit.getServer().getOnlinePlayers().size() > 0)
							Bukkit.broadcastMessage(
									ChatColor.translateAlternateColorCodes('&', Settings.get().MESSAGES.get(interval))
											.replace("%n", "\n"));
						playSound();
						
						playInstrument();
						
						interval++;

					} else {

						Bukkit.broadcastMessage(
								ChatColor.translateAlternateColorCodes('&', Settings.get().MESSAGES.get(interval))
										.replace("%n", "\n"));
						playSound();
						
						playInstrument();

						interval++;
					}

				} else {

					interval = 0;
				}
			}
		}.runTaskTimer(Main.getInstance(), 0, 20 * Settings.get().MESSAGE_DELAY);
	}

	private void playSound() {

		if (Settings.get().PLAY_SOUND.equalsIgnoreCase("none"))
			return;

		for (Player online : Bukkit.getOnlinePlayers()) {
			online.playSound(online.getLocation(), Sound.valueOf(Settings.get().PLAY_SOUND.toUpperCase()), 1.0F, 1.0F);
		}
	}

	private void playInstrument() {

		if (Settings.get().PLAY_SHARP_INSTRUMENT.equalsIgnoreCase("none"))
			return;

		if (Settings.get().PLAY_SHARP_TONE.equalsIgnoreCase("none"))
			return;

		if (Settings.get().PLAY_SHARP_OCTAVE == 0)
			return;

		for (Player online : Bukkit.getOnlinePlayers()) {

			online.playNote(online.getLocation(),
					Instrument.valueOf(Settings.get().PLAY_SHARP_INSTRUMENT.toUpperCase()),
					Note.sharp(Settings.get().PLAY_SHARP_OCTAVE, Tone.valueOf(Settings.get().PLAY_SHARP_TONE)));
		}
		
		if (Settings.get().PLAY_NATURAL_INSTRUMENT.equalsIgnoreCase("none"))
			return;

		if (Settings.get().PLAY_NATURAL_TONE.equalsIgnoreCase("none"))
			return;

		if (Settings.get().PLAY_NATURAL_OCTAVE == 0)
			return;
		
		for (Player online : Bukkit.getOnlinePlayers()) {

			online.playNote(online.getLocation(),
					Instrument.valueOf(Settings.get().PLAY_NATURAL_INSTRUMENT.toUpperCase()),
					Note.natural(Settings.get().PLAY_NATURAL_OCTAVE, Tone.valueOf(Settings.get().PLAY_NATURAL_TONE)));
		}
				
		if (Settings.get().PLAY_FLAT_INSTRUMENT.equalsIgnoreCase("none"))
			return;

		if (Settings.get().PLAY_FLAT_TONE.equalsIgnoreCase("none"))
			return;

		if (Settings.get().PLAY_FLAT_OCTAVE == 0)
			return;
		
		for (Player online : Bukkit.getOnlinePlayers()) {

			online.playNote(online.getLocation(),
					Instrument.valueOf(Settings.get().PLAY_FLAT_INSTRUMENT.toUpperCase()),
					Note.flat(Settings.get().PLAY_FLAT_OCTAVE, Tone.valueOf(Settings.get().PLAY_FLAT_TONE)));
		}
	}
}
