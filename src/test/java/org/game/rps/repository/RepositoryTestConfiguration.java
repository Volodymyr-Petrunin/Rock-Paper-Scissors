package org.game.rps.repository;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootConfiguration
@EntityScan(basePackages = "org.game.rps.domain")
@ComponentScan(basePackages = {"org.game.rps.repository"})
@EnableJpaRepositories(basePackages = "org.game.rps.repository")
@EnableAutoConfiguration
public class RepositoryTestConfiguration {

}
