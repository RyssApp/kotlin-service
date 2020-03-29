package app.ryss.service.config

import ch.qos.logback.classic.Level
import io.github.cdimascio.dotenv.dotenv

class Config {

    private val dotenv = dotenv {
        ignoreIfMissing = true
    }

    /**
     * The [Level] of the logger.
     */
    val logLevel: String = dotenv["${PREFIX}LOG_LEVEL"] ?: Level.INFO.levelStr

    /**
     * The sentry DSN.
     */
    val sentryDSN: String = dotenv["${PREFIX}SENTRY_DSN"] ?: ""

    /**
     * The [Environment] of the current instance.
     */
    val environment: Environment =
        Environment.valueOf(dotenv["${PREFIX}ENVIRONMENT"] ?: Environment.PRODUCTION.toString())

    companion object {
        private const val PREFIX = "KOTLIN_SERVICE_"
    }
}