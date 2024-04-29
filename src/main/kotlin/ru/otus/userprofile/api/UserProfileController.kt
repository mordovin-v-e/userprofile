package ru.otus.userprofile.api

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import ru.otus.userprofile.dto.User as UserDto
import ru.otus.userprofile.service.UserProfileService

@Component
class UserProfileController(
    private val userProfileService: UserProfileService
) : UserApiDelegate {

    override fun createUser(user: UserDto): ResponseEntity<Long> {
        return ResponseEntity.ok(userProfileService.create(user))
    }

    override fun deleteUser(userId: Long): ResponseEntity<Unit> {
        return ResponseEntity.ok(userProfileService.delete(userId))
    }

    override fun findUserById(userId: Long): ResponseEntity<UserDto> {
        return ResponseEntity.ok(userProfileService.findUserById(userId))
    }

    override fun updateUser(userId: Long, user: UserDto): ResponseEntity<Unit> {
        return ResponseEntity.ok(userProfileService.update(userId, user))
    }
}