package foundation.esoteric.minecraft.plugins.games.castlesiege.game.data;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;

public class CastleSiegeTeam {
    private final static Random random = new Random();

    private List<Player> players;

    public List<Player> getPlayers() {
        return players;
    }

    private CastleSiegeTeamBase teamBase;

    public CastleSiegeTeam(CastleSiegeTeamBase teamBase) {
        this.teamBase = teamBase;
    }

    public void spawnPlayers() {
        List<Location> spawnPoints = teamBase.getSpawnPoints();

        for (Player player : players) {
            player.teleport(spawnPoints.get(random.nextInt(spawnPoints.size())));
        }
    }
}
