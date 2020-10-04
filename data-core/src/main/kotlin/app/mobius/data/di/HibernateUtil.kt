package app.mobius.data.di

import app.mobius.data.util.propertyValue
import app.mobius.domain.entity.role.Resource
import org.hibernate.query.Query
import java.lang.reflect.Field
import javax.persistence.*
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Root

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
     * Describes if a new object is compatible with respect to uniqueness
     * throughout the entity hierarchy with Unique and UniqueConstraint
     * OBS: It is not guaranteed that the value does not exist when an entity is being persisted
     */
    fun isUniquenessValid(candidateObject: Any) : Boolean {
//        TODO: Evaluate @JoinColumn
//        TODO: Evaluate @Table uniqueConstraints

        val uniqueFields = getUniqueFieldsOfColumn(candidateObject::class.java)
        uniqueFields.map { field ->
            val value = candidateObject.propertyValue<Any>(field.name)
            if (isExistingField(candidateObject::class.java, field.name, value)) return false
        }
        return true
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
     * Describes the unique fields with @Column
     */
    private fun <T> getUniqueFieldsOfColumn(t: Class<T>): List<Field> {
        val declaredFields = t.declaredFields
        return declaredFields.filter { field ->
            isUniqueFieldOfColumn(field) && !field.isAnnotationPresent(Id::class.java)
        }
    }

    /**
     * Describes if a field has the @Column annotation and is unique
     * Source: https://stackoverflow.com/a/4296973/5279996
     */
    private fun isUniqueFieldOfColumn(field: Field) : Boolean {
        val annotationClass = Column::class.java
        return field.isAnnotationPresent(annotationClass) &&
                field.getAnnotation(annotationClass).unique
    }

    /**
     * Describes if a field has an entity association and is unique
     */
    private fun isUniqueFieldOfJoinColumn(field: Field) : Boolean {
        val annotationClass = JoinColumn::class.java
        return field.isAnnotationPresent(annotationClass) &&
                field.getAnnotation(annotationClass).unique
    }

    private fun <T> getUniqueConstraints(t: Class<T>) : List<UniqueConstraint> {
        val declaredFields = t.declaredFields
        declaredFields.map {
            it.getAnnotation(Table::class.java).uniqueConstraints
        }
        return listOf() //TODO
    }

    private fun <T> getCriteriaQuery(t: Class<T>) : CriteriaQuery<T> {
        return builder.createQuery(t)
    }

}