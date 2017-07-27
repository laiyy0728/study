package optional;

import java.util.Optional;

public class Person {

    private int age;

    private Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        Person person = new Person();
//        Optional<Person> optPerson = Optional.of(person);
//        System.out.println(optPerson.flatMap(Person::getCar)
//                .flatMap(Car::getInsurance)
//                .map(Insurance::getName)
//                .orElse("unKnown"));

        // 找出年龄大于或者等于minAge参数的Person所对应的保险公司列表
        getInsurance(Optional.ofNullable(person), 19);
    }

    public static String getInsurance(Optional<Person> person, int minAge){
        return person.filter(p -> p.getAge() > minAge)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("unKnown");
    }

}
