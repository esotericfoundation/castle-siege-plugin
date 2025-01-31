package foundation.esoteric.minecraft.plugins.games.castlesiege.game.classes.abs;

import foundation.esoteric.minecraft.plugins.games.castlesiege.CastleSiegePlugin;
import foundation.esoteric.minecraft.plugins.games.castlesiege.game.Game;
import org.bukkit.entity.Player;

public abstract class GameClass {
    protected final CastleSiegePlugin plugin;
    protected final Game game;

    public GameClass(Game game) {
        this.plugin = CastleSiegePlugin.instance;
        this.game = game;
    }

    public int getPlayerCount(int totalPlayerCount) {
        return 0;
    }

    public void equipPlayer(Player player) {
        player.getInventory().clear();
    }

    public abstract void sendInfo(Player player);
    public abstract GameClass newInstance();

}
