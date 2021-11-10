package net.portes.users.data.mappers

import net.portes.users.MockFactory.getLocationEntity
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author amadeus.portes
 */
@RunWith(MockitoJUnitRunner.StrictStubs::class)
class LocationMapperTest {

    private val mapper by lazy {
        LocationMapper(CoordinatesMapper(), StreetMapper())
    }

    @Test
    fun `verify transform`() {
        val result = mapper.mapFrom(getLocationEntity())
        assertEquals("Mexico", result.city)
        assertEquals("123456", result.coordinates?.latitude)
        assertEquals("12345", result.coordinates?.longitude)
        assertEquals("Mexico", result.country)
        assertEquals(12345, result.postcode)
        assertEquals("Mexico", result.state)
        assertEquals("Alameda", result.street?.name)
        assertEquals(12345, result.street?.number)
    }
}