package chat2

/**
 * Created by laiyy
 * Date 2017/8/15.
 */
fun main(args: Array<String>) {
    print(getStringLength("aaa"))
    print(if (getStringLength(111) == null) "不是字符串" else getStringLength(111))
}

fun getStringLength(obj: Any) : Int ? {
    if (obj is String) {
        return obj.length
    }
    return null
}