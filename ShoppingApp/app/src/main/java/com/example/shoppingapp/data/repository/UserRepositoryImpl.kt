package com.example.shoppingapp.data.repository

import com.example.shoppingapp.data.local.dao.UserDao
import com.example.shoppingapp.data.mapper.toDomain
import com.example.shoppingapp.data.mapper.toEntity
import com.example.shoppingapp.domain.model.User
import com.example.shoppingapp.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun register(username: String, email: String, password: String): User {
        return withContext(Dispatchers.IO) {
            val userId = UUID.randomUUID().toString()
            val passwordHash = password.reversed()
            val userEntity = User(
                userId = userId,
                username = username,
                email = email
            ).toEntity(passwordHash)
            userDao.insertUser(userEntity)
            userEntity.toDomain()
        }
    }

    override suspend fun login(email: String, password: String): User {
        return withContext(Dispatchers.IO) {
            val userEntity = userDao.getUserByEmail(email)
                ?: throw Exception("User not found")

            val passwordHash = password.reversed()
            if (userEntity.passwordHash == passwordHash) {
                userEntity.toDomain()
            } else {
                throw Exception("Invalid credentials")
            }
        }
    }

    override suspend fun getUserById(userId: String): User? {
        return withContext(Dispatchers.IO) {
            userDao.getUserById(userId)?.toDomain()
        }
    }
}