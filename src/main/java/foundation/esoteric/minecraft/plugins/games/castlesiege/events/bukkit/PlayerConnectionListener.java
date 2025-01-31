package foundation.esoteric.minecraft.plugins.games.castlesiege.events.bukkit;

import foundation.esoteric.minecraft.plugins.games.castlesiege.events.base.AbstractEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public final class PlayerConnectionListener extends AbstractEvent {
    public PlayerConnectionListener() {
        super();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        plugin.getPacketInterceptor().addPlayer(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        plugin.getPacketInterceptor().removePlayer(event.getPlayer());
    }
}
