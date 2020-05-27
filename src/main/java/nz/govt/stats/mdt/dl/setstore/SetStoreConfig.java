package nz.govt.stats.mdt.dl.setstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
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
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "nz.govt.stats.mdt.dl.setstore",
        entityManagerFactoryRef = "mssqlEntityManager",
        transactionManagerRef = "mssqlTransactionManager"
)
public class SetStoreConfig {
    @Autowired
    private Environment env;

    @Bean
    @Primary
    public DataSource MssqlDataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        return dataSource;
    }

    @Bean(name = "mssqlEntityManager")
    @Primary
    public LocalContainerEntityManagerFactoryBean mssqlEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(MssqlDataSource());
        em.setPackagesToScan(new String[] { "nz.govt.stats.mdt.dl.setstore.models" });
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.SQLServer2012Dialect");
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean(name = "mssqlTransactionManager")
    @Primary
    public PlatformTransactionManager mssqlTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory( mssqlEntityManager().getObject() );
        return transactionManager;
    }
}
