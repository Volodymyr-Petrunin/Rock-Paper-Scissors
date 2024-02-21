package org.game.rps.service;

import org.game.rps.GameConfiguration;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootConfiguration
@EntityScan(basePackages = "org.game.rps.domain")
@ComponentScan(basePackages = {"org.game.rps.service", "org.game.rps.repository"}, basePackageClasses = GameConfiguration.class)
@EnableAutoConfiguration
public class ServiceTestConfiguration {
}
