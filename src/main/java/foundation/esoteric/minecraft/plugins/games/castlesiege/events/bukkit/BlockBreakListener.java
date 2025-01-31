package foundation.esoteric.minecraft.plugins.games.castlesiege.events.bukkit;

import foundation.esoteric.minecraft.plugins.games.castlesiege.events.base.AbstractEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener extends AbstractEvent {
    public BlockBreakListener() {
        super();
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (plugin.getArenaManager().getArenas().containsKey(event.getBlock().getWorld().getUID())) {
            event.setCancelled(true);
        }
    }
}
