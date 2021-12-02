package at.rennweg.htl.day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) {
        try {
            // Part 1
            int depth = 0;
            int horizontalPos = 0;

            File input = new File(Objects.requireNonNull(Day2.class.getResource("input.txt"))
                    .getFile().replace("%20", " "));
            Scanner inputReader = new Scanner(input);

            while (inputReader.hasNextLine()) {
                String[] values = inputReader.nextLine().split(" ");

                switch (values[0]) {
                    case "forward" -> horizontalPos += Integer.parseInt(values[1]);
                    case "down" -> depth += Integer.parseInt(values[1]);
                    case "up" -> depth -= Integer.parseInt(values[1]);
                }
            }

            System.out.println("Part 1: " + depth * horizontalPos);

            // Part 2
            depth = 0;
            horizontalPos = 0;
            int aim = 0;

            inputReader = new Scanner(input);

            while (inputReader.hasNextLine()) {
                String[] values = inputReader.nextLine().split(" ");
                int parsedInt = Integer.parseInt(values[1]);

                switch (values[0]) {
                    case "forward" -> {
                        horizontalPos += parsedInt;
                        depth += (aim * parsedInt);
                    }
                    case "down" -> aim += parsedInt;
                    case "up" -> aim -= parsedInt;
                }
            }

            System.out.println("Part 2: " + depth * horizontalPos);

        } catch (FileNotFoundException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}
