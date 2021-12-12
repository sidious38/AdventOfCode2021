package at.rennweg.htl.day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day6 {
    public static final int NEW_FISH = 8;
    public static final int RESET_FISH = 6;

    public static void main(String[] args) {
        try {
            File input = new File(Objects.requireNonNull(Day6.class.getResource("input.txt"))
                    .getFile().replace("%20", " "));
            Scanner inputReader = new Scanner(input);

            ArrayList<Integer> fish = new ArrayList<>();

            // Part 1
            if (inputReader.hasNextLine()) {
                String[] fishArrayTemp = inputReader.nextLine().split(",");

                for (String fishTemp : fishArrayTemp) {
                    fish.add(Integer.parseInt(fishTemp));
                }
            }

            for (int day = 0; day < 80; day++) {
                int amountFish = fish.size();

                for (int fishCounter = 0; fishCounter < amountFish; fishCounter++) {
                    int tempFish = fish.get(fishCounter);

                    if (tempFish == 0) {
                        fish.set(fishCounter, RESET_FISH);
                        fish.add(NEW_FISH);
                    } else {
                        fish.set(fishCounter, tempFish - 1);
                    }
                }
            }

            System.out.println("Part 1: " + fish.size());


            // Part 2
            HashMap<Integer, Long> fishHashMap = new HashMap<>();
            Scanner inputReader2 = new Scanner(input);

            // fill initial with zeros
            for (int day = 0; day <= 8; day++) {
                fishHashMap.put(day, 0L);
            }

            if (inputReader2.hasNextLine()) {
                String[] fishArrayTemp = inputReader2.nextLine().split(",");

                for (String fishTemp : fishArrayTemp) {
                    int fishTempInt = Integer.parseInt(fishTemp);
                    fishHashMap.put(fishTempInt, fishHashMap.get(fishTempInt) + 1);
                }
            }

            for (int day = 0; day < 256; day++) {
                HashMap<Integer, Long> fishHashMapTemp = new HashMap<>();

                // fill initial with zeros
                for (int dayFill = 0; dayFill <= 8; dayFill++) {
                    fishHashMapTemp.put(dayFill, 0L);
                }

                for (int dayFish : fishHashMap.keySet()) {
                    if (dayFish == 0) { // Reset fish
                        fishHashMapTemp.put(RESET_FISH, fishHashMap.get(dayFish));
                        fishHashMapTemp.put(NEW_FISH, fishHashMap.get(dayFish));
                    } else if (dayFish == (RESET_FISH + 1)) { // Add fish from temp hashmap
                        fishHashMapTemp.put(dayFish - 1,
                                fishHashMapTemp.get(dayFish - 1) + fishHashMap.get(dayFish));
                    } else { // Remaining days
                        fishHashMapTemp.put(dayFish - 1, fishHashMap.get(dayFish));
                    }
                }

                fishHashMap = fishHashMapTemp;
            }

            long amountFish = 0;

            for (int day : fishHashMap.keySet()) {
                amountFish += fishHashMap.get(day);
            }

            System.out.println("Part 2: " + amountFish);

        } catch (FileNotFoundException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
