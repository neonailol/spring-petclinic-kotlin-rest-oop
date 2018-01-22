package nnl.rocks.projects.spkro.pets.rest

import nnl.rocks.projects.spkro.ApplicationTest
import nnl.rocks.projects.spkro.api.CreatePetTypeRB
import nnl.rocks.projects.spkro.api.UpdatePetTypeRB
import org.junit.Test
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class UpdatePetTypeResourceTest : ApplicationTest() {

    @Test
    fun `can update existing pet type`() {

        var id = Any()

        mockMvc.perform(
            petsApi.createPetType(CreatePetTypeRB("PetTypeName"))
        ).andDo {
            id = it.response.getHeaderValue("location")
            mockMvc.perform(
                petsApi.updatePetType(id, UpdatePetTypeRB("NewPetTypeName"))
            ).andExpect(
                MockMvcResultMatchers.status().isNoContent
            )
        }.andDo {
                mockMvc.perform(
                    petsApi.getPetType(id)
                ).andExpect(
                    MockMvcResultMatchers.jsonPath("$.name").value("NewPetTypeName")
                )
            }
    }
}
