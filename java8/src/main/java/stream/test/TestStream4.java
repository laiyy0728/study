package stream.test;

import stream.collector.PrimeCollector;
import stream.entity.Dish;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestStream4 {

    @Test
    public void test14(){
        long fastest = Long.MAX_VALUE;
        for (int index = 0; index < 10; index ++) {
            long start = System.nanoTime();
            IntStream.rangeClosed(2, 1000000).boxed().collect(Collectors.partitioningBy(TestStream4::isPrime)).get(true);
//            PrimeCollector.partitionPrimesWithCustomCollector(1000000);
            long duration = (System.nanoTime() - start) / 1000000;
            if (duration < fastest) {
                fastest = duration;
            }
        }
        System.out.println(fastest);
    }


    @Test
    public void testPrimeCollector(){
        PrimeCollector.partitionPrimesWithCustomCollector(1000).get(true).forEach(System.err::println);
    }

    @Test
    public void test13(){
        System.out.println(menu.stream().map(Dish::getName).collect(Collectors.joining(",")));
    }

    /**
     * 使用收集器，将数字分数质数和非质数
     */
    @Test
    public void test(){
//        System.out.println(IntStream.rangeClosed(2, 100).boxed().collect(Collectors.partitioningBy(TestStream4::isPrime)).get(true));
        System.out.println(IntStream.rangeClosed(2, 100).boxed().collect(Collectors.partitioningBy(TestStream4::isPrime)).get(false));
    }

    private static boolean isPrime(int number) {
        int number1 = (int)Math.sqrt(number);
        return IntStream.rangeClosed(2, number1) // 生成从 2 到 number 的平方根的所有数
                .noneMatch(i -> number % i == 0); // 如果 number 不能被从 2 到 number 的平方根（不包括）的所有数整除，则返回 true（质数）
    }


    /**
     * partitioningBy 分区函数与其他收集器联合使用
     */
    @Test
    public void test12(){
        System.out.println(menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.partitioningBy(dish -> dish.getCalories() > 500))));
//        System.out.println(menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.partitioningBy(Dish::getType)))); // 无法通过编译，因为第二个 partitioningBy 需要一个 boolean 类型的返回值
        System.out.println(menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.counting())));
    }

    @Test
    public void test11(){
        // 先按照是否是蔬菜分组，再取出每组的最大值
        System.out.println(menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get))));
    }

    @Test
    public void test10(){
        // 先按照是否是蔬菜分组，再按照类型分组
        System.out.println(menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.groupingBy(Dish::getType))));
    }

    @Test
    public void test9(){
        System.out.println(menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian)).get(true));
        System.out.println(menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList()));
    }

    /**
     * 分区函数
     */
    @Test
    public void test8(){
//        System.out.println(menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian)));
        menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian)).forEach( (key, value) -> {
            System.out.println(key + " ---- " + value);
        } );
    }


    /**
     * groupingBy 和 mapping 结合
     * 每种类型的 DISH 都有哪些 CaloriesLevel
     */
    @Test
    public void test7(){
//        System.out.println(menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(TestStream4::getCaloriesLevel, Collectors.toSet()))));
        System.out.println(menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(TestStream4::getCaloriesLevel, Collectors.toCollection(HashSet::new)))));
    }

    @Test
    public void test6(){
        System.out.println(menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.summarizingInt(Dish::getCalories))));
    }


    /**
     * 热量最高的菜
     */
    @Test
    public void test5(){
        System.out.println(menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get))));
    }


    /**
     * 热量最高的菜
     */
    @Test
    public void test4(){
        // 将菜单转为流、按照类型分组，寻找最大的热量，取int
        System.out.println(menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)))));
    }


    /**
     * 二级分组
     */
    @Test
    public void test3(){
        System.out.println(menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy(TestStream4::getCaloriesLevel)))); // 直接使用重构的代码
    }

    @Test
    public void test2(){
        System.out.println(menu.stream().collect(Collectors.groupingBy(TestStream4::getCaloriesLevel))); // 直接使用重构的方法
    }

    // 将同样的方法重构成一个方法
    private static CaloriesLevel getCaloriesLevel(Dish dish) {
        if (dish.getCalories() <= 400) {
            return CaloriesLevel.DIET;
        } else if (dish.getCalories() <= 700) {
            return CaloriesLevel.NORMAL;
        } else {
            return CaloriesLevel.FAT;
        }
    }

    @Test
    public void test1(){
        System.out.println(menu.stream().collect(Collectors.groupingBy(Dish::getType)));

    }

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

     private enum CaloriesLevel {DIET, NORMAL, FAT};

}
