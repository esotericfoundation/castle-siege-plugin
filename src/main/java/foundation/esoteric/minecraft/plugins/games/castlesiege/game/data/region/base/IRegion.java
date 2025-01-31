package foundation.esoteric.minecraft.plugins.games.castlesiege.game.data.region.base;

import foundation.esoteric.minecraft.plugins.games.castlesiege.util.region.Cuboid;
import org.bukkit.World;

public interface IRegion {
    Cuboid asCuboid(World world);
}
