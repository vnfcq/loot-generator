package edu.grinnell.csc207.lootgenerator;

public class Monster {
    public final String name;
    public final String type;
    public final int level;
    public final String treasureClass;

    public Monster(String name, String type, int level, String treasureClass) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.treasureClass = treasureClass;
    }
}
