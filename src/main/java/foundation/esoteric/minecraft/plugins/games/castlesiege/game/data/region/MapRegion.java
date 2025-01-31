package foundation.esoteric.minecraft.plugins.games.castlesiege.game.data.region;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import foundation.esoteric.minecraft.plugins.games.castlesiege.mobs.data.MobEnvironment;

@Getter @Setter @Accessors(chain = true)
public class MapRegion extends StructureRegion {
    private MobEnvironment environment;
    private int mobCap;
    private int totalMobs;
    private double mobSpawnRate;

    public MapRegion() {
        super();
        this.totalMobs = 0;
    }

    public MapRegion(MobEnvironment environment) {
        super();
        this.environment = environment;
    }

    public void incrementTotalMobs() {
        totalMobs++;
    }

    public void decrementTotalMobs() {
        totalMobs--;
    }
}
