package at.rennweg.htl.day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) {
        try {
            File input = new File(Objects.requireNonNull(Day4.class.getResource("input.txt"))
                    .getFile().replace("%20", " "));
            Scanner inputReader = new Scanner(input);

            ArrayList<Integer> numbersDrawn = new ArrayList<>();
            ArrayList<BingoBoard> boards = new ArrayList<>();

            // Part 1
            if (inputReader.hasNextLine()) {
                String[] nums = inputReader.nextLine().split(",");

                for (String num : nums) {
                    numbersDrawn.add(Integer.parseInt(num));
                }
            }

            while (inputReader.hasNextLine()) {
                if (inputReader.hasNextLine()) {
                    inputReader.nextLine();
                }

                int[][][] tempBingo =
                        new int[BingoBoard.ROW_COLUMN_LENGTH][BingoBoard.ROW_COLUMN_LENGTH][2];

                for (int i = 0; i < BingoBoard.ROW_COLUMN_LENGTH; i++) {
                    String[] nums = inputReader.nextLine().trim().split("\\s+");
                    for (int j = 0; j < BingoBoard.ROW_COLUMN_LENGTH; j++) {
                        tempBingo[i][j][0] = Integer.parseInt(nums[j]);
                    }
                }
                boards.add(new BingoBoard(tempBingo));
            }

            int winnerBoardIndex = -1;
            int lastNumDrawn = 0;

            for (int numberDrawn : numbersDrawn) {
                for (int i = 0; i < boards.size(); i++) {
                    if (boards.get(i).draw(numberDrawn)) {
                        winnerBoardIndex = i;
                        break;
                    }
                }

                if (winnerBoardIndex != -1) {
                    lastNumDrawn = numberDrawn;
                    break;
                }
            }

            int sumUndrawnNumbers = 0;

            for (int undrawnNumber : boards.get(winnerBoardIndex).getUndrawnNumbers()) {
                sumUndrawnNumbers += undrawnNumber;
            }

            System.out.println("Part 1: " + sumUndrawnNumbers * lastNumDrawn);

            // Part 2
            for (BingoBoard board : boards) {
                board.resetDrawn();
            }

            lastNumDrawn = 0;

            for (int numberDrawn : numbersDrawn) {
                boolean lastBoardWon = false;

                for (int i = 0; i < boards.size(); i++) {
                    if (boards.get(i).draw(numberDrawn)) {
                        if (boards.size() == 1) {
                            lastBoardWon = true;
                        } else {
                            boards.remove(i);
                            i--;
                        }
                    }
                }

                if (boards.size() == 1 && lastBoardWon) {
                    lastNumDrawn = numberDrawn;
                    break;
                }
            }

            sumUndrawnNumbers = 0;

            for (int undrawnNumber : boards.get(0).getUndrawnNumbers()) {
                sumUndrawnNumbers += undrawnNumber;
            }

            System.out.println("Part 2: " + sumUndrawnNumbers * lastNumDrawn);

        } catch (FileNotFoundException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
