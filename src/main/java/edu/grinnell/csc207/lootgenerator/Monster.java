package edu.grinnell.csc207.lootgenerator;

/**
 * A monster class.
 */
public class Monster {
    public final String name;
    public final String type;
    public final int level;
    public final String treasureClass;

    /**
     * Constructs a new Monster object from monstats.txt.
     *
     * @param name a string
     * @param type a string
     * @param level an integer
     * @param treasureClass a string
     */
    public Monster(String name, String type, int level, String treasureClass) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.treasureClass = treasureClass;
    }
}
