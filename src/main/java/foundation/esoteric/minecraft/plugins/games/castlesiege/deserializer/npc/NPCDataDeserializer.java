package foundation.esoteric.minecraft.plugins.games.castlesiege.deserializer.npc;

import com.google.gson.*;
import foundation.esoteric.minecraft.plugins.games.castlesiege.game.data.npc.NPCData;
import foundation.esoteric.minecraft.plugins.games.castlesiege.game.data.npc.NPCType;
import org.bukkit.Location;

import java.lang.reflect.Type;

public final class NPCDataDeserializer implements JsonDeserializer<NPCData> {
    @Override
    public NPCData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        return new NPCData()
            .setType(NPCType.fromString(object.get("type").getAsString()))
            .setLocation(context.deserialize(object.get("location"), Location.class))
            .setName(object.get("name").getAsString())
            .setSkinValue(object.get("skin_value").getAsString())
            .setSkinSignature(object.get("skin_signature").getAsString());
    }
}
