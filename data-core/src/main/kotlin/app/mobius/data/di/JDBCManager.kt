package app.mobius.data.di

import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.Transaction
import org.hibernate.cfg.Configuration
import org.reflections.Reflections
import java.util.*
import javax.persistence.*


/**
 *
 * Source: https://docs.jboss.org/hibernate/core/3.3/reference/en/html/session-configuration.html
 *
 * TODO:
 * https://stackoverflow.com/questions/970573/hibernate-error-cannot-resolve-table
 * https://stackoverflow.com/questions/12420996/intellij-idea-highlights-entity-class-names-with-cannot-resolve-symbol-in-jpq
 * https://www.baeldung.com/hibernate-mappingexception-unknown-entity
 */
class JDBCManager {

    @PersistenceUnit(name = "hypersistence-optimizer")
    private var entityManagerFactory: EntityManagerFactory? = null


    object HibernateCfg {

        /**
         * @param operation: e.g: save/update/read
         * @param message: successfully
         */
        fun executeQuery(session: Session, message: String, operation: () -> Unit) {
            try {
                beginTransaction(session)
                executeOperation(session, operation)
                commitTransaction(session, message)
            } finally {
                endSession(session, session.sessionFactory)
            }
        }

        /**
         * Open the session using hibernate cfg for only mapped entity
         */
        fun openSession(annotatedClass: Class<*>, resource: String = "hibernate.cfg.xml") : Session {
            val sessionFactory = generateSessionFactory(annotatedClass, resource)
            return sessionFactory.openSession()
        }


        /**
         * Open the session using hibernate cfg for all mapped entities
         */
        fun openSessionWithCfgForAll(resource: String = "hibernate.cfg.xml") : Session {
            val sessionFactory = autoScanEntities(resource)
            return sessionFactory.openSession()
        }

        /**
         * Generate session configuration for a simple entity
         * PRE: Configure entity mapping in open-persistence.xml necessary for the class hierarchy
         * OBS: Session Factory puede tener varias sesiones abiertas.
         * @param annotatedClass The class containing annotations
         * @param resource with extension .cfg.xml
         */
        private fun generateSessionFactory(annotatedClass: Class<*>, resource: String) : SessionFactory =
                Configuration()
                        .configure(resource)
                        .addAnnotatedClass(annotatedClass)
                        .buildSessionFactory()

        /**
         * Generate session factory for a lot of mapped entities
         * TODO: Ver si puedo EVITAR usar reflexion usando el archivo de persistencia que deberia hacerlo
         * Add all the classes annotated with Entity in the configuration
         * Precondition: The prefix contains classes with a configured hibernate mapping
         * Source: https://stackoverflow.com/a/60015879/5279996
         */
        private fun autoScanEntities(resource: String) : SessionFactory {
            val configuration = Configuration()
                    .configure(resource)
            val reflections = Reflections("app.mobius.domain.mapper")
            val importantClasses: Set<Class<*>> = reflections.getTypesAnnotatedWith(Entity::class.java)
            var i = 0
            println("-------")
            for (clazz in importantClasses) {
                i += 1
                println("Mapped classes $i: $clazz")
                configuration.addAnnotatedClass(clazz)
            }
            println("-------")
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
        private fun commitTransaction(session: Session, message: String) : Unit =
                session.transaction.commit().apply {
                    println("commitTransaction to db successfully: $message")
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
     * Source: https://stackoverflow.com/a/41845759/5279996
     */
    @SuppressWarnings
    object CustomPersistence {

        fun executeQuery(session: Session, message: String, annotatedClass: Class<*>, operation: () -> Unit) {
            val entityManager = createEntityManager(annotatedClass)
            beginTransaction(entityManager)
            executeOperation(session, operation)
            closeTransaction(entityManager)
        }

        /**
         * Open session using JPA without entities
         */
        fun openSessionWithJPA(annotatedClass: Class<*>) = createEntityManager(annotatedClass).unwrap(Session::class.java)

        fun createEntityManager(annotatedClass: Class<*>): EntityManager {
            val entityManagerFactory = Persistence.createEntityManagerFactory(
                    "hypersistence-optimizer"
            )
            /*val entityManagerFactory = createEntityManagerFactory(
                    createConfiguration(annotatedClass)
            )*/
            return entityManagerFactory.createEntityManager()
        }

        //        https://stackoverflow.com/a/30125601/5279996
        private fun createEntityManagerFactory(configuration: Configuration): EntityManagerFactory {
            val p: Properties = configuration.properties

            // convert to Map
            val pMap: MutableMap<String?, String?> = HashMap()
            val e: Enumeration<*> = p.propertyNames()
            while (e.hasMoreElements()) {
                val s = e.nextElement() as String
                pMap[s] = p.getProperty(s)
            }

            // create EntityManagerFactory
            return Persistence.createEntityManagerFactory("hypersistence-optimizer", pMap)
        }
        private fun createConfiguration(annotatedClass: Class<*>) : Configuration =
                Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(annotatedClass)

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