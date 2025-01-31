package foundation.esoteric.minecraft.plugins.games.castlesiege.game.classes.abs;

import foundation.esoteric.minecraft.plugins.games.castlesiege.game.Game;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public abstract class CustomisableGameClass extends GameClass {
    private final String className;

    public CustomisableGameClass(String className, Game game) {
        super(game);
        this.className = className.toLowerCase();
    }

    @Override
    public int getPlayerCount(int totalPlayerCount) {
        YamlConfiguration configuration = (YamlConfiguration) plugin.getConfig();

        ConfigurationSection classConfiguration = configuration.getConfigurationSection("classes." + className + ".max-players");
        assert classConfiguration != null;

        boolean isPercentage = classConfiguration.getBoolean("is-percentage");
        double value = classConfiguration.getDouble("value");

        return (int) (isPercentage ? Math.ceil((value / 100.0) * totalPlayerCount) : value);
    }
}
