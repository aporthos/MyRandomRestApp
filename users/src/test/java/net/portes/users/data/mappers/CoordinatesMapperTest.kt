package net.portes.users.data.mappers

import net.portes.users.MockFactory.getCoordinatesEntity
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author amadeus.portes
 */
@RunWith(MockitoJUnitRunner.StrictStubs::class)
class CoordinatesMapperTest {

    private val mapper by lazy {
        CoordinatesMapper()
    }

    @Test
    fun `verify transform`() {
        val result = mapper.mapFrom(getCoordinatesEntity())
        assertEquals("123456", result.latitude)
        assertEquals("12345", result.longitude)
    }
}