package net.portes.shared.data

/**
 * @author amadeus.portes
 */
interface BaseMapper<in IN, out OUT> {
    fun mapFrom(from: IN): OUT
}

interface BothBaseMapper<IN, OUT> {
    fun mapFrom(from: IN): OUT
    fun mapTo(from: OUT): IN
}

interface BaseDefaultMapper<IN> {
    fun toDefault(): IN
}