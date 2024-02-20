package org.game.rps;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class GameConfiguration {
    @Bean
    public Random random(){
        return new Random();
    }
}
