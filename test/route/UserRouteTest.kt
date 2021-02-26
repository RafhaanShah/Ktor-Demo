package route

import com.raf.ktor.demo.model.User
import com.raf.ktor.demo.module
import com.raf.ktor.demo.repository.UserRepository
import com.raf.ktor.demo.route.PARAM_ID
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import io.mockk.every
import io.mockk.mockkClass
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.koin.java.KoinJavaComponent.inject
import org.koin.test.KoinTest
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock

class UserRouteTest : KoinTest {

    private val user = User("Jon", "Snow", "jon.snow@mail.com")
    private val userId = 10

    private val userRepository: UserRepository by inject(UserRepository::class.java)

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        mockkClass(clazz)
    }

    @Test
    fun testPostAndGetUser() {

        withTestApplication(Application::module) {
        declareMock<UserRepository>()

            every { userRepository.addUser(user) } returns userId
            every { userRepository.getUser(userId) } returns user

            handleRequest(HttpMethod.Post, "/user") {
                addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                setBody(Json.encodeToString(user))
            }.apply {
                assertEquals(HttpStatusCode.Created, response.status())
                assertEquals(Json.encodeToString(mapOf(PARAM_ID to 10)), response.content)
            }

            handleRequest(HttpMethod.Get, "/user/$userId").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals(Json.encodeToString(user), response.content)
            }
        }
    }

}
