package com.gemidroid.comicshow.data.repository

import com.gemidroid.comicshow.data.models.Comic
import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ComicsRepositoryImpTest {

    @Mock
    var comics: List<Comic>? = null

    @BeforeEach
    fun setup() {
        //if we don't call below, we will get NullPointerException
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun test() {
        `when`(comics?.size).thenReturn(5)
        assertTrue(comics?.size == 5)
    }
}