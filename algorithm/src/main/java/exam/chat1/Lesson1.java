package exam.chat1;

import org.junit.Test;

import java.util.Random;

/**
 * Created by laiyy
 * Date 2017/9/21.
 */
public class Lesson1 {

    @Test
    public void test3(){
        String[][] arr = new String[new Random().nextInt(20)][new Random().nextInt(20)];
        for (int index = 0; index < arr.length; index++){
            for (int i = 0; i < arr[index].length; i++){
                if (new Random().nextBoolean()){
                    arr[index][i] = "*";
                } else {
                    arr[index][i] = " ";
                }
                System.out.print(arr[index][i] + "\t");
            }
            System.out.println();
        }
    }

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
            if (f != 0) {
                System.out.print(f + " \t");
            }
            f += g;
            g = f - g;
        }
    }

}
