package nnl.rocks.projects.spkro.pets

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import nnl.rocks.projects.spkro.core.toPageInfo
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Api
@RestController
class GetPetTypesResource(
    private val getPetTypesUseCase: GetPetTypesUseCase
) {

    @GetMapping("/api/v1/pets/types")
    @ApiOperation("Get list of available pet types")
    operator fun invoke(

        @ApiParam("query string for filtering")
        @RequestParam(required = false)
        query: String?,

        @ApiParam("pageable params, default is first 50 entries")
        @PageableDefault(size = 50)
        pageable: Pageable
    ): PetTypesVM {
        val types = getPetTypesUseCase(query, pageable).map { it.toPetTypeVM() }
        return PetTypesVM(types.toPageInfo(), types.content)
    }
}