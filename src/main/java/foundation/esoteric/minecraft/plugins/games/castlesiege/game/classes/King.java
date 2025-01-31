package foundation.esoteric.minecraft.plugins.games.castlesiege.game.classes;

import foundation.esoteric.minecraft.plugins.games.castlesiege.game.Game;
import foundation.esoteric.minecraft.plugins.games.castlesiege.game.classes.abs.GameClass;
import foundation.esoteric.minecraft.plugins.games.castlesiege.items.ItemIdentifier;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public final class King extends GameClass {
    public King(Game game) {
        super(game);
    }

    @Override
    public int getPlayerCount(int totalPlayerCount) {
        return 1;
    }

    @Override
    public void equipPlayer(Player player) {
        super.equipPlayer(player);

        Inventory inventory = player.getInventory();
        inventory.setItem(0, new ItemStack(Material.STONE_SWORD));
        inventory.setItem(1, game.getItemManager().getCustomItemStack(ItemIdentifier.LONGBOW));
        inventory.setItem(7, new ItemStack(Material.COOKED_BEEF, 12));
        inventory.setItem(9, new ItemStack(Material.ARROW, 12));

        EntityEquipment equipment = player.getEquipment();
        equipment.setHelmet(new ItemStack(Material.LEATHER_HELMET));
        equipment.setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
        equipment.setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
        equipment.setBoots(new ItemStack(Material.LEATHER_BOOTS));
    }

    @Override
    public void sendInfo(Player player) {
        player.sendMessage("You are the king!");
    }

    @Override
    public King newInstance() {
        return new King(game);
    }
}
