package br.com.introcdc.mapmeelv4.item;

import java.util.ArrayList;
import java.util.Collections;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.PotionEffect;

public class ItemBuilder {

    private ItemStack item;

    public ItemBuilder(ItemStack item) {
        this.item = item;
    }

    public ItemBuilder addCustonEffect(PotionEffect effect) {
        if (!this.item.getType().equals(Material.POTION)) {
            return this;
        }
        PotionMeta meta = (PotionMeta) this.item.getItemMeta();
        meta.addCustomEffect(effect, true);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment ench, int level) {
        this.item.addEnchantment(ench, level);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        this.item.setAmount(amount);
        return this;
    }

    public ItemBuilder setBookAuthor(String author) {
        if (!this.item.getType().toString().toUpperCase().contains("BOOK")) {
            return this;
        }
        BookMeta meta = (BookMeta) this.item.getItemMeta();
        meta.setAuthor(author);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setBookTitle(String title) {
        if (!this.item.getType().toString().toUpperCase().contains("BOOK")) {
            return this;
        }
        BookMeta meta = (BookMeta) this.item.getItemMeta();
        meta.setTitle(title);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setData(MaterialData data) {
        this.item.setData(data);
        return this;
    }

    public ItemBuilder setDurability(short dur) {
        this.item.setDurability(dur);
        return this;
    }

    public ItemBuilder setItemMeta(ItemMeta meta) {
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setLore(ArrayList<String> lore) {
        ItemMeta meta = this.item.getItemMeta();
        meta.setLore(lore);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setLore(String[] lore) {
        ItemMeta meta = this.item.getItemMeta();
        ArrayList<String> Lore = new ArrayList<>();
        Collections.addAll(Lore, lore);
        meta.setLore(Lore);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setName(String name) {
        ItemMeta meta = this.item.getItemMeta();
        meta.setDisplayName(name);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setOwner(String owner) {
        if (!this.item.getType().equals(Material.PLAYER_HEAD)) {
            return this;
        }
        SkullMeta meta = (SkullMeta) this.item.getItemMeta();
        meta.setOwner(owner);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setPagesBook(String... pages) {
        if (!this.item.getType().toString().toUpperCase().contains("BOOK")) {
            return this;
        }
        BookMeta meta = (BookMeta) this.item.getItemMeta();
        meta.setPages(pages);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setType(Material mat) {
        this.item.setType(mat);
        return this;
    }

    public ItemStack toItem() {
        return this.item;
    }

}
