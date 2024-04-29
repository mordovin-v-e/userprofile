package ru.otus.userprofile.mapper

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import ru.otus.userprofile.config.DefaultMapperConfig
import ru.otus.userprofile.entity.UserProfile
import ru.otus.userprofile.dto.User as UserDto

@Mapper(config = DefaultMapperConfig::class)
interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    fun map(user: UserDto) : UserProfile

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    fun map(userDto: UserDto, @MappingTarget userProfile: UserProfile)
}

@Mapper(config = DefaultMapperConfig::class)
interface UserModelMapper {
    fun map(userProfile: UserProfile) : UserDto
}