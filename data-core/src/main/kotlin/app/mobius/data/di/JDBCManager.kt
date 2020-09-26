package app.mobius.data.di

import app.mobius.domain.mapper.role.Permission
import app.mobius.domain.mapper.role.Resource
import app.mobius.domain.mapper.role.Subscription
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.Transaction
import org.hibernate.cfg.Configuration
import org.reflections.Reflections
import java.util.*
import javax.persistence.Entity
import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence
import javax.persistence.PersistenceUnit


class JDBCManager {

    companion object {

        @PersistenceUnit(name = "HypersistenceOptimizer")
        private var entityManagerFactory: EntityManagerFactory? = null

        /**
         * Open the session
         */
        fun openSession_1(annotatedClass: Class<*>, resource: String = "secret-hibernate.cfg.xml") : Session {
            val sessionFactory = SessionConfiguration.generate_1(annotatedClass, resource)
            return sessionFactory.openSession().also {
                println("openSession successfully")
            }
        }

//        TODO: deprecate secret-hibernate
        fun openSession_1_1(annotatedClass: Class<*>, resource: String = "secret-hibernate.cfg.xml") : Session {
            val sessionFactory = SessionConfiguration.generate_1_1(resource)
            return sessionFactory.openSession().also {
                println("openSession successfully")
            }
        }

        fun openSession_2(annotatedClass: Class<*>, resource: String = "secret-hibernate.cfg.xml") : Session {
            entityManagerFactory = createEntityManagerFactory(SessionConfiguration.generate_2(annotatedClass, resource))
            return entityManagerFactory?.let { getSessionFactory(it)!!.openSession() }!!
        }

        fun openSession_3(): Session {
            val emf = Persistence.createEntityManagerFactory("HypersistenceOptimizer")
            val em = emf.createEntityManager()
            val session = em.unwrap(Session::class.java)
            return session
        }

        fun createEntityManagerFactory(hibConfiguration: Configuration): EntityManagerFactory? {
            val p: Properties = hibConfiguration.properties

            // convert to Map
            val pMap: MutableMap<String?, String?> = HashMap()
            val e: Enumeration<*> = p.propertyNames()
            while (e.hasMoreElements()) {
                val s = e.nextElement() as String
                pMap[s] = p.getProperty(s)
            }

            // create EntityManagerFactory
            return Persistence.createEntityManagerFactory("HypersistenceOptimizer", pMap)
        }

        fun getSessionFactory(entityManagerFactory: EntityManagerFactory): SessionFactory? {
            return entityManagerFactory.unwrap(SessionFactory::class.java)
        }

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
         * https://docs.jboss.org/hibernate/core/3.3/reference/en/html/session-configuration.html
         */
        private object SessionConfiguration {
            /**
             * Generate session configuration
             * PRE: Configure entity mapping in open-persistence.xml necessary for the class hierarchy
             * OBS: Session Factory puede tener varias sesiones abiertas.
             * @param annotatedClass The class containing annotations
             * @param resource with extension .cfg.xml
             */
            fun generate_1(annotatedClass: Class<*>, resource: String) : SessionFactory =
                    Configuration()
                            .configure(resource)
                            .addAnnotatedClass(annotatedClass)
                            .buildSessionFactory()

            /*.addAnnotatedClass(Permission::class.java)
                            .addAnnotatedClass(Resource::class.java)*/

            /**
             * Add all the classes annotated with Entity in the configuration
             * Precondition: The prefix contains classes with a configured hibernate mapping
             */
            fun generate_1_1(resource: String) : SessionFactory {
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

            fun generate_2(annotatedClass: Class<*>, resource: String) : Configuration {
                val configuration = Configuration()
                        .configure(resource)
                /*val reflections = Reflections("app.mobius")
                val importantClasses: Set<Class<*>> = reflections.getTypesAnnotatedWith(Entity::class.java)
                for (clazz in importantClasses) {
                    println("TEST Reflections: $clazz")
                    configuration.addAnnotatedClass(clazz)
                }*/
                configuration.addAnnotatedClass(Subscription::class.java)
                println("TEST Should be: ${Subscription::class.java}")
                return configuration

            }

            fun generate_3(annotatedClass: Class<*>, resource: String) : Configuration {
                return Configuration()
                        .configure(resource)
//                        .addAnnotatedClass(annotatedClass)
                        .addAnnotatedClass(Permission::class.java)
                        .addAnnotatedClass(Resource::class.java)
            }

        }



        /**
         * Begin a transaction
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

}