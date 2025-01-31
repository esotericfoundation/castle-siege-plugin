package foundation.esoteric.minecraft.plugins.games.castlesiege.deserializer.region;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import foundation.esoteric.minecraft.plugins.games.castlesiege.game.data.region.StructureRegion;
import foundation.esoteric.minecraft.plugins.games.castlesiege.deserializer.region.base.AbstractRegionDeserializer;
import org.bukkit.Location;

import java.lang.reflect.Type;

public class StructureRegionDeserializer extends AbstractRegionDeserializer<StructureRegion> {
    @Override
    public StructureRegion deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        return new StructureRegion()
            .setCorner1(context.deserialize(jsonObject.get("corner_1"), Location.class))
            .setCorner2(context.deserialize(jsonObject.get("corner_2"), Location.class))
            .setIgnoreY(jsonObject.get("ignore_y").getAsBoolean());
    }
}
