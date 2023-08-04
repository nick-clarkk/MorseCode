import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        boolean check = false;
        int choice = 0;
        Scanner keyboard = new Scanner(System.in);

        String morseCode;
        String fileName;

        while(!check) {
            menu();
            choice = keyboard.nextInt();
            switch(choice) {
                case 1:
                    MorseCodeTree tree = new MorseCodeTree();
                    tree.readMorseCodeTree();
                    tree.morseCodeTable();
                    System.out.println("\n\n");
                    break;

                case 2:
                    Scanner fileInput = new Scanner(System.in);
                    System.out.print("Please enter the file name >> ");
                    String file = fileInput.nextLine();
                    decodeMorseFile(file);
                    break;

                case 3:
                    Scanner console = new Scanner(System.in);
                    System.out.print("Please enter the morse code you want to decode >> ");
                    String input = console.nextLine();
                    decodeUserInput(input);
                    break;

                case 4:
                    System.out.println("Exiting program...");
                    System.exit(0);

            }
        }
    }

    public static void menu() {
        System.out.println("\n----------------------------------------------");
        System.out.println("Choose an option to continue.\n");
        System.out.println("(1) View morse codes with their letters.");
        System.out.println("(2) Decode a morse code file. Enter file name.");
        System.out.println("(3) Enter morse code to decode.");
        System.out.println("(4) Exit.");
        System.out.println("----------------------------------------------\n");
    }

    public static void decodeMorseFile(String file) {
        MorseCodeTree tree = new MorseCodeTree();
        String line, decodedLine;

        try {
            Scanner inputFile = new Scanner(new FileInputStream(file));
            while(inputFile.hasNext()) {
                line = inputFile.next();
                decodedLine = tree.translateFromMorseCode(line);
                System.out.println(decodedLine);
            }
            System.out.println("\n\n");
            inputFile.close();
        }catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void decodeUserInput(String input) {
        MorseCodeTree tree = new MorseCodeTree();
        String output;
        String[] tempArray = input.split(" ");
        for (String s : tempArray) {
            output = tree.translateFromMorseCode(s);
            System.out.print(output);
        }
        System.out.println("\n");
    }
}
