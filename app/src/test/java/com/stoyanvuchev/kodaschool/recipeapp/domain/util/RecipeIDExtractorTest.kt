package com.stoyanvuchev.kodaschool.recipeapp.domain.util

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.Test

class RecipeIDExtractorTest {

    @Test
    fun `RecipeIDExtractor extracts RecipeID`() {

        val uri = "http://www.edamam.com/ontologies/edamam.owl#recipe_d04a8f9d3b6662a802c17e1a2e5f0f1b"
        val actual = RecipeIDExtractor.extractId(uri)
        val expected = "recipe_d04a8f9d3b6662a802c17e1a2e5f0f1b"

        assertThat(actual).isEqualTo(expected)

    }

}