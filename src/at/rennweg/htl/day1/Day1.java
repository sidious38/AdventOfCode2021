package at.rennweg.htl.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) {
        try {
            // Part 1
            int increaseCounter = 0;

            File input = new File(Objects.requireNonNull(Day1.class.getResource("input.txt"))
                    .getFile().replace("%20", " "));
            Scanner inputReader = new Scanner(input);
            int lastVal = Integer.parseInt(inputReader.nextLine());

            while (inputReader.hasNextLine()) {
                int currVal = Integer.parseInt(inputReader.nextLine());

                if (currVal > lastVal) {
                    increaseCounter++;
                }

                lastVal = currVal;
            }

            System.out.println("Part 1: " + increaseCounter);

            // Part 2
            increaseCounter = 0;
            inputReader = new Scanner(input);

            int[] lastCompareGroup = new int[3];

            for (int i = 0; i < lastCompareGroup.length; i++) {
                lastCompareGroup[i] = Integer.parseInt(inputReader.nextLine());
            }

            while (inputReader.hasNextLine()) {
                int[] currCompareGroup = new int[3];
                currCompareGroup[0] = lastCompareGroup[1];
                currCompareGroup[1] = lastCompareGroup[2];
                currCompareGroup[2] = Integer.parseInt(inputReader.nextLine());

                int sumLast = lastCompareGroup[0] + lastCompareGroup[1] + lastCompareGroup[2];
                int sumCurr = currCompareGroup[0] + currCompareGroup[1] + currCompareGroup[2];

                if (sumCurr > sumLast) {
                    increaseCounter++;
                }

                lastCompareGroup = currCompareGroup;
            }

            System.out.println("Part 2: " + increaseCounter);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
