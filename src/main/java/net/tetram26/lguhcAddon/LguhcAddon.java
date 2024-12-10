package net.tetram26.lguhcAddon;

import fr.ph1lou.werewolfapi.GetWereWolfAPI;
import net.tetram26.lguhcAddon.listeners.JoiningListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class LguhcAddon extends JavaPlugin {
    public static GetWereWolfAPI ww;
    @Override
    public void onEnable() {
        // Plugin startup logic
        GetWereWolfAPI ww = getServer().getServicesManager().load(GetWereWolfAPI.class);
        getServer().getPluginManager().registerEvents(new JoiningListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
