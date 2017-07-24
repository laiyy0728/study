package chat2.test;

import chat2.entity.ForkJoinSumCalculator;
import chat2.entity.WordCounter;
import chat2.entity.WordCounterSpliterator;
import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.StreamSupport;

/**
 * Created by laiyy
 * Date 2017/7/24.
 */
public class TestStream5 {

    private static final String SENTENCE =
            " Nel mezzo del cammin di nostra vita mi ritrovai in una selva oscura ché la dritta via era smarrita ";


    /**
     * Spliterator 版
     */
    @Test
    public void testSpliterator(){
        System.out.println(StreamSupport.stream(new WordCounterSpliterator(SENTENCE), true).reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine));
    }

    /**
     * 并行版
     */
    @Test
    public void test7() {
        System.out.println(IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt).parallel().reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine));
    }

    /**
     * 提取版
     */
    @Test
    public void test6() {
        System.out.println(IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt).reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine));
    }


    /**
     * 顺序执行
     */
    @Test
    public void test5() {
        System.out.println(countWordsIteratively(SENTENCE));
    }

    public int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) counter++;
                lastSpace = false;
            }
        }
        return counter;
    }

    @Test
    public void test4() {
        long[] longs = LongStream.rangeClosed(1, 10000000).toArray();
        RecursiveTask<Long> task = new ForkJoinSumCalculator(longs);
        System.out.println(new ForkJoinPool().invoke(task));
    }

    @Test
    public void test3() {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, 10000000).parallel().forEach(accumulator::add);
        System.out.println(accumulator.total);
    }

    private class Accumulator {
        private long total = 0;

        private void add(long value) {
            total += value;
        }
    }


    @Test
    public void test2() {
        System.out.println(measureSumPref(TestStream5::iterativeSum, 10000000) + "毫秒");
    }

    @Test
    public void test() {
        System.out.println(measureSumPref(TestStream5::sequectialSum, 10000000) + " 毫秒");
    }

    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 0; i <= n; i++) {
            result += i;
        }
        return result;
    }

    public static long sequectialSum(long n) {
        return LongStream.rangeClosed(1L, n)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    public long measureSumPref(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int index = 0; index < 10; index++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 100000;
            System.out.println(sum);
            if (duration < fastest) {
                fastest = duration;
            }
        }
        return fastest;
    }

}
