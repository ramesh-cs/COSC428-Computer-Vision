package nz.govt.stats.mdt.dl.localrepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@PropertySource({ "classpath:application.properties" })
//@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "nz.govt.stats.mdt.dl.localrepo",
        entityManagerFactoryRef = "sqliteEntityManager",
        transactionManagerRef = "sqliteTransactionManager"
)
public class LocalRepoConfig {

    @Bean
    public DataSource SQLiteDataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
//        dataSource.setUrl("jdbc:sqlite:images.sqlite");
        dataSource.setUrl("jdbc:sqlite:C:\\Users\\rsivaram\\Desktop\\Net Migration Project\\DeepLearning\\TextProcessor\\src\\main\\resources\\images");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean sqliteEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(SQLiteDataSource());
        em.setPackagesToScan(new String[] { "nz.govt.stats.mdt.dl.localrepo.models" });
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        properties.put("hibernate.dialect", "org.hibernate.dialect.SQLiteDialect");
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    public PlatformTransactionManager sqliteTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory( sqliteEntityManager().getObject() );
        return transactionManager;
    }
}
