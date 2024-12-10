package net.tetram26.lguhcAddon;

import fr.ph1lou.werewolfapi.GetWereWolfAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import net.tetram26.lguhcAddon.listeners.JoiningListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class LguhcAddon extends JavaPlugin {

    public static GetWereWolfAPI ww;

    @Override
    public void onEnable() {
        ww = getServer().getServicesManager().load(GetWereWolfAPI.class);
        saveDefaultConfig();
        this.getCommand("rtpcommand").setExecutor(new RtpCommand());
        this.getCommand("lguhcAddon").setExecutor(this);
        this.getCommand("rtpcommand").setTabCompleter(new RtpCommand());
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new JoiningListener(), this);
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
