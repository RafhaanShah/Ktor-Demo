package route

import com.raf.ktor.demo.model.User
import com.raf.ktor.demo.module
import com.raf.ktor.demo.route.PARAM_ID
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Test
import kotlin.test.assertEquals

class UserRouteTest {

    private val user = User("Jon", "Snow", "jon.snow@mail.com")

    @Test
    fun testPostAndGetUser() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Post, "/user") {
                addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                setBody(Json.encodeToString(user))
            }.apply {
                assertEquals(HttpStatusCode.Created, response.status())
                assertEquals(Json.encodeToString(mapOf(PARAM_ID to 1)), response.content)
            }

            handleRequest(HttpMethod.Get, "/user/1").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals(Json.encodeToString(user), response.content)
            }
        }
    }

}
