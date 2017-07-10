package chat1;

/**
 * Created by laiyy
 * Date 2017/7/3.
 */
public class MeaningOfThis {

    public final int value = 4;

    public void doInt(){
        int value = 6;

        Runnable runnable = new Runnable() {
            public final int value = 5;
            @Override
            public void run() {
                int value = 10;
                System.out.println(this.value);
            }
        };
        runnable.run();
    }

    public static void main(String[] args) {
        MeaningOfThis meaningOfThis = new MeaningOfThis();
        meaningOfThis.doInt();
    }

}
