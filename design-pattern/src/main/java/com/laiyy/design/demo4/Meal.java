package com.laiyy.design.demo4;

import java.util.ArrayList;
import java.util.List;

/**
 * @author laiyy
 * @date 2018/7/6 17:29
 * @description
 */
public class Meal {

    private List<Item> items = new ArrayList<>();

    public void addItem(Item item){
        items.add(item);
    }

    public float getCost(){
        float cost = 0.0f;
        for (Item item : items) {
            cost += item.price();
        }
        return cost;
    }

    public void showItems(){
        for (Item item : items) {
            System.out.println("Item：" + item.name() + "，Packing：" + item.packing() + "，Price：" + item.price());
        }
    }

}
