package pl.inder00.otchlan.tasks;

import java.util.Iterator;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import pl.inder00.otchlan.iOtchlan;
import pl.inder00.otchlan.data.Config;

public class Timmer extends BukkitRunnable {
	
	private int timmer = 60;
	private Config cfg = Config.getInst();
	
	public Timmer(int delay) {
		timmer = delay;
	}
	
	@Override
	public void run() {
		Iterator<Entry<Integer, String>> itr = cfg.messages.entrySet().iterator();
		while(itr.hasNext()) {
			Entry<Integer, String> s = itr.next();
			if(s.getKey() == timmer) {
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', s.getValue()));
			}
		}
		if(timmer <= 0) {
			timmer = cfg.getTimmerDelay();
			iOtchlan.getInst().a();
			return;
		}
		timmer--;
	}

	public int getTimmer() {
		return timmer;
	}

}
