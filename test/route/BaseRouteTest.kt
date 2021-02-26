package route

import io.mockk.mockkClass
import org.junit.Rule
import org.koin.test.KoinTest
import org.koin.test.mock.MockProviderRule

abstract class BaseRouteTest : KoinTest {

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        mockkClass(clazz)
    }

}
