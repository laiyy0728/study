package heeloWorl;

/**
 * Created by laiyy
 * Date 2017/8/7.
 */
public class HelloWorld {

    private String name;

    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorld(args[0]);
        helloWorld.greet();
    }

    public HelloWorld(String name) {
        this.name = name;
    }

    private void greet(){
        System.out.println("hello ," + name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
