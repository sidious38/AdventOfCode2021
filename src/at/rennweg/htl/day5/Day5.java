package at.rennweg.htl.day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class Day5 {
    public static void main(String[] args) {
        try {
            File input = new File(Objects.requireNonNull(Day5.class.getResource("input.txt"))
                    .getFile().replace("%20", " "));
            Scanner inputReader = new Scanner(input);

            VentDiagram ventDiagram = new VentDiagram(1000, false);

            // Part 1
            while (inputReader.hasNextLine()) {
                String[] linePoints = inputReader.nextLine().split(" -> ");
                int[] start = new int[2];
                int[] end = new int[2];

                for (int i = 0; i < start.length; i++) {
                    start[i] = Integer.parseInt(linePoints[0].split(",")[i]);
                }

                for (int i = 0; i < end.length; i++) {
                    end[i] = Integer.parseInt(linePoints[1].split(",")[i]);
                }

                ventDiagram.addLine(start, end);
            }

            System.out.println("Part 1: " + ventDiagram.getOverlapPoints());

            // Part 2
            inputReader = new Scanner(input);
            VentDiagram ventDiagramDiagonal = new VentDiagram(1000, true);

            while (inputReader.hasNextLine()) {
                String[] linePoints = inputReader.nextLine().split(" -> ");
                int[] start = new int[2];
                int[] end = new int[2];

                for (int i = 0; i < start.length; i++) {
                    start[i] = Integer.parseInt(linePoints[0].split(",")[i]);
                }

                for (int i = 0; i < end.length; i++) {
                    end[i] = Integer.parseInt(linePoints[1].split(",")[i]);
                }

                ventDiagramDiagonal.addLine(start, end);
            }

            System.out.println("Part 2: " + ventDiagramDiagonal.getOverlapPoints());

        } catch (FileNotFoundException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
