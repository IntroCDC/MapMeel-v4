package br.com.introcdc.mapmeelv4.music;
/*
 * Written by IntroCDC, Bruno Coêlho at 23/08/2017 - 17:07
 */

public enum MapSound {
    STOP("mapmeelv4.effect.stop", 1),
    EFFECT_COIN("mapmeelv4.effect.coin", 1),
    EFFECT_STAR("mapmeelv4.effect.star", 1),
    EFFECT_COMPLETE("mapmeelv4.effect.complete", 1),
    EFFECT_GETSTAR("mapmeelv4.effect.getstar", 1),
    EFFECT_BIRD_ONE("mapmeelv4.effect.bird_one", 1),
    EFFECT_BIRD_TWO("mapmeelv4.effect.bird_two", 1),
    EFFECT_STARTING("mapmeelv4.effect.starting", 1),
    EFFECT_JOINING("mapmeelv4.effect.joining", 1),
    EFFECT_LETSGO("mapmeelv4.effect.lets_go", 1),
    CASTLE_MUSIC("mapmeelv4.music.castle_music", 2),
    STARTING("mapmeelv4.music.starting", 1),
    MUSIC_ONE("mapmeelv4.music.music_one", 2),
    MUSIC_TWO("mapmeelv4.music.music_two", 3),
    MUSIC_THREE("mapmeelv4.music.music_three", 3),
    MUSIC_FOUR("mapmeelv4.music.music_four", 3),
    MUSIC_FIVE("mapmeelv4.music.music_five", 3),
    MUSIC_SIX("mapmeelv4.music.music_six", 3),
    MUSIC_SEVEN("mapmeelv4.music.music_seven", 3),;

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
