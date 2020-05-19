package io.petros.movies.app.runner

import io.petros.movies.app.App
import io.petros.movies.test.utils.MOCK_WEB_SERVER_URL

class InstrumentedTestApp : App() {

    override fun baseUrl() = MOCK_WEB_SERVER_URL

}
