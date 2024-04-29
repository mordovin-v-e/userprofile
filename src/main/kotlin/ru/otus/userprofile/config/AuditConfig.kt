package ru.otus.userprofile.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.util.Optional

@Configuration
@EnableJpaAuditing
class AuditConfig {

    @Bean
    fun auditProvider() : AuditorAware<String> = AuditorAware { Optional.of("system") }
}