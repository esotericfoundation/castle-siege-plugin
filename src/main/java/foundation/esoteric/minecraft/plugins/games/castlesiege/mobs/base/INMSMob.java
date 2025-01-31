package foundation.esoteric.minecraft.plugins.games.castlesiege.mobs.base;

import net.minecraft.world.level.Level;
import foundation.esoteric.minecraft.plugins.games.castlesiege.game.Game;
import foundation.esoteric.minecraft.plugins.games.castlesiege.game.data.region.MapRegion;
import foundation.esoteric.minecraft.plugins.games.castlesiege.mobs.MobType;
import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;

public interface INMSMob extends Listener {
    INMSMob newInstance(Level world, MapRegion region, Game game);
    void prepare();
    boolean isPrepared();
    void spawn();
    void registerEvents();
    Entity getHandle();
    MapRegion getHomeRegion();
    Integer getWeight();
    MobType getType();
}
