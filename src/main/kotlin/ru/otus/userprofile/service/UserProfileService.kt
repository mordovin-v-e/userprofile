package ru.otus.userprofile.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import ru.otus.userprofile.mapper.UserMapper
import ru.otus.userprofile.mapper.UserModelMapper
import ru.otus.userprofile.dto.User
import ru.otus.userprofile.exception.ApplicationException
import ru.otus.userprofile.dto.User as UserDto
import ru.otus.userprofile.repository.UserProfileRepository

@Service
class UserProfileService(
    private val userMapper: UserMapper,
    private val userModelMapper: UserModelMapper,
    private val userProfileRepository: UserProfileRepository,
) {
    fun findUserById(id : Long): User {
        val userProfile = userProfileRepository.findByIdOrNull(id)
            ?: throw ApplicationException(HttpStatus.NOT_FOUND, "User not found by id=$id")
        
        return userModelMapper.map(userProfile)
    }

    fun create(userDto: UserDto): Long {
        val userProfile = userMapper.map(userDto)

        return userProfileRepository.save(userProfile).id
    }

    fun update(id : Long, userDto: UserDto) {
        val userProfile = userProfileRepository.findByIdOrNull(id)?.apply {
            userMapper.map(userDto, this)
        }  ?: throw ApplicationException(HttpStatus.NOT_FOUND, "User not found by id=$id")

        userProfileRepository.save(userProfile)
    }

    fun delete(id : Long) = userProfileRepository.deleteById(id)
}