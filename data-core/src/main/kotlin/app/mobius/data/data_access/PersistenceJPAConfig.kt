package app.mobius.data.data_access

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.sql.DataSource

/**
 * Source:
 *  . https://www.baeldung.com/the-persistence-layer-with-spring-and-jpa
 *  . https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto-data-access
 */
@Configuration
@EnableTransactionManagement
open class PersistenceJPAConfig {

    /**
     * Source:
     * https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto-use-custom-entity-manager
     */
    @Bean
    open fun entityManagerFactory() : LocalContainerEntityManagerFactoryBean {
        val em = LocalContainerEntityManagerFactoryBean()
        em.dataSource = dataSource()

        val vendorAdapter: JpaVendorAdapter = HibernateJpaVendorAdapter()
        em.setJpaVendorAdapter(vendorAdapter)
        em.setJpaProperties(additionalProperties())

        return em
    }

    @Bean
    open fun dataSource(): DataSource {
        val dataSource = DriverManagerDataSource()

        dataSource.driverClassName = "org.postgresql.Driver"
        dataSource.url = "jdbc:postgresql://localhost:5432/"
        dataSource.username = ""
        dataSource.password = ""

        return dataSource
    }

    @Bean
    open fun transactionManager(): PlatformTransactionManager? {
        val transactionManager = JpaTransactionManager()
        transactionManager.entityManagerFactory = entityManagerFactory().getObject()
        return transactionManager
    }

    @Bean
    open fun exceptionTranslation(): PersistenceExceptionTranslationPostProcessor? {
        return PersistenceExceptionTranslationPostProcessor()
    }

    private fun additionalProperties(): Properties {
        val properties = Properties()
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop")
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect")
        return properties
    }

}

