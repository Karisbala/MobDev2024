fun isPositive(number: Int): Any {
    if (number == 0) return "Number is zero"
    return number > 0
}

fun main() {
    val number = readln().toInt()

    val result = isPositive(number)

    if (result == true) {
        println("Number is positive")
    }
    else if (result == false) {
        println("Number is negative")
    }
    else println(result)
}


