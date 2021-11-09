package net.portes.myrandomrestapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import net.portes.users.data.datasources.UsersDataSourceImpl
import net.portes.users.data.repositories.UsersRepositoryImpl
import net.portes.users.data.services.UserService
import net.portes.users.domain.datasources.UsersDataSource
import net.portes.users.domain.repositories.UsersRepository
import retrofit2.Retrofit

/**
 * @author amadeus.portes
 */
@Module
@InstallIn(ApplicationComponent::class)
object UsersModule {

    @Provides
    fun providesIpcService(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)

    @Provides
    fun providesUsersDataSource(usersDataSourceImpl: UsersDataSourceImpl): UsersDataSource =
        usersDataSourceImpl

    @Provides
    fun providesUsersRepository(usersRepositoryImpl: UsersRepositoryImpl): UsersRepository =
        usersRepositoryImpl
}