package pl.inder00.otchlan.utils;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Util {
	
	public static boolean isEmpty(Inventory s) {
		for(ItemStack it : s.getContents())
		{
		    if(it != null) return false;
		}
		return true;
	}

}
