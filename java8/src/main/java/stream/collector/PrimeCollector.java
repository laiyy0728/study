package stream.collector;

import lambda.inter.Predicate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.IntStream;

/**
 * 其中T、 A和R分别是流中元素的类型、用于累积部分结果的对象类型，以及collect操作最
 * 终 结 果 的 类 型 。 这 里 应 该 收 集Integer 流 ， 而 累 加 器 和 结 果 类 型 则 都 是 Map<Boolean,
 * List<Integer>>，键是true和false，
 * 值则分别是质数和非质数的List
 *
 * @param <T>
 */
public class PrimeCollector<T> implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

    public static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(new PrimeCollector<>());
    }

    private static boolean isPrime(List<Integer> primes, int candidate) {
        int number = (int) Math.sqrt(candidate); // 取被除数的平方根
        return takeWhile(primes, i -> i <= number) // 筛选出小于被测数平方根的质数
                .stream()
                .noneMatch(p -> candidate % p == 0); // 是否是质数
    }

    private static <A> List<A> takeWhile(List<A> list, Predicate<A> predicate) {
        int i = 0;
        for (A a : list) {
            if (!predicate.test(a)) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }

    /**
     * 这里不但创建了用作累加器的Map，还为true和false两个键下面初始化了对应的空列表。
     * 在收集过程中会把质数和非质数分别添加到这里。
     *
     * @return
     */
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>() {{
            put(true, new ArrayList<>());
            put(false, new ArrayList<>());
        }};
    }

    /**
     * 在这个方法中，调用了isPrime方法，将待测试是否为质数的数以及迄今找到的质数列表
     * （也就是累积Map中true键对应的值）传递给它。这次调用的结果随后被用作获取质数或非质数
     * 列表的键，这样就可以把新的被测数添加到恰当的列表中。
     *
     * @return
     */
    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
            acc.get(isPrime(acc.get(true), candidate)).add(candidate);
        };
    }

    /**
     * 在并行收集时把两个部分累加器合并起来，这里，它只需要合并两个Map，即
     * 将第二个Map中质数和非质数列表中的所有数字合并到第一个Map的对应列表中就行了
     * <p>
     * 实际上这个收集器是不能并行使用的，因为该算法本身是顺序的。这意味着永远都
     * 不会调用combiner方法，可以把它的实现留空（更好的做法是抛出一个UnsupportedOperationException异常）
     *
     * @return
     */
    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2) -> {
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));
            return map1;
        };
    }

    /**
     * accumulator正好就是收集器的结果，用不着
     * 进一步转换，那么finisher方法就返回identity函数
     *
     * @return
     */
    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    /**
     * 既不是CONCURRENT也不是UNORDERED，
     * 但却是IDENTITY_FINISH
     * <p>
     * UNORDERED——归约结果不受流中项目的遍历和累积顺序的影响。
     * CONCURRENT——accumulator函数可以从多个线程同时调用，且该收集器可以并行归
     * 约流。如果收集器没有标为UNORDERED，那它仅在用于无序数据源时才可以并行归约。
     * IDENTITY_FINISH——这表明完成器方法返回的函数是一个恒等函数，可以跳过。这种
     * 情况下，累加器对象将会直接用作归约过程的最终结果。这也意味着，将累加器A不加检
     * 查地转换为结果R是安全的
     *
     * @return
     */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }
}
