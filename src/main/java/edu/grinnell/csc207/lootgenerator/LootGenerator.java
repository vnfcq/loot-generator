package edu.grinnell.csc207.lootgenerator;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * The main loot generator class.
 */
public class LootGenerator {
    /** The path to the dataset (either the small or large set). */
    private static final String DATA_SET = "data/large";

    private static class GenAffix {
        String name;
        String stats;

        GenAffix(String name, String stats) {
            this.name = name;
            this.stats = stats;
        }
    }

    /**
     * Runs the main function.
     *
     * @param args an array of strings
     * @throws IOException an exception
     */
    public static void main(String[] args) throws IOException {
        LootData data = LootData.read(DATA_SET);
        Random rng = new Random();
        Scanner sc = new Scanner(System.in);

        System.out.println("This program kills monsters and generates loot!");

        while (true) {
            Monster m = pickMonster(data, rng);

            System.out.printf("Fighting %s...%n", m.name);
            System.out.printf("You have slain %s!%n", m.name);
            System.out.printf("%s dropped:%n%n", m.name);

            String baseItem = generateBaseItem(m.treasureClass, data, rng);
            String baseStats = generateBaseStats(baseItem, data, rng);

            GenAffix prefix = generateAffix(data.prefixes, rng);
            GenAffix suffix = generateAffix(data.suffixes, rng);

            StringBuilder sb = new StringBuilder();
            if (prefix != null) {
                sb.append(prefix.name).append(" ");
            }
            sb.append(baseItem);
            if (suffix != null) {
                sb.append(" ").append(suffix.name);
            }
            System.out.println(sb.toString());
            System.out.println(baseStats);
            if (prefix != null) {
                System.out.println(prefix.stats);
            }
            if (suffix != null) {
                System.out.println(suffix.stats);
            }
            System.out.println();

            while (true) {
                System.out.print("Fight again [y/n]? ");
                String s = sc.nextLine();
                System.out.println(s);

                if (s.equalsIgnoreCase("y")) {
                    break;
                } else if (s.equalsIgnoreCase("n")) {
                    sc.close();
                    return;
                }
            }
        }
    }

    private static Monster pickMonster(LootData data, Random rng) {
        return data.monsters.get(rng.nextInt(data.monsters.size()));
    }

    private static String[] fetchTreasureClass(String treasure, LootData data) {
        return data.treasures.get(treasure);
    }

    private static String generateBaseItem(String treasure, LootData data, Random rng) {
        while (data.treasures.containsKey(treasure)) {
            String[] drops = fetchTreasureClass(treasure, data);
            treasure = drops[rng.nextInt(3)];
        }
        return treasure;
    }

    private static String generateBaseStats(String baseItem, LootData data, Random rng) {
        int[] range = data.armors.get(baseItem);
        int defense = rng.nextInt(range[0], range[1] + 1);
        return String.format("Defense: %d", defense);
    }

    private static GenAffix generateAffix(List<Affix> affixes, Random rng) {
        if (!rng.nextBoolean()) {
            return null;
        }
        Affix a = affixes.get(rng.nextInt(affixes.size()));
        int value = rng.nextInt(a.min, a.max + 1);
        return new GenAffix(a.name, String.format("%d %s", value, a.modCode));
    }
}
