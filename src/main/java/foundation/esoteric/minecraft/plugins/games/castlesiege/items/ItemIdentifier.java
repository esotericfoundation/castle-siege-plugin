package foundation.esoteric.minecraft.plugins.games.castlesiege.items;

import lombok.Getter;

@Getter
public enum ItemIdentifier {
    LONGBOW("longbow"),
    RECURVE_BOW("recurve_bow");

    private final String asString;
    ItemIdentifier(String identifier) {
        this.asString = identifier;
    }
}
