package me.kzv.core.tags

class CustomersRepository {
    private val customers: MutableList<Customer> = mutableListOf()

    fun contains(name: String): Boolean {
        return customers.stream().anyMatch { it.name == name }
    }

    fun persist(customer: Customer) {
        customers.add(customer)
    }
}
