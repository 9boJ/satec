import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static final String FILENAME = "C:\\Users\\jatin.LAPTOP-IJ07VMBM\\Downloads\\maze1.txt";
    public static void main(String[] args) {
        File myFile = new File(FILENAME);
//        try{
//            Scanner input = new Scanner(myFile);
//            int loopconter = 0;
//            String[] maze;
//            while (input.hasNext()){
//                System.out.println(input.nextLine());
//            }
//            input.close();
//        } catch (FileNotFoundException e) {
//            System.out.println("File not found");
//        }

        try{
            Scanner input = new Scanner(myFile);
            Stack<String> mazeRev = new Stack<>();

            while (input.hasNext()){
                mazeRev.push(input.nextLine());
            }

            System.out.println(mazeRev);
            //String[] mymaze = new String[mazeRev.size()];
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}