fun main() {
//    val person1 = Person("Asset", 23, "asset@gmail.com")
//    val employee1 = Employee("Abzal", 23, "abzal@gmail.com", 1000)
//    println(person1)
//    println(employee1)

    val bankAccount1 = BankAccount(100)
    bankAccount1.withdraw(80)
    println("Balance is: ${bankAccount1.getBalance()}")
    bankAccount1.withdraw(100)
    println("Balance is: ${bankAccount1.getBalance()}")
    bankAccount1.deposit(100)
    println("Balance is: ${bankAccount1.getBalance()}")

}
