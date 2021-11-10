package net.portes.users.data.mappers

import net.portes.users.MockFactory.getStreetEntity
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author amadeus.portes
 */
@RunWith(MockitoJUnitRunner.StrictStubs::class)
class StreetMapperTest {

    private val mapper by lazy {
        StreetMapper()
    }

    @Test
    fun `verify transform`() {
        val result = mapper.mapFrom(getStreetEntity())
        assertEquals("Alameda", result.name)
        assertEquals(12345, result.number)
    }
}