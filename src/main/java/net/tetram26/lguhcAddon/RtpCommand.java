package net.tetram26.lguhcAddon;

import net.kyori.adventure.text.Component;
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

public class
RtpCommand implements CommandExecutor, TabCompleter {

    Random random = new Random();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        //Si le pseudo du joueur n'est pas donné / Si il y a trop d'arg (ne marche si le joueur à un compte bugg)
        if (args.length != 1) {
            commandSender.sendMessage("§c/rtp player>");
            return true;
        }

        Player player = Bukkit.getPlayer(args[0]);

        //Si le pseudo du joueur n'est pas bon
        if(player == null || !player.isOnline()) {
            commandSender.sendMessage("§cCe joueur n'existe pas.");
            return true;
        }
        else {
            World world = LguhcAddon.ww.getWereWolfAPI().getMapManager().getWorld();

            int wbSize = (int) world.getWorldBorder().getSize();
            int x = random.nextInt(-wbSize, wbSize);
            int z = random.nextInt(-wbSize, wbSize);

            Location location = new Location(world, x, world.getHighestBlockYAt(x, z), z);
            player.teleport(location);

            player.sendMessage(MiniMessage.miniMessage().deserialize(LguhcAddon.getInstance().getConfig().getString("messageToPlayer")));
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
