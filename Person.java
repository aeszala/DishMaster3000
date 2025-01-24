public class Person {
    private String name;
    private String standing;
    private int dishCount;
    public static int total;

    public Person() {
        this.name = "no name";
        this.standing = "TBD";
        this.dishCount = -1;
    }

    public Person(String name, String standing, int dishCount) {
        this.name = name;
        this.standing = standing;
        this.dishCount = dishCount;
    }

    public double getDishCount() {
        return dishCount;
    }

    public String getName() {
        return name;
    }

    public String getStanding() {
        return standing;
    }

    public double getPercentage() {
        if (total != 0)
            return Math.round(((double)dishCount/total) * 100);
        else
            return 0;
    }

    public void setDishCount(int dishCount) {
        if (dishCount <= total)
            this.dishCount = dishCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStanding() {
        double dishStats = getPercentage();
        if (dishStats == -1)
            standing = "TBD";
        else if (dishStats > 90)
            standing = "The Dish Master";
        else if (dishStats > 75)
            standing = "The definitively better roommate";
        else if (dishStats > 50)
            standing = "The slightly better roomate";
        else if (dishStats == 50)
            standing = "Perfectly balanced, as all things should be";
        else if (dishStats < 10)
            standing = "An absolute loser";
        else if (dishStats < 25)
            standing = "The definitively worse roommate";
        else if (dishStats < 50)
            standing = "The slightly worse roommate";
    }

    public void display(boolean displayPercent) {
        System.out.println(name + " - \"" + standing + "\"");
        if (displayPercent) {
            if (dishCount <= 0)
                System.out.println(name + " has washed 0.0% of the dishes!");
            else
                System.out.println(name + " has washed " + getPercentage() + "% of the dishes!");
        } else {
            if (dishCount <= 0)
                System.out.println(name + " has washed the dishes 0 times!");
            else if (dishCount == 1)
                System.out.println(name + " has washed the dishes 1 time!");
            else
                System.out.println(name + " has washed the dishes " + dishCount + " times!");
        }
    }
}