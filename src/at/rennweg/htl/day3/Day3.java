package at.rennweg.htl.day3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day3 {
    public static void main(String[] args) {
        try {
            // Part 1
            int[] commonOne = new int[12];
            int[] commonZero = new int[12];
            int gammaRate = 0;
            int epsilonRate = 0;

            File input = new File(Objects.requireNonNull(Day3.class.getResource("input.txt"))
                    .getFile().replace("%20", " "));
            Scanner inputReader = new Scanner(input);

            while (inputReader.hasNextLine()) {
                String line = inputReader.nextLine();

                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '1') {
                        commonOne[i]++;
                    } else {
                        commonZero[i]++;
                    }
                }
            }

            for (int i = 0; i < commonOne.length; i++) {
                if (commonOne[i] > commonZero[i]) {
                    gammaRate = (gammaRate << 1) | 1;
                    epsilonRate = epsilonRate << 1;
                } else {
                    gammaRate = gammaRate << 1;
                    epsilonRate = (epsilonRate << 1) | 1;
                }
            }

            System.out.println("Part 1: " + gammaRate * epsilonRate);

            // Part 2
            List<String> values = Files.readAllLines(Path.of(input.getPath()));

            for (int i = 0; i < commonOne.length; i++) {
                int ones = 0;
                int zeros = 0;

                for (String line : values) {
                    if (line.charAt(i) == '1') {
                        ones++;
                    } else {
                        zeros++;
                    }
                }

                int finalI = i;

                if (values.size() > 1) {
                    if (zeros > ones) {
                        values.removeIf(s -> (s.charAt(finalI) == '1'));
                    } else {
                        values.removeIf(s -> (s.charAt(finalI) == '0'));
                    }
                }
            }

            int oxygenRating = Integer.parseInt(values.get(0), 2);

            values = Files.readAllLines(Path.of(input.getPath()));

            for (int i = 0; i < commonOne.length; i++) {
                int ones = 0;
                int zeros = 0;

                for (String line : values) {
                    if (line.charAt(i) == '1') {
                        ones++;
                    } else {
                        zeros++;
                    }
                }

                int finalI = i;

                if (values.size() > 1) {
                    if (zeros > ones) {
                        values.removeIf(s -> (s.charAt(finalI) == '0'));
                    } else {
                        values.removeIf(s -> (s.charAt(finalI) == '1'));
                    }
                }
            }

            int co2Rating = Integer.parseInt(values.get(0), 2);

            System.out.println("Part 2: " + oxygenRating * co2Rating);

        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }
    }
}
