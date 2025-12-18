package edu.grinnell.csc207.lootgenerator;

public class Affix {
    public final String name;
    public final String modCode;
    public final int min;
    public final int max;

    public Affix(String name, String modCode, int min, int max) {
        this.name = name;
        this.modCode = modCode;
        this.min = min;
        this.max = max;
    }
}
