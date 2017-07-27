package lambda.inter;

/**
 * 自定义 Lambda 接口
 * Created by laiyy
 * Date 2017/7/3.
 */
public interface Predicate<T> {

    boolean test(T t);

}
