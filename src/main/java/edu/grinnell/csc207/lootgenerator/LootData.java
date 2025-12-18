package edu.grinnell.csc207.lootgenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * A loot data class.
 */
public class LootData {
    public final List<Monster> monsters;
    public final Map<String, String[]> treasures;
    public final Map<String, int[]> armors;
    public final List<Affix> prefixes;
    public final List<Affix> suffixes;

    /**
     * Constructs a new loot data object from the given data set.
     *
     * @param monsters a list of monster objects
     * @param treasures a map with a string key and a string array value
     * @param armors a map with a string key and an integer array value
     * @param prefixes a list of affix objects
     * @param suffixes a list of affix objects
     */
    public LootData(List<Monster> monsters,
                    Map<String, String[]> treasures,
                    Map<String, int[]> armors,
                    List<Affix> prefixes,
                    List<Affix> suffixes) {
        this.monsters = monsters;
        this.treasures = treasures;
        this.armors = armors;
        this.prefixes = prefixes;
        this.suffixes = suffixes;
    }

    /**
     * Reads the loot data from the given data set.
     *
     * @param dataDir a string
     * @return a loot data object
     * @throws IOException an exception
     */
    public static LootData read(String dataDir) throws IOException {
        List<Monster> monsters = parseMonsters(Path.of(dataDir, "monstats.txt"));
        Map<String, String[]> treasures = parseTreasures(Path.of(dataDir, "TreasureClassEx.txt"));
        Map<String, int[]> armors = parseArmors(Path.of(dataDir, "armor.txt"));
        List<Affix> prefixes = parseAffixes(Path.of(dataDir, "MagicPrefix.txt"));
        List<Affix> suffixes = parseAffixes(Path.of(dataDir, "MagicSuffix.txt"));
        return new LootData(monsters, treasures, armors, prefixes, suffixes);
    }

    private static List<Monster> parseMonsters(Path path) throws IOException {
        List<Monster> monsters = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                monsters.add(new Monster(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3]));
            }
        }

        return monsters;
    }

    private static Map<String, String[]> parseTreasures(Path path) throws IOException {
        Map<String, String[]> treasures = new HashMap<>();

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                treasures.put(parts[0], new String[] {parts[1], parts[2], parts[3]});
            }
        }

        return treasures;
    }

    private static Map<String, int[]> parseArmors(Path path) throws IOException {
        Map<String, int[]> armors = new HashMap<>();

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                armors.put(parts[0], new int[] {Integer.parseInt(parts[1]),
                                                Integer.parseInt(parts[2])});
            }
        }

        return armors;
    }

    private static List<Affix> parseAffixes(Path path) throws IOException {
        List<Affix> affixes = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                affixes.add(new Affix(
                        parts[0],
                        parts[1],
                        Integer.parseInt(parts[2]),
                        Integer.parseInt(parts[3])
                ));
            }
        }

        return affixes;
    }
}
