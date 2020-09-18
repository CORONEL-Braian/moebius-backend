package app.mobius.data.di

import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.Transaction
import org.hibernate.cfg.Configuration

data class Test(val test: String)

class InjectionContainer {
    companion object {

        fun test() {
//            executeQuery()
        }

        /**
         * @param operation: e.g: save/update/read
         * @param message: successfully
         */
        fun executeQuery(resource: String,
                         annotatedClass: Class<*>,
                         operation: () -> Unit,
                         message: String
        ) {
            val sessionFactory = readMetadata(resource, annotatedClass)
            val session = openSession(sessionFactory)

            try {
                beginTransaction(session)
                executeOperation(session, operation)
                commitTransaction(session, message)
            } finally {
                endSession(session, sessionFactory)
            }
        }


        /**
         * Read the configuration file
         * OBS: Session Factory puede tener varias sesiones abiertas.
         * @param annotatedClass The class containing annotations
         * @param resource with extension .cfg.xml
         */
        private fun readMetadata(resource: String, annotatedClass: Class<*>) : SessionFactory =
                Configuration()
                        .configure(resource)
                        .addAnnotatedClass(annotatedClass)
                        .buildSessionFactory()

        /**
         * Open the session
         */
        private fun openSession(sessionFactory: SessionFactory) : Session =
                sessionFactory.openSession().also {
                    println("openSession successfully")
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