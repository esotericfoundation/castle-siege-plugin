package foundation.esoteric.minecraft.plugins.games.castlesiege.managers;

import lombok.Getter;
import foundation.esoteric.minecraft.plugins.games.castlesiege.game.Game;
import foundation.esoteric.minecraft.plugins.games.castlesiege.game.data.arena.ArenaConfig;
import org.bukkit.World;

import java.util.HashMap;
import java.util.UUID;

@Getter
public class GameManager {
    private final HashMap<UUID, Game> games = new HashMap<>();

    public Game createGame(World world, ArenaConfig arena) {
        games.put(world.getUID(), new Game(arena, world.getPlayers()));
        return games.get(world.getUID());
    }

    public boolean hasOngoingGame(World world) {
        return games.containsKey(world.getUID());
    }
}
