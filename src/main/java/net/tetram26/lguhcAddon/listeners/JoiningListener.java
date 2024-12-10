package net.tetram26.lguhcAddon.listeners;

import fr.ph1lou.werewolfapi.enums.StateGame;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


import static net.tetram26.lguhcAddon.LguhcAddon.ww;

public class JoiningListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onClientConnected(PlayerJoinEvent event){
        if (ww.getWereWolfAPI().isState(StateGame.GAME)) {
            event.getPlayer().setGameMode(GameMode.SPECTATOR);
        }
    }
}
