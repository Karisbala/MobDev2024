class BankAccount(private var balance: Int) {
    fun deposit(amount: Int) {
        println("Depositing $amount")
        balance += amount
    }
    fun withdraw(amount: Int) {
        println("Withdrawing $amount")
        if (balance >= amount) {
            balance -= amount
        }
        else println("Not enough balance to withdraw, current balance is $balance")
    }
    fun getBalance(): Int {
        return balance
    }
}