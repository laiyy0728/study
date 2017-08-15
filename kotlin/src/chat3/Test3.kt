package chat3

/**
 * Created by laiyy
 * Date 2017/8/15.
 */
fun main(args: Array<String>) {
    val map = hashMapOf<String, Int>()
    map.put("one", 1)
    map.put("two", 2)

    for ((key, value) in map) {
        println("key = $key, value = $value")
    }

}