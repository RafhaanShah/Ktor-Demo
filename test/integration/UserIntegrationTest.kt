package integration

import com.raf.ktor.demo.model.User
import com.raf.ktor.demo.route.PARAM_ID
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class UserIntegrationTest : BaseIntegrationTest() {

    private val user = User("Jon", "Snow", "jon.snow@mail.com")

    @Test
    fun testPostAndGetUser() = runBlocking {
        val postResponse = client.post<HttpResponse> {
            url("${basePath}/user")
            contentType(ContentType.Application.Json)
            body = user
        }
        assertEquals(HttpStatusCode.Created, postResponse.status)
        assertEquals(mapOf(PARAM_ID to 1), Json.decodeFromString<Map<String, Int>>(postResponse.readText()))

        val getResponse = client.get<HttpResponse>("${basePath}/user/1")
        assertEquals(HttpStatusCode.OK, getResponse.status)
        assertEquals(user, Json.decodeFromString<User>(getResponse.readText()))
    }

}
