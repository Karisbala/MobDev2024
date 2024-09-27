fun main() {
    val compare: (Int, Int) -> Boolean = { a, b -> a > b }

    interpretResult(compare(100, 16))
}

fun interpretResult(compare: Boolean) {
    if (compare) {
        println("First number is greater than second number")
    }
    else println("Second number is greater or equal to First number")
}
