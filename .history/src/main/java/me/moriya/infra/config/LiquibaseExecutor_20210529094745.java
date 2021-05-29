package me.moriya.infra.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.util.ExceptionUtil;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.DatabaseConnection;
import liquibase.database.DatabaseFactory;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;

@ApplicationScoped
public class LiquibaseExecutor {

    private static final Logger logger = LoggerFactory.getLogger(LiquibaseExecutor.class);
    
    @ConfigProperty(name = "quarkus.liquibase.migrate-at-start")
	boolean isMigrateAtStart;

    @ConfigProperty(name = "quarkus.datasource.jdbc.url")
	String datasourceUrl;
    
	@ConfigProperty(name = "quarkus.datasource.username")
	String datasourceUsername;

	@ConfigProperty(name = "quarkus.datasource.password")
	String datasourcePassword;
        
    @ConfigProperty(name = "quarkus.liquibase.change-log")
	String changeLogLocation;

    public void runLiquibaseMigration(@Observes StartupEvent event) throws LiquibaseException {
        if()
        Liquibase liquibase = null;
        try {
            ResourceAccessor resourceAccessor = new ClassLoaderResourceAccessor(Thread.currentThread().getContextClassLoader());
            DatabaseConnection conn = DatabaseFactory.getInstance().openConnection(datasourceUrl, datasourceUsername, datasourcePassword, null, resourceAccessor);
            liquibase = new Liquibase(changeLogLocation, resourceAccessor, conn);
            liquibase.update(new Contexts(), new LabelExpression());
        } catch (Exception e) {
            logger.error("Liquibase Migration Exception: " + ExceptionUtil.generateStackTrace(e));
        }
        finally {
            if(liquibase!=null)
            {
                liquibase.close();
            }
        }
	}

}
