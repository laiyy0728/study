package chat2

/**
 * Created by laiyy
 * Date 2017/8/15.
 */
fun main(args: Array<String>) {
    cases("Hello")
    cases(1)
    cases(0L)
    cases(MyClass())
    cases("hello")
}

fun cases(obj: Any) {
    when (obj) {
        1 -> println("One")
        "Hello" -> println("Hello")
        is Long -> println("is Long")
        !is String -> println("!is String")
        else -> println("unKnown")
    }
}

class MyClass() {

}