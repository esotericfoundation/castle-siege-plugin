package foundation.esoteric.minecraft.plugins.games.castlesiege;

import lombok.Getter;
import foundation.esoteric.minecraft.plugins.games.castlesiege.commands.DebugCommand;
import foundation.esoteric.minecraft.plugins.games.castlesiege.commands.EndGameCommand;
import foundation.esoteric.minecraft.plugins.games.castlesiege.commands.StartGameCommand;
import foundation.esoteric.minecraft.plugins.games.castlesiege.events.bukkit.AsyncChatListener;
import foundation.esoteric.minecraft.plugins.games.castlesiege.events.bukkit.PlayerConnectionListener;
import foundation.esoteric.minecraft.plugins.games.castlesiege.events.packet.PlayerInteractPacketEvent;
import foundation.esoteric.minecraft.plugins.games.castlesiege.game.Game;
import foundation.esoteric.minecraft.plugins.games.castlesiege.managers.ArenaManager;
import foundation.esoteric.minecraft.plugins.games.castlesiege.managers.GameManager;
import foundation.esoteric.minecraft.plugins.games.castlesiege.managers.PersistentDataManager;
import foundation.esoteric.minecraft.plugins.games.castlesiege.packets.PacketInterceptor;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public final class CastleSiegePlugin extends JavaPlugin implements Listener {
    public static CastleSiegePlugin instance;

    private ArenaManager arenaManager;
    private GameManager gameManager;
    private PersistentDataManager pdcManager;
    private PacketInterceptor packetInterceptor;

    public CastleSiegePlugin() {
        CastleSiegePlugin.instance = this;
    }

    @Override
    public void onLoad() {
        loadResources();

        super.onLoad();
    }

    @Override
    public void onEnable() {
        initGameManager();
        initPersistentDataManager();
        initPacketInterceptor();

        registerEvents();
        registerCommands();

        super.onEnable();
    }

    @Override
    public void onDisable() {
        //Avoid concurrent modification
        Map<UUID, Game> games = gameManager.getGames();
        Map<UUID, Game> copy = games.entrySet().stream()
            .filter(entry -> entry.getValue().isLoaded())
            .collect(HashMap::new, (map, entry) -> map.put(entry.getKey(), entry.getValue()), Map::putAll);

        copy.forEach((id, game) -> games.get(id).endGame(
            (world) -> getLogger().info(
                "[Disabling Logic] Automatically ended Castle Siege game in world: " + world.getName())));

        super.onDisable();
    }

    @EventHandler
    public void onServerLoaded(ServerLoadEvent event) {
        initArenaManager();
    }

    private void loadResources() {
        if (!getDataFolder().exists()) {
            boolean success = getDataFolder().mkdir();
            getLogger().info("Data folder created: " + success);
        }

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        saveResource("castle-siege-arenas.json", true);
    }

    private void initGameManager() {
        this.gameManager = new GameManager();
    }

    private void initPersistentDataManager() {
        this.pdcManager = new PersistentDataManager();
    }

    private void initPacketInterceptor() {
        this.packetInterceptor = new PacketInterceptor();
    }

    private void initArenaManager() {
        this.arenaManager = new ArenaManager();
        arenaManager.loadFromConfig();
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(this, this);

        new PlayerConnectionListener().register();
        new AsyncChatListener().register();

        new PlayerInteractPacketEvent().register();
    }

    private void registerCommands() {
        getNotNullCommand("start").setExecutor(new StartGameCommand());
        getNotNullCommand("end").setExecutor(new EndGameCommand());
        getNotNullCommand("debug").setExecutor(new DebugCommand());
    }

    private PluginCommand getNotNullCommand(String name) {
        if (getCommand(name) == null)
            throw new IllegalArgumentException("Command not found: " + name);

        return getCommand(name);
    }

}
