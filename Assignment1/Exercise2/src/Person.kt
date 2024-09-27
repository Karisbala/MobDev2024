open class Person (val name: String, var age: Int, var email: String) {
    override fun toString(): String {
        return "Person name='$name', age=$age, email='$email'"
    }
}