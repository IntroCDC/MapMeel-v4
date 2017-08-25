package br.com.introcdc.mapmeelv4.enums;
/*
 * Writter by IntroCDC, Bruno Coêlho at 23/08/2017 - 17:07
 */

public enum MapSound {
    STOP("mapmeelv4.effect.null", 1),
    EFFECT_COIN("mapmeelv4.effect.coin", 1),
    STARTING("mapmeelv4.music.starting", 1),
    MUSIC_ONE("mapmeelv4.music.music_one", 2);

    private String file;
    private int minutes;

    MapSound(String file, int minutes) {
        this.file = file;
        this.minutes = minutes;
    }

    public String getFile() {
        return file;
    }

    public int getMinutes() {
        return minutes;
    }
}
