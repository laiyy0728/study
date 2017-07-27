package chat2.entity;

import java.util.concurrent.RecursiveTask;

/**
 * Created by laiyy
 * Date 2017/7/24.
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    private final long[] numbers; // 要求和的数组

    private final int start; // 子任务处理数组的起始位置

    private final int end; // 子任务处理的数组的结束位置

    public static final long THRESHOLD = 10000; // 不再将任务分解为子任务的数组大小

    public ForkJoinSumCalculator(long[] numbers) {
        this.start = 0;
        this.numbers = numbers;
        this.end = numbers.length;
    }

    public ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length < THRESHOLD) {
            return computeSequentially();  // 如果 小于等于阈值，顺序执行
        }
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2); // 创建一个子任务
        leftTask.fork();// 利用 ForkJoinPool 创建异步线程执行新创建的子任务
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
        Long rightResult = rightTask.compute(); // 同步执行第二个子任务，可能允许递归
        Long leftResult = leftTask.join(); // 读取第一个子任务的结果，如果未完成，就等待
        return leftResult + rightResult; // 结果合并
    }

    private long computeSequentially() {
        long sum = 0;
        for (int index = start; index < end; index++) {
            sum += numbers[index];
        }
        return sum;
    }
}
