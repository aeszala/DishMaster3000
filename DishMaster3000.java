// Keeping track of dishes/chores done around the apartment.

import java.util.Scanner;

public class DishMaster3000 {

    static int fileCount;
    static final int DELAY = 700;  // miliseconds
    static final Person USER = new Person();
    static final Person ROOMMATE = new Person();
    static Scanner scanner = new Scanner(System.in);

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
        USER.display(displayPercent);
        System.out.println();
        ROOMMATE.display(displayPercent);
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
        scanner.close();
    }

    public static void oldUser() {
        System.out.println("Please type the name of the file below :)");
        

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
        
        display(false);

        scanner.close();
    }

}