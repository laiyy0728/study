package exam.chat1;

import org.junit.Test;

import java.util.Random;

/**
 * Created by laiyy
 * Date 2017/9/21.
 */
public class Lesson1 {

    /**
     * 二维数组置换
     */
    @Test
    public void test2() {
        int[][] arr = new int[4][10];
        for (int index = 0; index < arr.length; index++) {
            for (int i = 0; i < arr[index].length; i++) {
                arr[index][i] = new Random().nextInt(100);
            }
        }
        for (int index = 0; index < arr.length; index++) {
            for (int i = 0; i < arr[index].length; i++) {
                System.out.print(arr[index][i] + "\t");
            }
            System.out.println();
        }
        int[][] arr2 = new int[10][4];
        for (int index = 0; index < arr2.length; index++) {
            for (int i = 0; i < arr2[index].length; i++) {
                arr2[index][i] = arr[i][index];
            }
        }
        for (int index = 0; index < arr2.length; index++) {
            for (int i = 0; i < arr2[index].length; i++) {
                System.out.print(arr2[index][i] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 斐波那契数列
     */
    @Test
    public void test1() {
        int f = 0;
        int g = 1;
        for (int i = 0; i <= 15; i++) {
            System.out.print(f + " \t");
            f += g;
            g = f - g;
        }
    }

}
