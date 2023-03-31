import com.example.kotlin.KotlinTestApplication
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest(
    classes = arrayOf(KotlinTestApplication::class)
)
class KotlinTestApplicationTests {

    @Test
    fun contextLoads() {
    }

}
