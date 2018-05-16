package br.com.introcdc.mapmeelv4.coin;

import org.bukkit.Material;

public enum CoinType {
    X1(Material.WOOD,
       4,
       1),
    X2(Material.REDSTONE,
       0,
       2),
    X5(Material.WOOD,
       5,
       5);

    private byte bytes;
    private int coins;
    private Material material;

    CoinType(Material material, int bytes, int coins) {
        this.material = material;
        this.bytes = (byte) bytes;
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
