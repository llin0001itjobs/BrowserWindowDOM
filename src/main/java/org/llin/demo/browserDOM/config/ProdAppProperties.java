package org.llin.demo.browserDOM.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;

@ConfigurationProperties(prefix = "prod")
@Profile("prod")
@Validated
public class ProdAppProperties {

    private final Datasource datasource = new Datasource();
    private final Jpa jpa = new Jpa();
    private final Logging logging = new Logging();

    public static class Datasource {
        @NotBlank
        private String url;

        private String driverClassName;

        // Getters and Setters
        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDriverClassName() {
            return driverClassName;
        }

        public void setDriverClassName(String driverClassName) {
            this.driverClassName = driverClassName;
        }
    }

    public static class Jpa {
        private String databasePlatform;
        private String ddlAuto;
        private boolean showSql;

        private final Properties properties = new Properties();

        public static class Properties {
            private String hibernateDialect;

            // Getters and Setters
            public String getHibernateDialect() {
                return hibernateDialect;
            }

            public void setHibernateDialect(String hibernateDialect) {
                this.hibernateDialect = hibernateDialect;
            }
        }

        // Getters and Setters
        public String getDatabasePlatform() {
            return databasePlatform;
        }

        public void setDatabasePlatform(String databasePlatform) {
            this.databasePlatform = databasePlatform;
        }

        public String getDdlAuto() {
            return ddlAuto;
        }

        public void setDdlAuto(String ddlAuto) {
            this.ddlAuto = ddlAuto;
        }

        public boolean isShowSql() {
            return showSql;
        }

        public void setShowSql(boolean showSql) {
            this.showSql = showSql;
        }

        public Properties getProperties() {
            return properties;
        }
    }

    public static class Logging {
        private final Level level = new Level();

        public static class Level {
            private String orgHibernateSQL;
            private String orgHibernateTypeDescriptorSqlBasicBinder;

            // Getters and Setters
            public String getOrgHibernateSQL() {
                return orgHibernateSQL;
            }

            public void setOrgHibernateSQL(String orgHibernateSQL) {
                this.orgHibernateSQL = orgHibernateSQL;
            }

            public String getOrgHibernateTypeDescriptorSqlBasicBinder() {
                return orgHibernateTypeDescriptorSqlBasicBinder;
            }

            public void setOrgHibernateTypeDescriptorSqlBasicBinder(String orgHibernateTypeDescriptorSqlBasicBinder) {
                this.orgHibernateTypeDescriptorSqlBasicBinder = orgHibernateTypeDescriptorSqlBasicBinder;
            }
        }

        public Level getLevel() {
            return level;
        }
    }

    // Getters for top-level nested classes
    public Datasource getDatasource() {
        return datasource;
    }

    public Jpa getJpa() {
        return jpa;
    }

    public Logging getLogging() {
        return logging;
    }
}
