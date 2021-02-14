package app.mobius.api

import java.net.URL

object ApiEndpoints {
    private const val PORT = 8090
    private val MOBIUS_URL = URL("http://localhost:$PORT")

    val URL_BASE = MOBIUS_URL.toString()
}