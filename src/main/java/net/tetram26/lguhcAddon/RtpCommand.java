package net.tetram26.lguhcAddon;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

/**
 * Classe de la commande "/rtp"
 */
public class RtpCommand implements CommandExecutor, TabCompleter {

    Random random = new Random();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        //Si le pseudo du joueur n'est pas donné / Si il y a trop d'arguments (ne marche si le joueur à un compte bugg)
        if (args.length != 1) {
            commandSender.sendMessage("§c/rtp <player>");
            return true;
        }
        // Récupération du joueur visé
        Player player = Bukkit.getPlayer(args[0]);

        //Si le pseudo du joueur n'est pas bon
        if(player == null || !player.isOnline()) {
            if (LguhcAddon.getInstance().getConfig().get("Erreurs.PlayerIsNull") != null) {
                commandSender.sendMessage(LguhcAddon.getInstance().getConfig().getString("Erreurs.PlayerIsNull"));
            }
            else {
                commandSender.sendMessage("<color:red>Le joueur ciblé n'est pas connecté ou n'existe pas.");
            }
            return true;
        }
        else {
            // Récupération du monde du LG UHC.
            World world = LguhcAddon.ww.getWereWolfAPI().getMapManager().getWorld();
            // Récupération de la taille de bordure du monde
            int wbSize = (int) world.getWorldBorder().getSize();
            // Récupération des coordonnées du centre de la worldborder
            Location WbCenter = world.getWorldBorder().getCenter();
            int centerX = (int) WbCenter.x();
            int centerZ = (int) WbCenter.z();
            // Détermination de coordonnées pour la téléportation
            int x = random.nextInt(-wbSize/2, wbSize/2);
            int z = random.nextInt(-wbSize/2, wbSize/2);
            Location location = new Location(world, x+centerX, world.getHighestBlockYAt(x+centerX, z+centerZ)+2, z+centerZ);
            // Téléportation du joueur
            player.teleport(location);

            if (LguhcAddon.getInstance().getConfig().get("messageToPlayer") != null){
                player.sendMessage(LguhcAddon.getInstance().getConfig().getRichMessage("messageToPlayer"));
            }
            else {
                player.sendMessage(MiniMessage.miniMessage().deserialize("<color:red>Vous avez été téléporté(e) aléatoirement sur la carte par un opérateur."));
            }

        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return LguhcAddon.getInstance().getServer().getOnlinePlayers().stream()
                .map(Player::getName)
                .toList();
    }
}
