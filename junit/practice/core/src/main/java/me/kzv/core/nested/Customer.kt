package me.kzv.core.nested

import java.util.*

class Customer private constructor(builder: Builder) {
    private val gender: Gender = builder.gender
    val firstName: String = builder.firstName
    val lastName: String = builder.lastName

    val middleName: String?
    val becomeCustomer: Date?

    class Builder(gender: Gender, val firstName: String, val lastName: String) {
        internal val gender: Gender = gender

        var middleName: String? = null
        var becomeCustomer: Date? = null

        fun withMiddleName(middleName: String?): Builder {
            this.middleName = middleName
            return this
        }

        fun withBecomeCustomer(becomeCustomer: Date?): Builder {
            this.becomeCustomer = becomeCustomer
            return this
        }

        fun build(): Customer {
            return Customer(this)
        }
    }

    init {
        this.middleName = builder.middleName
        this.becomeCustomer = builder.becomeCustomer
    }

    fun getGender(): Gender {
        return gender
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val customer = other as Customer
        return gender === customer.gender &&
                firstName == customer.firstName &&
                lastName == customer.lastName &&
                middleName == customer.middleName &&
                becomeCustomer == customer.becomeCustomer
    }

    override fun hashCode(): Int {
        return Objects.hash(gender, firstName, lastName, middleName, becomeCustomer)
    }
}
