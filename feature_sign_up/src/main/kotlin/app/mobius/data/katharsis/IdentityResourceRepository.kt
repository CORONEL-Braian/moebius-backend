package app.mobius.data.katharsis

import app.mobius.infrastructure.dto.PersonDto
import io.katharsis.queryspec.QuerySpec
import io.katharsis.repository.ResourceRepositoryV2
import io.katharsis.resource.list.ResourceList
import org.springframework.stereotype.Component

@Component
class IdentityResourceRepository: ResourceRepositoryV2<PersonDto, Long> {
    override fun getResourceClass(): Class<PersonDto> {
        return PersonDto::class.java
    }

    override fun findOne(id: Long?, querySpec: QuerySpec?): PersonDto {
        TODO("Not yet implemented")
    }

    override fun findAll(querySpec: QuerySpec?): ResourceList<PersonDto> {
        TODO("Not yet implemented")
    }

    override fun findAll(ids: MutableIterable<Long>?, querySpec: QuerySpec?): ResourceList<PersonDto> {
        TODO("Not yet implemented")
    }

    override fun <S : PersonDto?> save(entity: S): S {
        TODO("Not yet implemented")
    }

    override fun <S : PersonDto?> create(entity: S): S {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long?) {
        TODO("Not yet implemented")
    }
}