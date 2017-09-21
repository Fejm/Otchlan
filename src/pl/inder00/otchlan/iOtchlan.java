package pl.inder00.otchlan;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import pl.inder00.otchlan.commands.OtchlanCommand;
import pl.inder00.otchlan.data.Config;
import pl.inder00.otchlan.data.Manager;
import pl.inder00.otchlan.tasks.Timmer;

public class iOtchlan extends JavaPlugin {

	private static iOtchlan inst;
	private Timmer timmer;
	private Inventory inventory;
	private boolean opened = false;
	
	public static iOtchlan getInst() {
		return inst;
	}
	
	
	public void onEnable() {
		PluginDescriptionFile pdf = this.getDescription();
		inst = this;
		if(inst != null) System.out.println("["+pdf.getName()+" v"+pdf.getVersion()+"] Instance loaded."); 
		
		new Manager();
		new Config().load();
		new OtchlanCommand(this);
		
		timmer = new Timmer(Config.getInst().getTimmerDelay());
		timmer.runTaskTimer(this, 0, 20);
	}


	public Timmer getTimmer() {
		return timmer;
	}
	public Inventory getInventory() {
		return inventory;
	}
	
	public void a() {
		Config cfg = Config.getInst();
		inventory = Bukkit.createInventory(null, 54, "§6Otchlan");
		World world = Bukkit.getWorld(cfg.getWorldName());
		for(Entity s : world.getEntities()) {
			if(s instanceof Item) {
				inventory.addItem(new ItemStack[] { ((Item)s).getItemStack() });
				s.remove();
			}
		}
		opened = true;
		Bukkit.getScheduler().runTaskLater(this, new Runnable() {

			@Override
			public void run() {
				opened = false;
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', cfg.otchlanClosed));
				inventory.clear();
				inventory = null;
			}
			
		}, 20*cfg.openedTime);
	}


	public boolean isOpened() {
		return opened;
	}


	public void setOpened(boolean opened) {
		this.opened = opened;
	}
	

}
