package pl.inder00.otchlan.data;

import java.io.File;

import pl.inder00.otchlan.iOtchlan;

public class Manager {
	
	private static File mainDir = iOtchlan.getInst().getDataFolder();
	private static File cfgFile = new File(mainDir, "config.yml");
	
	public Manager(){
		if(!mainDir.exists()) mainDir.mkdir();
		if(!cfgFile.exists()) iOtchlan.getInst().saveDefaultConfig();
	}

}
