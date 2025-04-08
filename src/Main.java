/*
Main.java
 Summary - solves a maze by reading the maze from a file. the file is added to a stack then to a 1D array then it is broken down into char and add to a 2D array.
the goal is to find a path from the top of the maze to the bottom. Ever open spot it has been its shown with “cookie crumbs” (.). If no path exists, it outputs that
no exit.
 Author - Jatin Bhagat
 Class - AUCSC 112
 ID Number - 1862200
 Date - Mar/17/2025
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static final String FILENAME = "C:\\Users\\Jaitn Bhagat\\Desktop\\mazaa\\maze2.txt";
    public static String[] mymaze;
    public static char[][] mymaze2D;
    public static Stack<Integer> x = new Stack<>();
    public static Stack<Integer> y = new Stack<>();
    static int newY;
    static int newX;

    public static void main(String[] args) {
        File myFile = new File(FILENAME);

        try {
            Scanner input = new Scanner(myFile);
            Stack<String> mazeRev = new Stack<>();

            while (input.hasNext()) {
                mazeRev.push(input.nextLine());
            }

            input.close();

            /*
            Adds the file into 1D an array
             */
            mymaze = new String[mazeRev.size()];
            for (int i = mymaze.length - 1; i >= 0; i--) {
                mymaze[i] = mazeRev.pop();
            }

            find();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Finds the way out of the maze by calling:
     * arrayto2D();
     * findtheopening();
     * solvethemaze();
      */
    public static void find() {
        System.out.println();
        System.out.println(" File Path: " + FILENAME);
        System.out.println();
        
        arrayto2D();
        findtheopening();
        solvethemaze();
    }



    /**
     * Turns the 1D into an 2D array
     */
    public static void arrayto2D() {
        mymaze2D = new char[mymaze.length][mymaze[0].length()];

        for (int i = 0; i < mymaze.length; i++) {
            for (int j = 0; j < mymaze[0].length(); j++) {
                mymaze2D[i][j] = mymaze[i].charAt(j);
            }
        }

    }

    /**
     *  Finds the opeing of the maze and add the row and col to the stacks also levels a “cookie crumbs”
     */
    public static void findtheopening() {
        for (int i = 0; i < mymaze2D[0].length; i++) {
            if (mymaze2D[0][i] == ' ') {
                x.push(i);
                y.push(0);
                mymaze2D[y.peek()][x.peek()] = '.';
                break;
            }
        }
    }

    /**
     * Locations to look at next
     */
    private static final int[][] movesthatcanbedone = {
            {0, 1},   // Down
            {0, -1},  // Up
            {1, 0},   // Right
            {-1, 0}   // Left

    };

    /**
     * Solves the maze by looking for open spots then moves to it and goes back if it cant find an open spots by using "pop" on x and y .
     */
    public static void solvethemaze() {
        while (!y.isEmpty()) {

            if (y.peek() == mymaze2D.length - 1) {
                System.out.println("Exit is at: " + x.peek() + ", " + y.peek());
                return;
            }

            boolean moved = false;
            for (int[] direction : movesthatcanbedone) {
                newX = x.peek() + direction[0];
                newY = y.peek() + direction[1];

                if (newX >= 0 && newY >= 0 && newX < mymaze2D[0].length && newY < mymaze2D.length && mymaze2D[newY][newX] == ' ') {
                    x.push(newX);
                    y.push(newY);
                    updataarrya();
                    moved = true;
                    break;
                }
            }

            if (!moved) {
                x.pop();
                y.pop();
            }
            printarrya();
        }
        System.out.println("No exit found.");
    }

    /**
     * Prints the maze
     */
    public static void printarrya() {
        for (int i = 0; i < mymaze2D.length; i++) {
            for (int j = 0; j < mymaze2D[i].length; j++) {
                System.out.print(mymaze2D[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    /**
     * updates the array by add "."
     */
    public static void updataarrya() {
        if(mymaze2D[newY][newX] == ' '){
            mymaze2D[newY][newX] = '.';
        }
    }
}
