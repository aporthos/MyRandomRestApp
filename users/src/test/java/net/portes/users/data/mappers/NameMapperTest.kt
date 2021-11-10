package net.portes.users.data.mappers

import net.portes.users.MockFactory.getNameEntity
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author amadeus.portes
 */
@RunWith(MockitoJUnitRunner.StrictStubs::class)
class NameMapperTest {

    private val mapper by lazy {
        NameMapper()
    }

    @Test
    fun `verify transform`() {
        val result = mapper.mapFrom(getNameEntity())
        assertEquals("Ms", result.title)
        assertEquals("Hubertine", result.first)
        assertEquals("Hausner", result.last)
    }

}