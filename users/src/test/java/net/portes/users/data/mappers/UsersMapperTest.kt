package net.portes.users.data.mappers

import net.portes.users.MockFactory.getUserResponse
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author amadeus.portes
 */
@RunWith(MockitoJUnitRunner.StrictStubs::class)
class UsersMapperTest {

    private val mapper by lazy {
        UsersMapper(LocationMapper(CoordinatesMapper(), StreetMapper()))
    }

    @Test
    fun `verify transform`() {
        val result = mapper.mapFrom(getUserResponse())
        Assert.assertEquals("0170-1776917", result.cell)
        Assert.assertEquals("hubertine.hausner@example.com", result.email)
        Assert.assertEquals("female", result.gender)
        Assert.assertEquals("Mexico", result.location?.city)
        Assert.assertEquals("12345", result.location?.coordinates?.longitude)
        Assert.assertEquals("123456", result.location?.coordinates?.latitude)
        Assert.assertEquals("Ms Hubertine Hausner", result.name)
        Assert.assertEquals("DE", result.nat)
        Assert.assertEquals("0718-4389707", result.phone)
        Assert.assertEquals("url", result.picture)
    }
}