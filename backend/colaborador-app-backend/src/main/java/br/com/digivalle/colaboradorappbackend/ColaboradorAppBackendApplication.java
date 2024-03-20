package br.com.digivalle.colaboradorappbackend;

import br.com.digivalle.colaboradorappbackend.config.database.DatabaseMigration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ColaboradorAppBackendApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ColaboradorAppBackendApplication.class, args);

		DatabaseMigration databaseMigration = context.getBean(DatabaseMigration.class);

		databaseMigration.migrateDatabase();
	}
}
