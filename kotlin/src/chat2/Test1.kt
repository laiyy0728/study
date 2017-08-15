package chat2

/**
 * Created by laiyy
 * Date 2017/8/15.
 */
fun main(args: Array<String>) {
    println(max(args[0].toInt(), args[1].toInt()))
}

fun max(a: Int, b: Int) = if (a > b) a else b