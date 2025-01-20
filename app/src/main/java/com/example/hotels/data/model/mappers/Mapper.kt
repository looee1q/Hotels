package com.example.hotels.data.model.mappers

interface Mapper<I, out O> {
    fun map(input: I): O
}