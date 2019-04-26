package br.com.introcdc.mapmeelv4.coin;

import org.bukkit.Material;

public enum CoinType {
    X1(Material.ACACIA_PLANKS, 1),
    X2(Material.REDSTONE, 2),
    X5(Material.GOLD_INGOT, 5);

    private byte bytes;
    private int coins;
    private Material material;

    CoinType(Material material, int coins) {
        this.material = material;
        this.coins = coins;
    }

    public byte getBytes() {
        return this.bytes;
    }

    public int getCoins() {
        return this.coins;
    }

    public Material getMaterial() {
        return this.material;
    }

}
