package net.tetram26.lguhcAddon;

import fr.ph1lou.werewolfapi.GetWereWolfAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * Classe principale du plugin
 */
public final class LguhcAddon extends JavaPlugin {

    public static GetWereWolfAPI ww;

    @Override
    public void onEnable() {
        // Récupération de l'api du plugin de lg UHC
        ww = getServer().getServicesManager().load(GetWereWolfAPI.class);
        saveDefaultConfig();
        // Enregistrement des commandes
        this.getCommand("rtpcommand").setExecutor(new RtpCommand());
        this.getCommand("lguhcAddon").setExecutor(this);
        // Enregistrement de la complétion pour la commande
        this.getCommand("rtpcommand").setTabCompleter(new RtpCommand());

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
