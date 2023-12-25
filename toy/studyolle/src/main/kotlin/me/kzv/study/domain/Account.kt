package me.kzv.study.domain

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
data class Account(
    @Id @GeneratedValue
    val id: Long? = null,

    @Column(unique = true)
    var email: String,

    @Column(unique = true)
    var nickname: String,

    var password: String,

    var emailVerified: Boolean? = null,

    var emailCheckToken: String? = null,

    var joinedAt: LocalDateTime? = null,

    var bio: String? = null,

    var url: String? = null,

    var occupation: String? = null,

    var location: String? = null,

    @Lob @Basic(fetch = FetchType.EAGER)
    var profileImage: String? = null,

    var studyCreatedByEmail: Boolean = false,

    var studyCreatedByWeb: Boolean = true,

    var studyEnrollmentResultByEmail: Boolean = false,

    var studyEnrollmentResultByWeb: Boolean = true,

    var studyUpdatedResultByEmail: Boolean = false,

    var studyUpdatedResultByWeb: Boolean = true,
){
    fun generateEmailCheckToken() {
        this.emailCheckToken = UUID.randomUUID().toString()
    }

}
