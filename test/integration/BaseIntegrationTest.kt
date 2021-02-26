package integration

import com.raf.ktor.demo.module
import io.ktor.application.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.junit.AfterClass
import org.junit.BeforeClass

abstract class BaseIntegrationTest {

    companion object {

        private lateinit var server: NettyApplicationEngine
        lateinit var client: HttpClient

        private const val host = "http://localhost"
        private const val port = 8080
        const val basePath = "${host}:${port}"

        @BeforeClass
        @JvmStatic
        fun setup() {
            server = embeddedServer(
                Netty,
                port = port,
                module = Application::module
            ).apply {
                start(wait = false)
            }

            client = HttpClient(CIO) {
                install(JsonFeature)
            }
        }

        @AfterClass
        @JvmStatic
        fun teardown() {
            client.close()
            server.stop(1000, 10000)
        }
    }

}
