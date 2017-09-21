package pl.inder00.otchlan.data;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import pl.inder00.otchlan.iOtchlan;

public class Config {
	
	private static Config inst;
	public FileConfiguration cfg = iOtchlan.getInst().getConfig();
	
	public HashMap<Integer, String> messages = new HashMap<Integer, String>();
	private int timmerDelay = 300;
	private String worldName = Bukkit.getWorlds().iterator().next().getName();
	public int openedTime = 20;
	public String otchlanClosed = "&7Otchlan zostala &6zamknieta.";
	public String isClosed = "&cOtchlan jest obecnie zamknieta";
	public String isEmpty = "&cOtchlan jest pusta";
	
	public void load(){
		if(cfg.getInt("timmerDelay") != 0) this.timmerDelay = cfg.getInt("timmerDelay");
		if(cfg.getInt("openedTime") != 0) this.openedTime = cfg.getInt("openedTime");
		if(cfg.getString("worldName") != null) this.worldName = cfg.getString("worldName");
		if(cfg.getString("otchlanClosed") != null) this.otchlanClosed = cfg.getString("otchlanClosed");
		if(cfg.getString("isClosed") != null) this.isClosed = cfg.getString("isClosed");
		if(cfg.getString("isEmpty") != null) this.isEmpty = cfg.getString("isEmpty");
		if(cfg.getStringList("messages") != null) {
			for(String s : cfg.getStringList("messages")) {
				String[] split = s.split(":");
				messages.put(Integer.valueOf(split[0]), split[1]);
			}
		} else {
			messages.put(120, "&7Otchlan zostanie otwarta za &62min.");
			messages.put(60, "&7Otchlan zostanie otwarta za &61min.");
			messages.put(30, "&7Otchlan zostanie otwarta za &630sek.");
			messages.put(3, "&7Otchlan zostanie otwarta za &63sek.");
			messages.put(2, "&7Otchlan zostanie otwarta za &62sek.");
			messages.put(1, "&7Otchlan zostanie otwarta za &61sek.");
		}
	}
	//=========================================================================
	//Instance
	public static Config getInst(){
		if(inst == null) return new Config();
		return inst;
	}
	public Config(){
		inst = this;
	}
	//=========================================================================
	public int getTimmerDelay() {
		return timmerDelay;
	}
	public String getWorldName() {
		return worldName;
	}

}
