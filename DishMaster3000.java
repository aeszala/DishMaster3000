// Keeping track of dishes/chores done around the apartment.

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class DishMaster3000 {

    static int fileCount;
    static final int DELAY = 700;  // miliseconds
    static final Person USER = new Person();
    static final Person ROOMMATE = new Person();
    static Scanner scanner = new Scanner(System.in);
    static boolean loop = true;
    static boolean displayPercent = false;

    /**
     * Sets dish stats and standing for you and your roommate
     * @param user the number of times you have done the dishes
     * @param roommate the number of times your roommate has done the dishes
     */
    public static void setDishStats(int user, int roommate) {
        Person.total = user + roommate;
        USER.setDishCount(user);
        ROOMMATE.setDishCount(roommate);
        USER.setStanding();
        ROOMMATE.setStanding();
    }

    /**
     * displays you and your roommates names, titles, and how often you have both done the dishes.
     * @param displayPercent if true, displays a percentage of how often each person has done the dishes. If false, shows the number of times as an integer.
     */
    public static void display(boolean displayPercent) {
        System.out.println();
        System.out.println("~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
        USER.display(displayPercent);
        System.out.println();
        ROOMMATE.display(displayPercent);
        System.out.println("~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
        System.out.println();
    }

     /**
     * displays you and your roommates names, titles, and how often you have both done the dishes.
     * @param displayPercent if true, displays a percentage of how often each person has done the dishes. If false, shows the number of times as an integer.
     * @param userDisplaysFirst if true, the user's name and stats will be displayed first. If false, the roommate will be displayed first.
     */
    public static void display(boolean displayPercent, boolean userDisplaysFirst) {
        if (userDisplaysFirst) {
            USER.display(displayPercent);
            ROOMMATE.display(displayPercent);
        } else {
            ROOMMATE.display(displayPercent);
            USER.display(displayPercent);
        }
    }

    public static int validNum(int n) {
        int num = n;
        boolean valid = false;
        while (!valid) {
            if(num >= 0) {
                valid = true;
            } else {
                System.out.print("\nHow is that possible?! Please type the real number! ");
                num = scanner.nextInt();
            }
        }
        return num;
    }

    public static void newUser() {

    System.out.println("Welcome newcomer!!\n");
    System.out.println("What is your name?");
    USER.setName(scanner.nextLine());

    System.out.print("Hello " + USER.getName() + "!! How many times have you done the dishes? ");
    int userCount = validNum(scanner.nextInt());
    scanner.nextLine();

    System.out.println("\nAnd your roommate's name?");
    ROOMMATE.setName(scanner.nextLine());

    System.out.print("How many times has " + ROOMMATE.getName() + " done the dishes? ");
    int roommateCount = validNum(scanner.nextInt());
    scanner.nextLine();

    setDishStats(userCount, roommateCount);

        System.out.print("\nAwesome! Creating profiles now");
        try {
            for (int i = 0; i < 4; i++) {
                Thread.sleep(DELAY);
                System.out.print(".");
                System.out.flush();
            }
        } catch (InterruptedException e) {
            System.err.println("Something went wrong :(");
        }
        System.out.print("Done!\n");
    }

    public static void oldUser() {
        System.out.println("Please type the name of the file below :)");
        readFile(scanner.nextLine());
    }

    public static void readFile(String fileName) {
        int userDC = 0;
        int roommateDC = 0;
        try (Scanner scanner = new Scanner(new File(fileName))) {
            // read user data
            if (scanner.hasNextLine()) {
                String[] userData = scanner.nextLine().split(",");
                USER.setName(userData[0]);
                userDC = Integer.parseInt(userData[1]);
            }
            // read roommate data
            if (scanner.hasNextLine()) {
                String[] roommateData = scanner.nextLine().split(",");
                ROOMMATE.setName(roommateData[0]);
                roommateDC = Integer.parseInt(roommateData[1]);
            }
            Person.total = userDC + roommateDC;
            USER.setDishCount(userDC);
            ROOMMATE.setDishCount(roommateDC);
            USER.setStanding();
            ROOMMATE.setStanding();
            System.out.println("File " + fileName + " read successfully!");
        } catch (FileNotFoundException e) {
            System.err.println("I couldn't find that file! " + e.getMessage());
        }
    }

    public static void writeFile(String fileName) {
        try(FileWriter writer = new FileWriter(fileName)) {
            writer.write(USER.toString() + "\n");
            writer.write(ROOMMATE.toString() + "\n");
            System.out.println("Save successful!");
        } catch (IOException e) {
            System.err.println("Something went wrong :( " + e.getMessage());
        }
    }

    public static boolean checkChoice(String choice) {
        if (choice.equalsIgnoreCase("exit")) {
            loop = false;
        } else if (choice.equalsIgnoreCase("save")) {
            System.out.println("What would you like to call the file?");
            writeFile(scanner.nextLine());
        } else if (choice.equalsIgnoreCase("read")) {
            System.out.println("Please type the name of the file you would like to read.");
            readFile(scanner.nextLine());
        } else if (choice.equalsIgnoreCase("add")) {
            System.out.println("Yippee!! Who did the dishes this time?");
            String whoDunnit = scanner.nextLine();
            if (whoDunnit.equalsIgnoreCase(USER.getName()))
                USER.add();
            else if (whoDunnit.equalsIgnoreCase(ROOMMATE.getName()))
                ROOMMATE.add();
            else {
                System.out.println("Hmm, I don't know who that is. Please type " 
                + USER.getName() + " or " + ROOMMATE.getName() + ".");
            }
        } else if (choice.equalsIgnoreCase("change name")) {
            System.out.println("Type u to change your name or r to change your roommate's name!");
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("u")) {
                System.out.println("What would you like to change your name to?");
                USER.setName(scanner.nextLine());
                System.out.println("Name changed successfully!");
            } else if(input.equalsIgnoreCase("r")) {
                System.out.println("What would you like to change their name to?");
                ROOMMATE.setName(scanner.nextLine());
                System.out.println("Name changed successfully!");
            }
        } else if (choice.equalsIgnoreCase("toggle display")) {
            if(displayPercent) {
                displayPercent = false;
                System.out.println("I will now display integers!");
            } else {
                displayPercent = true;
                System.out.println("I will now display percentages!");
            }
        } else if (choice.equalsIgnoreCase("?")) {
            Options();
        } else if (choice.equalsIgnoreCase("more")) {
            System.out.println("Display extra settings here");
        } else {
            return false;
        }
        return true;
    }

    public static void Options() {
        System.out.println("?  -  Displays all options\n");
        System.out.println("exit  -  Exits the program (don't forget to save!)\n");
        System.out.println("save  -  Saves your data to a text file\n");
        System.out.println("read  -  Reads data from a pre-existing file\n");
        System.out.println("add  -  Use this to add to you or your roommates dish count\n");
        System.out.println("toggle display  -  Changes display from integers to percentages, and vise versa\n");
        System.out.println("change name  -  This is how you change you or your roommate's name\n");
        // System.out.println("more  -  Displays more customization options\n");
    }
        
    public static void main(String[] args) {
        String ui;

        System.out.println("Welcome to DishMaster3000! Are YOU are Dish Master!?");
        System.out.println();
        System.out.println("Would you like to use a previously written file?");
        boolean valid = false;
        while (!valid) {
            ui = scanner.nextLine();
            if (ui.equalsIgnoreCase("y") || ui.equalsIgnoreCase("yes")) {
                valid = true;
                oldUser();
            } else if (ui.equalsIgnoreCase("n") || ui.equalsIgnoreCase("no")) {
                valid = true;
                newUser();
            } else if (ui.equalsIgnoreCase("y/n") || ui.equalsIgnoreCase("yes/no")) {
                System.out.println("You think you're sooo funny don't you?");
                System.out.println("Type y/n or yes/no.");
            } else {
                System.out.println("Please type y/n or yes/no!");
            }
        }
        
        do {
            display(displayPercent);
            boolean Done = false;
            while(!Done) {
                System.out.println("What would you like to do? Type \"?\" to see all options.");
                String choice = scanner.nextLine().trim();
                if (checkChoice(choice))
                    Done = true;
            }
        } while (loop);


        scanner.close();
    }

}