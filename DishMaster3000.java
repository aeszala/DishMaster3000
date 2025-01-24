// Keeping track of dishes/chores done around the apartment.

import java.util.Scanner;

public class DishMaster3000 {

    static int fileCount;
    static final int DELAY = 700;  // miliseconds
    static final Person USER = new Person("no name");  // insert your name here
    static final Person ROOMMATE = new Person("no name");  // insert your roommate's name here

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

    public static void newUser() {
        Scanner scanner = new Scanner(System.in);

    System.out.println("Welcome newcomer!!\n");
    System.out.println("What is your name?");
    USER.setName(scanner.nextLine());

    System.out.print("Hello " + USER.getName() + "!! How many times have you done the dishes? ");
    int userCount = scanner.nextInt();
    scanner.nextLine();

    System.out.println("\nAnd your roommate's name?");
    ROOMMATE.setName(scanner.nextLine());

    System.out.print("How many times has " + ROOMMATE.getName() + " done the dishes? ");
    int roommateCount = scanner.nextInt();
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
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String ui;

        System.out.println("Welcome to DishMaster3000! Are YOU are Dish Master!?");
        System.out.println();
        System.out.println("Would you like to use a previously written file?");
        ui = scanner.nextLine();
        if (ui.equalsIgnoreCase("y") || ui.equalsIgnoreCase("yes")) {
            System.out.println("Please type the name of the file below.");
        } else if (ui.equalsIgnoreCase("n") || ui.equalsIgnoreCase("no")) {
            newUser();
        }  // end of creating new profiles

        display(false);

        scanner.close();
    }

}