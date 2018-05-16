package br.com.introcdc.mapmeelv4.block;
/*
 * Written by IntroCDC, Bruno Coêlho at 23/08/2017 - 08:13
 */

import org.bukkit.Material;

public class BlockId {

    private Material material;
    private byte data;

    public BlockId(Material material) {
        this.material = material;
        this.data = 0;
    }

    public BlockId(Material material, int data) {
        this.material = material;
        this.data = (byte) data;
    }

    public Material getMaterial() {
        return material;
    }

    public byte getData() {
        return data;
    }
}
