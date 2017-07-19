package chat2.test;

import chat2.entity.Dish;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by laiyy
 * Date 2017/7/19.
 */
public class TestStream1 {

    private List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH)
    );

    private List<String> words = Arrays.asList("Java 8", "Lambda", "In", "Action");

    private List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    @Test
    public void testMapReduce(){
        System.out.println(menu.stream().map( dish -> 1).reduce(0, Integer::sum));
    }

    @Test
    public void testReduce() {
//        System.out.println(numbers.stream().reduce(0, (a, b) -> a + b));
        System.out.println(numbers.stream().reduce(0, Integer::sum));
        System.out.println(numbers.stream().reduce(0, Integer::max));
        System.out.println(numbers.stream().reduce(0, Integer::min));
    }

    @Test
    public void testFindAny() {
//        Optional<Dish> any = menu.stream().filter(Dish::isVegetarian).findAny();
        menu.stream().filter(Dish::isVegetarian).findAny().ifPresent(dish -> System.out.println(dish.getName()));
    }

    @Test
    public void testNoneMatch() {
        // 是否所有元素都不满足 noneMatch 的条件
        System.out.println(menu.stream().noneMatch(dish -> dish.getCalories() > 1000));
    }

    @Test
    public void testAllMatch() {
        // 是否所有元素都满足 allMatch 的条件
        System.out.println(menu.stream().allMatch(dish -> dish.getCalories() < 1000));
    }

    @Test
    public void testAnyMatch() {
        // 是否满足 anyMatch 的条件
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("存在");
        }
    }

    @Test
    public void testMap3() {
//        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
//        System.out.println(numbers.stream().map(number -> Math.pow(number, 2)).collect(Collectors.toList()));

        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(4, 5);
        // 返回所有数对：(1,4),(1,5),(2,4),(2,5),(3,4),(3,5)
//        numbers1.stream().flatMap(number -> numbers2.stream().map(number2 -> new int[]{number, number2})).collect(Collectors.toList()).forEach(System.err::println);
        // 返回所有能被 3 整除的数对：(1,5),(2,4)
        numbers1.stream().flatMap(number -> numbers2.stream().filter(number2 -> (number + number2) % 3 == 0).map(number2 -> new int[]{number, number2})).collect(Collectors.toList()).forEach(System.err::println);
    }

    @Test
    public void testMap2() {
        List<Stream<String>> collect = words.stream().map(word -> word.split("")).map(Arrays::stream).distinct().collect(Collectors.toList());
        System.out.println(collect);

        List<String> collect1 = words.stream().map(word -> word.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        System.out.println(collect1);
    }

    @Test
    public void testMap() {
        menu.stream().map(Dish::getName).map(String::length).collect(Collectors.toList()).forEach(System.err::println);
    }

    @Test
    public void testSkip() {
        menu.stream().skip(3).collect(Collectors.toList()).forEach(System.err::println);
    }

    @Test
    public void testLimit() {
        menu.stream().filter(d -> d.getCalories() > 300).limit(3).collect(Collectors.toList()).forEach(System.err::println);
    }

    @Test
    public void testDistinct() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream().filter(i -> i % 2 == 0).distinct().forEach(System.err::println);
    }

    @Test
    public void testFilter() {
        List<Dish> collect = menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void test1() {
        List<String> names = menu.stream().filter(d -> {
            System.out.println("filtter：" + d.getName());
            return d.getCalories() > 300;
        }).map(d -> {
            System.out.println("map：" + d.getName());
            return d.getName();
        }).limit(3).collect(Collectors.toList());
        System.out.println(names);
    }

    @Test
    public void test() {
        List<String> collect = menu.stream().filter(d -> d.getCalories() > 300).map(Dish::getName).limit(3).collect(Collectors.toList());
        System.out.println(collect);
    }

}
