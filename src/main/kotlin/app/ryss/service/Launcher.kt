package app.ryss.service

import app.ryss.service.config.Config
import app.ryss.service.config.Environment
import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import io.sentry.Sentry
import org.slf4j.LoggerFactory

fun main() {
    val cfg = Config()
    val debug = cfg.environment == Environment.DEVELOPMENT
    val logLevel = Level.valueOf(cfg.logLevel)

    val rootLogger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as Logger
    rootLogger.level = logLevel

    if (!debug) {
        Sentry.init("${cfg.sentryDSN}?stacktrace.app.packages=app.ryss")
    } else {
        Sentry.init()
    }

    TODO("Start service")
}