package br.com.introcdc.mapmeelv4.enums;

public enum Nivel {
    L_2A("2A");

    private boolean cleared;
    private String name;

    Nivel(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public boolean isCleared() {
        return this.cleared;
    }

    public void setCleared(final boolean cleared) {
        this.cleared = cleared;
    }

}
