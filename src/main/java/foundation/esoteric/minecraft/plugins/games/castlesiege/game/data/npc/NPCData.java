package foundation.esoteric.minecraft.plugins.games.castlesiege.game.data.npc;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.bukkit.Location;

@Getter @Setter @Accessors(chain = true)
public class NPCData {
    private NPCType type;
    private Location location;
    private String name;
    private String skinValue;
    private String skinSignature;
}
