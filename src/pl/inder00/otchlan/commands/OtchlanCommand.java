package pl.inder00.otchlan.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import pl.inder00.otchlan.iOtchlan;
import pl.inder00.otchlan.data.Config;
import pl.inder00.otchlan.utils.Util;

public class OtchlanCommand implements CommandExecutor {
	
	public OtchlanCommand(iOtchlan api) {
		api.getCommand("otchlan").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) return false;
		Config cfg = Config.getInst();
		Player p = (Player) sender;
		iOtchlan api = iOtchlan.getInst();
		if(!api.isOpened()) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', cfg.isClosed));
			return false;
		}
		if(Util.isEmpty(api.getInventory())) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', cfg.isEmpty));
			return false;
		}
		p.openInventory(api.getInventory());
		return false;
	}

}
