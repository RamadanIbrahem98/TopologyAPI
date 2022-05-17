package io.RamadanIbrahem98.TopologyAPI;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class TopologyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TopologyApiApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
						.components(new Components())
						.info(new Info().title("Topology API").version("1.0.0").description("A Simple RESTful API for Handling Topologies"));
	}

}
