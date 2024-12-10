package net.tetram26.lguhcAddon;

import fr.ph1lou.werewolfapi.GetWereWolfAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class LguhcAddon extends JavaPlugin {

    public static GetWereWolfAPI ww = getInstance().getServer().getServicesManager().load(GetWereWolfAPI.class);

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getCommand("randomtp").setExecutor(new RtpCommand());
        getCommand("lguhcAddon").setExecutor(this);
        getCommand("randomtp").setTabCompleter(new RtpCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static LguhcAddon getInstance(){
        return getPlugin(LguhcAddon.class);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        reloadConfig();
        return true;
    }
}
