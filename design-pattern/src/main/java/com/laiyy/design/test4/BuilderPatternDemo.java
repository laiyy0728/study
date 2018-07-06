package com.laiyy.design.test4;

/**
 * @author laiyy
 * @date 2018/7/6 17:32
 * @description
 */
public class BuilderPatternDemo {

    public static void main(String[] args) {
        MealBuilder builder = new MealBuilder();

        Meal prepareVegMeal = builder.prepareVegMeal();
        System.out.println("veg meal");
        prepareVegMeal.showItems();
        System.out.println("Total Cost" + prepareVegMeal.getCost());

        Meal meal = builder.prepareNonVegMeal();
        System.out.println("Non Veg Meal");
        meal.showItems();
        System.out.println("Total Cost " + meal.getCost());
    }

}
