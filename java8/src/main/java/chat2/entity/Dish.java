package chat2.entity;


/**
 * Created by laiyy
 * Date 2017/7/18.
 */
public class Dish {

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    private final String name;

    private final boolean vegetarian;

    private final int calories;

    private final Type type;

    public enum Type {MEAT, FISH, OTHER}

}
