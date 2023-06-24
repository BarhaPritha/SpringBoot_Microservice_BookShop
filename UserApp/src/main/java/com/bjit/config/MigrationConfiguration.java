package com.bjit.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("user-app")
public class MigrationConfiguration {

    private Boolean migration;

    public Boolean getMigration() {
        return migration;
    }

    public void setMigration(Boolean migration) {
        this.migration = migration;
    }

}
