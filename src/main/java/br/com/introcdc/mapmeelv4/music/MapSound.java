package br.com.introcdc.mapmeelv4.music;
/*
 * Written by IntroCDC, Bruno Coêlho at 23/08/2017 - 17:07
 */

public enum MapSound {
    STOP("mapmeelv4.effect.stop", 1),
    EFFECT_COIN("mapmeelv4.effect.coin", 1),
    EFFECT_STAR("mapmeelv4.effect.star", 1),
    EFFECT_BAD_MESSAGE("mapmeelv4.effect.badmessage", 1),
    EFFECT_MESSAGE("mapmeelv4.effect.message", 1),
    EFFECT_OPEN_DOOR("mapmeelv4.effect.open_door", 1),
    EFFECT_COMPLETE("mapmeelv4.effect.complete", 1),
    EFFECT_WIN("mapmeelv4.effect.win", 1),
    EFFECT_GETSTAR("mapmeelv4.effect.getstar", 1),
    EFFECT_BIRD_ONE("mapmeelv4.effect.bird_one", 1),
    EFFECT_BIRD_TWO("mapmeelv4.effect.bird_two", 1),
    EFFECT_STARTING("mapmeelv4.effect.starting", 1),
    EFFECT_JOINING("mapmeelv4.effect.joining", 1),
    EFFECT_LETSGO("mapmeelv4.effect.lets_go", 1),

    MUSIC_THEME("mapmeelv4.music.theme", 207),
    CASTLE_MUSIC("mapmeelv4.music.castle_music", 117),
    MUSIC_ONE("mapmeelv4.music.music_one", 138),
    MUSIC_TWO("mapmeelv4.music.music_two", 165),
    MUSIC_THREE("mapmeelv4.music.music_three", 180),
    MUSIC_FOUR("mapmeelv4.music.music_four", 159),
    MUSIC_FIVE("mapmeelv4.music.music_five", 173),
    MUSIC_SIX("mapmeelv4.music.music_six", 180),
    MUSIC_SEVEN("mapmeelv4.music.music_seven", 220),
    MUSIC_EIGHT("mapmeelv4.music.music_eight", 140),
    MUSIC_NINE("mapmeelv4.music.music_nine", 59),
    MUSIC_TEN("mapmeelv4.music.music_ten", 137),
    MUSIC_ELEVEN("mapmeelv4.music.music_eleven", 130),
    MUSIC_TWELVE("mapmeelv4.music.music_twelve", 149),
    MUSIC_THIRTEEE("mapmeelv4.music.music_thirteen", 171),

    MUSIC_CUSTOM_DARK_FOREST("mapmeelv4.music.custom_dark_forest", 127),
    MUSIC_BAD("mapmeelv4.music.music_bad", 13),
    MUSIC_GOOD("mapmeelv4.music.music_good", 55),

    STARTING("mapmeelv4.music.starting", 1),
    MUSIC_END_ONE("mapmeelv4.music.end_one", 1),
    MUSIC_END_TWO("mapmeelv4.music.end_two", 1),
    MUSIC_END_THREE("mapmeelv4.music.end_three", 3),

    ;

    private String file;
    private int seconds;

    MapSound(String file, int seconds) {
        this.file = file;
        this.seconds = seconds;
    }

    public String getFile() {
        return file;
    }

    public int getSeconds() {
        return seconds;
    }

}
