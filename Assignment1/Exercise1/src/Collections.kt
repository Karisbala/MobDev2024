fun main() {
    val numbers: Array<Int> = arrayOf(1,2,3,4,5)
    var sum = 0
    for (n in numbers) {
        sum += n
        println(n)
    }

    println("Sum of numbers is: $sum")
}