package foundation.esoteric.minecraft.plugins.games.castlesiege.events.base;

import foundation.esoteric.minecraft.plugins.games.castlesiege.CastleSiegePlugin;
import org.bukkit.event.Listener;

public class AbstractEvent implements Listener {
    protected final CastleSiegePlugin plugin;

    public AbstractEvent() {
        this.plugin = CastleSiegePlugin.instance;
    }

    public void register() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
}
