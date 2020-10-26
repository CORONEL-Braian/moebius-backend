package app.mobius.data.data_access

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
 *  . https://www.baeldung.com/properties-with-spring
 *
 *  . TODO: https://developer.okta.com/blog/2019/02/01/spring-hibernate-guide
 */
@Configuration
@EnableTransactionManagement
open class PersistenceJPAConfig {

    /**
     * Source:
     * https://stackoverflow.com/a/64531683/5279996
     */
    @Bean
    open fun entityManagerFactory() : LocalContainerEntityManagerFactoryBean {
        val em = LocalContainerEntityManagerFactoryBean()
        em.dataSource = dataSource()
        em.setPackagesToScan("app.mobius.domain.entity")

        val vendorAdapter: JpaVendorAdapter = HibernateJpaVendorAdapter()
        em.jpaVendorAdapter = vendorAdapter
        em.setJpaProperties(additionalProperties())

        return em
    }

    @Bean
    open fun dataSource(): DataSource {
        val dataSource = DriverManagerDataSource()

//        dataSource.driverClassName = "org.postgresql.Driver"  TODO: Check postgreSQL
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

        properties.setProperty("hibernate.show_sql", "true")
        return properties
    }

}

