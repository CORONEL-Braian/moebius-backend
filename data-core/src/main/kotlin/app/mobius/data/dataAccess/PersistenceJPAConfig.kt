package app.mobius.data.dataAccess

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
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
 *
 *  . TODO: https://developer.okta.com/blog/2019/02/01/spring-hibernate-guide
 */
@Configuration
@EnableTransactionManagement
open class PersistenceJPAConfig {

    @Autowired
    private lateinit var env: Environment

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

    /**
     * Sources:
     *  . https://stackoverflow.com/a/40511351/5279996
     *  . https://stackoverflow.com/a/23678638/5279996
     */
    private fun additionalProperties(): Properties {
        val properties = Properties()
        properties.setProperty("hibernate.hbm2ddl.auto", "validate")
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect")

//        Naming Strategy in Spring
        properties.setProperty("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy::class.java.name)
        properties.setProperty("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy::class.java.name)

        properties.setProperty("hibernate.show_sql", "true")
        return properties
    }

    @Bean
    open fun dataSource(): DataSource {
        val dataSource = DriverManagerDataSource()

        dataSource.url = env.getProperty("spring.datasource.url")
        dataSource.username = env.getProperty("spring.datasource.username")
        dataSource.password = env.getProperty("spring.datasource.password")

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

}