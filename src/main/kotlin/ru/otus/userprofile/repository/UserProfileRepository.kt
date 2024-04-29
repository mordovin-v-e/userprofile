package ru.otus.userprofile.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.otus.userprofile.entity.UserProfile

interface UserProfileRepository : JpaRepository<UserProfile, Long> { }