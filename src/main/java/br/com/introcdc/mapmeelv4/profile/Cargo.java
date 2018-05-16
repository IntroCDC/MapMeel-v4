package br.com.introcdc.mapmeelv4.profile;

public enum Cargo {
    ADMIN,
    CONVIDADO,
    MEEL;
    public boolean isStaff() {
        return this.equals(ADMIN);
    }
}
