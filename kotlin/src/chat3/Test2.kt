package chat3

/**
 * Created by laiyy
 * Date 2017/8/15.
 */
data class User(val name : String, val id : Int)


fun getUser() : User {
    return User("Alex", 1)
}

fun main(args: Array<String>) {
    val user = getUser()
    println("name = ${user.name}, id = ${user.id}")

    val (name, id) = getUser()
    println("name = $name, id = $id")

}
