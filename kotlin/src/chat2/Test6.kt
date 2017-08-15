package chat2

/**
 * Created by laiyy
 * Date 2017/8/15.
 */
fun main(args: Array<String>) {
    val x = args[0].toInt()
    val y = 10
    if (x in 1..y-1){
        println("OK")
    }
    for (a in 1..5){
       println("${a}")
    }
    println()
    val array = arrayListOf<String>()
    array.add("aaa")
    array.add("bbb")
    array.add("ccc")
    if (x !in 0..array.size - 1) {
        println("数组下标越界")
    }
    if ("aaa" in array){
        println("数组中包含 aaa")
    }
    if ("ddd" in array){
        println("数组中包含 ddd")
    } else{
        println("数组中不包含 ddd")
    }



}