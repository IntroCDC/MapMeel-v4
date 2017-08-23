package br.com.introcdc.mapmeelv4.enums;

public enum Cargo {
    ADMIN,
    CONVIDADO,
    MEEL;
    public boolean isStaff() {
        return this.equals(ADMIN);
    }
}
