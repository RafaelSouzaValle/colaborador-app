package br.com.digivalle.colaboradorappbackend.config.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Component
public class DatabaseMigration {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseMigration(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void migrateDatabase() {
        if (!tableExists("Colaborador")) {
            executeScript("db/migration/V1__Create_Colaborador_Table.sql");
        }
    }

    public boolean tableExists(String tableName) {
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, tableName);
        return count > 0;
    }

    private void executeScript(String scriptPath) {
        try {
            ClassPathResource resource = new ClassPathResource(scriptPath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            String script = reader.lines().collect(Collectors.joining("\n"));
            jdbcTemplate.execute(script);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
