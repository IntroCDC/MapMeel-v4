package br.com.introcdc.mapmeelv4.enums;
/*
 * Writter by IntroCDC, Bruno Co�lho at 23/08/2017 - 17:07
 */

public enum Music {
    STOP("mapmeelv4.effect.null", 1),
    STARTING("mapmeelv4.music.starting", 1),
    MUSIC_ONE("mapmeelv4.music.music_one", 2);

    private String file;
    private int minutes;

    Music(String file, int minutes) {
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
