package br.com.introcdc.mapmeelv4.bases;

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

    private final ItemStack item;

    public ItemBuilder(final ItemStack item) {
        this.item = item;
    }

    public ItemBuilder addCustonEffect(final PotionEffect effect) {
        if (!this.item.getType().equals(Material.POTION)) {
            return this;
        }
        final PotionMeta meta = (PotionMeta) this.item.getItemMeta();
        meta.addCustomEffect(effect, true);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder addEnchantment(final Enchantment ench, final int level) {
        this.item.addEnchantment(ench, level);
        return this;
    }

    public ItemBuilder setAmount(final int amount) {
        this.item.setAmount(amount);
        return this;
    }

    public ItemBuilder setBookAuthor(final String author) {
        if (!this.item.getType().toString().toUpperCase().contains("BOOK")) {
            return this;
        }
        final BookMeta meta = (BookMeta) this.item.getItemMeta();
        meta.setAuthor(author);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setBookTitle(final String title) {
        if (!this.item.getType().toString().toUpperCase().contains("BOOK")) {
            return this;
        }
        final BookMeta meta = (BookMeta) this.item.getItemMeta();
        meta.setTitle(title);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setData(final MaterialData data) {
        this.item.setData(data);
        return this;
    }

    public ItemBuilder setDurability(final short dur) {
        this.item.setDurability(dur);
        return this;
    }

    public ItemBuilder setItemMeta(final ItemMeta meta) {
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setLore(final ArrayList<String> lore) {
        final ItemMeta meta = this.item.getItemMeta();
        meta.setLore(lore);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setLore(final String[] lore) {
        final ItemMeta meta = this.item.getItemMeta();
        final ArrayList<String> Lore = new ArrayList<>();
        Collections.addAll(Lore, lore);
        meta.setLore(Lore);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setName(final String name) {
        final ItemMeta meta = this.item.getItemMeta();
        meta.setDisplayName(name);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setOwner(final String owner) {
        if (!this.item.getType().equals(Material.SKULL_ITEM)) {
            return this;
        }
        final SkullMeta meta = (SkullMeta) this.item.getItemMeta();
        meta.setOwner(owner);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setPagesBook(final String... pages) {
        if (!this.item.getType().toString().toUpperCase().contains("BOOK")) {
            return this;
        }
        final BookMeta meta = (BookMeta) this.item.getItemMeta();
        meta.setPages(pages);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setType(final Material mat) {
        this.item.setType(mat);
        return this;
    }

    public ItemStack toItem() {
        return this.item;
    }

}
