class Employee(name: String, age: Int, email: String, val salary: Int) : Person(name, age, email) {
    override fun toString(): String {
        return "${super.toString()}, salary= $salary"
    }
}