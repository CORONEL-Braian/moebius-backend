package app.mobius.data.dataAccess

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Value
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import kotlin.properties.Delegates

@RunWith(SpringRunner::class)
@TestPropertySource("/foo.properties")
class FilePropertyInjectionUnitTest {

    @delegate:Value("\${foo}")  var foo by Delegates.notNull<Int>()

    @Test
    fun whenFilePropertyProvided_thenProperlyInjected() {
        assertThat(foo).isEqualTo("bar")
    }
}
