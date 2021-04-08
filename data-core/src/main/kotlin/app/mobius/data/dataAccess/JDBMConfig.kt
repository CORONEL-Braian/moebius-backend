package app.mobius.data.dataAccess

import app.mobius.io.ResourceUtils.getFile
import org.hibernate.HibernateException
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.Transaction
import org.hibernate.cfg.Configuration
import org.reflections.Reflections
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy
import javax.persistence.*


/**
 * Java Data Base Managment
 * Source:
 *  https://docs.jboss.org/hibernate/core/3.3/reference/en/html/session-configuration.html
 */
class JDBMConfig {

    object Hibernate {

        private const val HIBERNATE_CONFIGURATION = "secret-hibernate.cfg.xml"

       /* @Bean(name = ["entityManagerFactory"])
        fun sessionFactory(): LocalSessionFactoryBean? {
            return LocalSessionFactoryBean()
        }
*/
        /**
         * @param operation: e.g: save/update/read
         * @param message: successfully
         */
        @Throws(HibernateException::class)
        fun executeQuery(session: Session, message: String = "", operation: () -> Unit) {
            try {
                beginTransaction(session)
                executeOperation(session, operation)
                println("MetaModel: ${session.metamodel.managedTypes}")
                println("MetaModel: ${session.metamodel}")
                commitTransaction(session, message)
            } catch (e: HibernateException) {
                e.printStackTrace()
            } finally {
                endSession(session, session.sessionFactory)
            }
        }

        fun executeHQL() {

        }

        /**
         * Open the session using hibernate cfg for only mapped entity
         * @param canonicalName: e.g: secret-hibernate.cfg.xml
         */
        fun openSessionForOnly(annotatedClass: Class<*>, canonicalName: String = HIBERNATE_CONFIGURATION) : Session {
            val sessionFactory = getSessionFactoryForOnly(annotatedClass, canonicalName)
            return sessionFactory.openSession()
        }


        /**
         * Open the session using hibernate cfg for all mapped entities
         */
        fun openSession(resource: String = HIBERNATE_CONFIGURATION) : Session {
            val sessionFactory = autoScanEntities(resource)
            return sessionFactory.openSession()
        }

        /**
         * Generate session factory for a simple entity
         * PRE: Configure entity mapping in open-persistence.xml necessary for the class hierarchy
         * OBS: Session Factory puede tener varias sesiones abiertas.
         * @param annotatedClass The class containing annotations
         * @param canonicalName: e.g: secret-hibernate.cfg.xml
         */
        @Throws(HibernateException::class)
        fun getSessionFactoryForOnly(annotatedClass: Class<*>, canonicalName: String) : SessionFactory {
            return Configuration()
                    .configure(getFile("data-core", canonicalName))
                    .addAnnotatedClass(annotatedClass)
                    .buildSessionFactory()
        }

        /**
         * Generate session factory for a lot of mapped entities and using naming strategy
         * Add all the classes annotated with Entity in the configuration
         * Precondition: The prefix contains classes with a configured hibernate mapping
         * OBS: Spring could be auto scan by packages with LocalContainerEntityManagerFactoryBean
         * Source:
         *  . Reflections: https://stackoverflow.com/a/60015879/5279996
         *  . Naming strategy: https://stackoverflow.com/a/57617237/5279996
         */
        private fun autoScanEntities(canonicalName: String) : SessionFactory {
            val configuration = Configuration()
                    .configure(getFile("data-core", canonicalName))

            /**
             * Auto Scan Entities
             *
             * TODO: Should be the entities packages per features:
             *  [
             *    app.mobius.domain.entity.*,
             *    app.mobius.xFeature.entity.*,
             *    app.mobius.YFeature.entity.*,
             *  ]
             */
            val reflections = Reflections(PackagesToScan.SUMMATION)
            val importantClasses: Set<Class<*>> = reflections.getTypesAnnotatedWith(Entity::class.java)

            var i = 0
            println("-------")
            for (clazz in importantClasses) {
                i += 1
                println("Mapped entities $i: $clazz")
                configuration.addAnnotatedClass(clazz)
            }
            println("-------")

//            Naming Strategy: from camelCase/snake_case of backend to snake_case db attributes
            configuration.setImplicitNamingStrategy(SpringImplicitNamingStrategy.INSTANCE)
            configuration.setPhysicalNamingStrategy(SpringPhysicalNamingStrategy())

            return configuration.buildSessionFactory()
        }

        /**
         * Begin a transaction.
         * OBS: The transaction can have several operations
         */
        private fun beginTransaction(session: Session) : Transaction =
                session.beginTransaction()

        /**
         * Execute an operation
         * @param operation: e.g: save/update/read
         */
        private fun executeOperation(session: Session, operation: () -> Unit): Session {
            operation()
            return session
        }

        /**
         * Commit transaction
         * Obs: A rollback could also be performed as an alternative
         */
        private fun commitTransaction(session: Session, message: String = "") : Unit =
                session.transaction.commit().apply {
                    println("JDBM: commitTransaction to db successfully $message")
                }

        /**
         * End session regardless of the success of the operations
         */
        private fun endSession(session: Session, sessionFactory: SessionFactory) {
            session.close()
            sessionFactory.close()
        }

    }

    /**
     * Observation: persistence.xml file dont work for auto scan
     *      . Hibernate only scans for JPA Entities inside jar files, and not in classes/ folders !!!
     * Source:
     *  . https://stackoverflow.com/a/44816353/5279996
     *  . https://stackoverflow.com/a/41845759/5279996
     *  . https://stackoverflow.com/a/13756652/5279996
     */
    @SuppressWarnings
    private object JPA {

        fun executeQuery(session: Session, operation: () -> Unit) {
            val entityManager = createEntityManager()
            beginTransaction(entityManager)
            executeOperation(session, operation)
            closeTransaction(entityManager)
        }

        /**
         * Open session without entities
         */
        fun openSession() = createEntityManager().unwrap(Session::class.java)

        fun createEntityManager(): EntityManager {
            val entityManagerFactory = Persistence.createEntityManagerFactory(
                    "hypersistence-optimizer"
            )
            return entityManagerFactory.createEntityManager()
        }

        private fun beginTransaction(entityManager: EntityManager) {
            entityManager.transaction.begin()
        }

        /**
         * Execute an operation
         * @param operation: e.g: save/update/read
         */
        private fun executeOperation(session: Session, operation: () -> Unit): Session {
            operation()
            return session
        }

        private fun closeTransaction(entityManager: EntityManager) {
            entityManager.transaction.commit()
            entityManager.close()
        }
    }

}