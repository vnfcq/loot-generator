package edu.grinnell.csc207.lootgenerator;

/**
 * An affix class.
 */
public class Affix {
    public final String name;
    public final String modCode;
    public final int min;
    public final int max;

    /**
     * Constructs a new Affix object from MagicPrefix.txt and MagicSuffix.txt.
     *
     * @param name a string
     * @param modCode a string
     * @param min an integer
     * @param max an integer
     */
    public Affix(String name, String modCode, int min, int max) {
        this.name = name;
        this.modCode = modCode;
        this.min = min;
        this.max = max;
    }
}
