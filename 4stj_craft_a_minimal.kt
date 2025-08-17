import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration
import java.time.Instant

@SpringBootApplication
class CraftAMinimalWebAppNotifierApplication

fun main(args: Array<String>) {
    runApplication<CraftAMinimalWebAppNotifierApplication>(*args)
}

@RestController
class NotifierController {

    @GetMapping("/notifications")
    fun getNotifications(): Flux<Notification> {
        return Flux.interval(Duration.ofSeconds(1))
            .map { Notification("Notification ${it + 1}", Instant.now()) }
    }
}

data class Notification(val message: String, val timestamp: Instant)

@Bean
fun notificationRepository(): MutableList<Notification> {
    return mutableListOf<Notification>()
}