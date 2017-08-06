package action;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author laiyy0728
 * @Date 2017/8/6 11:05
 */
public class TestLambda {

    /**
     * 给定集合 List<Integer> = {1,4,9},求出所有子集
     */
    @Test
    public void testLambda1() {
        List<Integer> list = Arrays.asList(1, 4, 9);
        List<List<Integer>> subsets = subsets(list);
        System.out.println(subsets);
    }

    private static List<List<Integer>> subsets(List<Integer> list){
        if (list.isEmpty()) {
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(Collections.emptyList());
            return ans; // 如果输入为空，则返回一个空集合
        }
        Integer first = list.get(0); // 否则取出第一个元素，
        List<Integer> result = list.subList(1, list.size());

        List<List<Integer>> subAns = subsets(result); // 找出剩余部分的所有子集，并赋值给 subAns
        List<List<Integer>> subAns2 = insertAll(first,subAns );

        return concat(subAns, subAns2);
    }

    private static List<List<Integer>> concat(List<List<Integer>> subAns, List<List<Integer>> subAns2) {
        List<List<Integer>> result = new ArrayList<>(subAns);
        result.addAll(subAns2);
        return result;
    }

    private static List<List<Integer>> insertAll(Integer first, List<List<Integer>> subAns) {
        List<List<Integer>> result = new ArrayList<>();
        subAns.forEach( list -> {
            // 复制列表，从而有机会对其进行添加操作，即使底层是可变的，也不应该复制底层的结构
            List<Integer> copyList = new ArrayList<>();
            copyList.add(first);
            copyList.addAll(list);
            result.add(copyList);
        });
        return result;
    }

}
