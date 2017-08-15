package chat2

/**
 * Created by laiyy
 * Date 2017/8/15.
 */
fun main(args: Array<String>) {
    if (args.size < 2){
        print("No Number Supplied!")
    } else{
        val x = parseInt(args[0])
        val y = parseInt(args[1])
        if (x != null && y != null){
            print(x * y)
        } else{
            print("aaa")
        }
    }
}

fun parseInt(str : String) : Int ? {
    try {
        return str.toInt()
    } catch (e : Exception) {
        e.printStackTrace()
    }
    return null
}

