package app.mobius

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.Entity

@Entity
data class Some(val name: String)

@Repository
interface SomeRepository: CrudRepository<Some, Long>

@RestController
@RequestMapping
class SomeController {
    @Autowired
    private lateinit var someRepository: SomeRepository
}