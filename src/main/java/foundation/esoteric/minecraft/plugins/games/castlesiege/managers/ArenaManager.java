package foundation.esoteric.minecraft.plugins.games.castlesiege.managers;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import net.kyori.adventure.text.format.NamedTextColor;
import foundation.esoteric.minecraft.plugins.games.castlesiege.CastleSiegePlugin;
import foundation.esoteric.minecraft.plugins.games.castlesiege.deserializer.npc.NPCDataDeserializer;
import foundation.esoteric.minecraft.plugins.games.castlesiege.deserializer.other.ColourDeserializer;
import foundation.esoteric.minecraft.plugins.games.castlesiege.deserializer.other.LocationDeserializer;
import foundation.esoteric.minecraft.plugins.games.castlesiege.deserializer.other.MaterialDeserializer;
import foundation.esoteric.minecraft.plugins.games.castlesiege.deserializer.region.GateRegionDeserializer;
import foundation.esoteric.minecraft.plugins.games.castlesiege.deserializer.region.MapRegionDeserializer;
import foundation.esoteric.minecraft.plugins.games.castlesiege.deserializer.region.StructureRegionDeserializer;
import foundation.esoteric.minecraft.plugins.games.castlesiege.game.data.arena.ArenaConfig;
import foundation.esoteric.minecraft.plugins.games.castlesiege.game.data.npc.NPCData;
import foundation.esoteric.minecraft.plugins.games.castlesiege.game.data.region.GateRegion;
import foundation.esoteric.minecraft.plugins.games.castlesiege.game.data.region.MapRegion;
import foundation.esoteric.minecraft.plugins.games.castlesiege.game.data.region.StructureRegion;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class ArenaManager {
    private final CastleSiegePlugin plugin;

    @Getter
    private final Map<UUID, ArenaConfig> arenas;

    public ArenaManager() {
        this.plugin = CastleSiegePlugin.instance;
        this.arenas = new HashMap<>();
    }

    public void loadFromConfig() {
        File castleSiegeArenaFile = new File(plugin.getDataFolder(), "castle-siege-arenas.json");

        try {
            Reader reader = new FileReader(castleSiegeArenaFile);
            Gson gson = createGson();

            for (ArenaConfig arena : gson.fromJson(reader, ArenaConfig[].class)) {
                arenas.put(getWorldUID(arena.worldName()), arena);
            }

            reader.close();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private Gson createGson() {
        return new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(StructureRegion.class, new StructureRegionDeserializer())
            .registerTypeAdapter(MapRegion.class, new MapRegionDeserializer())
            .registerTypeAdapter(GateRegion.class, new GateRegionDeserializer())
            .registerTypeAdapter(NamedTextColor.class, new ColourDeserializer())
            .registerTypeAdapter(Location.class, new LocationDeserializer())
            .registerTypeAdapter(NPCData.class, new NPCDataDeserializer())
            .registerTypeAdapter(Material.class, new MaterialDeserializer())
            .create();
    }

    private UUID getWorldUID(String worldName) {
        World world = Bukkit.getWorld(worldName);

        if (world == null) plugin.getLogger().warning(
            "The world name provided does not match any existing world. Please double-check castle-siege-arenas.json.");

        assert world != null;
        return world.getUID();
    }
}
