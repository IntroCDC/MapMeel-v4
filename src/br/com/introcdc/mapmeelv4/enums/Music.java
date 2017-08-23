package br.com.introcdc.mapmeelv4.enums;
/*
 * Writter by IntroCDC, Bruno Coêlho at 23/08/2017 - 17:07
 */

public enum Music {
    STARTING("mapmeelv4.music.starting");

    private String file;

    Music(String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
    }
}
