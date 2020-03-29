package app.ryss.service.core

import app.ryss.service.config.Config
import app.ryss.service.metrics.MemoryMetrics
import com.influxdb.client.InfluxDBClientFactory

/**
 * The main service class.
 * @property cfg the [Config] for this service.
 */
class Service(private val cfg: Config) {

    private val memoryMetrics: MemoryMetrics?

    init {
        memoryMetrics = if (cfg.enableMetrics) {
            val influxDBClient = InfluxDBClientFactory.create(cfg.influxDbAddress, cfg.influxDbToken.toCharArray())
            MemoryMetrics(influxDBClient, cfg.influxDbBucket, cfg.influxDbOrg)
        } else {
            null
        }
    }

    /**
     * Starts the service.
     */
    suspend fun start() {
        memoryMetrics?.start()
    }

    companion object {
        /**
         * The name of the service.
         */
        const val NAME = "service_name"
    }
}