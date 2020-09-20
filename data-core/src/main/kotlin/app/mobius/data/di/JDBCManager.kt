package app.mobius.data.di

import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.Transaction
import org.hibernate.cfg.Configuration
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "test")
data class SomeTest(@Id val test: String?)


fun main(args: Array<String>) {
    JDBCManager.test2()
}

class JDBCManager {
    companion object {


        fun test2() {
            val session = openSession("hibernate.cfg.xml", SomeTest::class.java)

            executeQuery(session, "Work") {
                session.save(SomeTest("2"))
            }
        }

        /**
         * Open the session
         */
        fun openSession(resource: String, annotatedClass: Class<*>) : Session {
            val sessionFactory = SessionConfiguration.generate(resource, annotatedClass)
            return sessionFactory.openSession().also {
                println("openSession successfully")
            }
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
             * OBS: Session Factory puede tener varias sesiones abiertas.
             * @param annotatedClass The class containing annotations
             * @param resource with extension .cfg.xml
             */
            fun generate(resource: String, annotatedClass: Class<*>) : SessionFactory =
                    Configuration()
                            .configure(resource)
                            .addAnnotatedClass(annotatedClass)
                            .buildSessionFactory()
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