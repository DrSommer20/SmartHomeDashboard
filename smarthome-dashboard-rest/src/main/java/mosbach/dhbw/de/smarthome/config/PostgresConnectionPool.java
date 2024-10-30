package mosbach.dhbw.de.smarthome.config;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;


public class PostgresConnectionPool {

    private static final String databaseConnectionnUrl = System.getenv("DB_CONN");
    private static final String username = System.getenv("DB_USER");
    private static final String password = System.getenv("DB_PASS");


    private static BasicDataSource dataSource;

    static {
        dataSource = new BasicDataSource();
        dataSource.setUrl(databaseConnectionnUrl);
        dataSource.setUsername(username); // Add username
        dataSource.setPassword(password); // Add password
        dataSource.setDriverClassName("org.postgresql.Driver"); // Set driver class name
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxOpenPreparedStatements(100);
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}