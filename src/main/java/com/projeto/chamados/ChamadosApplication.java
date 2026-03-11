package com.projeto.chamados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.time.Clock;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChamadosApplication {

    public static void main(String[] args) {
		SpringApplication.run(ChamadosApplication.class, args);
    
    }
        
    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }


}
