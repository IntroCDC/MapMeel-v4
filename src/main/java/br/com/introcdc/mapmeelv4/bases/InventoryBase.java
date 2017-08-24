package br.com.introcdc.mapmeelv4.bases;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import br.com.introcdc.mapmeelv4.MapUtils;

public class InventoryBase extends MapUtils {

    public static ArrayList<Inventory> inventories = new ArrayList<>();

    public static boolean canPutItem(Inventory inventory, ItemStack item) {
        return inventory.first(item) != -1 || inventory.firstEmpty() != -1;
    }

    public static void clearInventory(Player player) {
        player.getInventory().clear();
        ItemStack air = new ItemStack(Material.AIR);
        player.getInventory().setHelmet(air);
        player.getInventory().setChestplate(air);
        player.getInventory().setLeggings(air);
        player.getInventory().setBoots(air);
    }

    public static void fill(Inventory inventory, ItemStack item) {
        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) != null) {
                if (inventory.getItem(i).getType().equals(Material.AIR)) {
                    inventory.setItem(i, item);
                }
            } else {
                inventory.setItem(i, item);
            }
        }
    }

    public static boolean hasItem(Inventory inventory, ItemStack item, int amount) {
        if (amount <= 0) {
            return true;
        }
        int itemAmount = 0;
        for (ItemStack itemSelect : inventory.getContents()) {
            if (itemSelect != null && itemSelect.isSimilar(item)) {
                itemAmount += itemSelect.getAmount();
            }
        }
        return itemAmount >= amount;
    }

    public static boolean isFull(Inventory inventory) {
        return inventory.firstEmpty() == -1;
    }

    public static void removeItem(Inventory inventory, ItemStack item, int amount) {
        int toRemove = amount;
        for (int i = 0; i < inventory.getSize(); i++) {
            if (toRemove <= 0) {
                break;
            }
            ItemStack itemSelected = inventory.getItem(i);
            if (itemSelected == null) {
                continue;
            }
            if (itemSelected.isSimilar(item)) {
                if (itemSelected.getAmount() > toRemove) {
                    itemSelected.setAmount(itemSelected.getAmount() - toRemove);
                    break;
                } else {
                    toRemove -= itemSelected.getAmount();
                    inventory.clear(i);
                }
            }
        }
    }

}
