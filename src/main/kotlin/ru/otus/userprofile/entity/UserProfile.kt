package ru.otus.userprofile.entity

import jakarta.persistence.Entity
import jakarta.validation.constraints.NotBlank
import ru.otus.userprofile.entity.audit.Auditable

@Entity
class UserProfile (
    @NotBlank
    var username: String,

    @NotBlank
    var lastName: String,

    @NotBlank
    var firstName: String,

    @NotBlank
    var email: String,

    @NotBlank
    var phone: String
) : Auditable()