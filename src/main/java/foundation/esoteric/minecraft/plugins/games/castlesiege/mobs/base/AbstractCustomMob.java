package foundation.esoteric.minecraft.plugins.games.castlesiege.mobs.base;

import net.minecraft.world.level.Level;
import foundation.esoteric.minecraft.plugins.games.castlesiege.CastleSiegePlugin;
import foundation.esoteric.minecraft.plugins.games.castlesiege.game.Game;
import foundation.esoteric.minecraft.plugins.games.castlesiege.game.data.region.MapRegion;
import foundation.esoteric.minecraft.plugins.games.castlesiege.managers.PersistentDataManager;
import foundation.esoteric.minecraft.plugins.games.castlesiege.mobs.MobType;
import org.bukkit.entity.Entity;

public abstract class AbstractCustomMob implements INMSMob {
    protected final CastleSiegePlugin plugin;
    protected final PersistentDataManager pdcManager;

    protected final Game game;
    protected final MapRegion homeRegion;

    protected MobType mobType;
    protected Integer weight;

    protected boolean readyForSpawn;

    public AbstractCustomMob(MapRegion region, Game game) {
        this.plugin = CastleSiegePlugin.instance;
        this.pdcManager = plugin.getPdcManager();

        this.game = game;
        this.homeRegion = region;
    }
    public void createMobData(MobType type, Integer weight) {
        this.mobType = type;
        this.weight = weight;
    }

    @Override
    public abstract INMSMob newInstance(Level world, MapRegion region, Game game);
    @Override
    public abstract void registerEvents();
    @Override
    public abstract void prepare();
    @Override
    public abstract void spawn();

    @Override
    public boolean isPrepared() { return readyForSpawn; }

    @Override
    public abstract Entity getHandle();

    @Override
    public MapRegion getHomeRegion() {
        return homeRegion;
    }

    @Override
    public Integer getWeight() {
        return weight;
    }

    @Override
    public MobType getType() {
        return mobType;
    }
}
