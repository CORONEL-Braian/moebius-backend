package app.mobius.data.di

import app.mobius.data.util.propertyValue
import app.mobius.domain.entity.role.Resource
import org.hibernate.query.Query
import java.lang.reflect.Field
import javax.persistence.Column
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Root

/**
 * https://codingexplained.com/coding/java/hibernate/unique-field-validation-using-hibernate-spring
 */
class HibernateUtil {

    private val session = JDBM.Hibernate.openSession()
    private val builder = session.criteriaBuilder

    fun <T> allTheRows(t: Class<T>) : List<T> {
        val criteriaQuery: CriteriaQuery<T> = getCriteriaQuery(t)

        val root: Root<T> = criteriaQuery.from(t)
        criteriaQuery.select(root)

        val query: Query<T> = session.createQuery(criteriaQuery)
        return query.resultList
    }

    /**
     * Describe si hay algun campo que tiene un valor unico existente
     */
    fun <T> isThereAUniqueExistingField(t: Class<T>, instance: Any) : Boolean {
        val uniqueFields = getUniqueFieldsOfColumn(t)
        uniqueFields.map { field ->
            val value = instance.propertyValue<Any>(field.name)
            if (isExistingField(t, field.name, value)) return true
        }
        return false
    }

    /**
     * Describe si un campo de una entidad existe
     */
    private fun <T> isExistingField(t: Class<T>, name: String, value: Any) : Boolean {
        val criteriaQuery: CriteriaQuery<T> = getCriteriaQuery(t)

        val root: Root<T> = criteriaQuery.from(t)
        criteriaQuery.select(root)

        criteriaQuery.where(builder.equal(root.get<Resource>(name), value))

        val query: Query<T> = session.createQuery(criteriaQuery)
        val results: List<T> = query.resultList

        return results.isNotEmpty()
    }

    /**
     * Describe the unique fields that do have annotation @Column
     */
    private fun <T> getUniqueFieldsOfColumn(t: Class<T>): List<Field> {
        val declaredFields = t.declaredFields
        return declaredFields.filter { field ->
            isUniqueFieldOfColumn(field)
        }
    }

    /**
     * Describe si un campo tiene la anotacion @Column y es unico
     * Source: https://stackoverflow.com/a/4296973/5279996
     */
    private fun isUniqueFieldOfColumn(field: Field) : Boolean {
        val annotationClass = Column::class.java
        return field.isAnnotationPresent(annotationClass) &&
                field.getAnnotation(annotationClass).unique
    }


    private fun <T> getCriteriaQuery(t: Class<T>) : CriteriaQuery<T> {
        return builder.createQuery(t)
    }

}