package app.mobius.data.dataAccess.hibernate

import app.mobius.data.dataAccess.JDBMConfig
import app.mobius.data.util.propertyValue
import app.mobius.domain.entity.role.Resource
import org.hibernate.query.Query
import java.lang.reflect.Field
import java.util.*
import javax.persistence.*
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root
import kotlin.jvm.Throws

class HibernateData {

    private val session = JDBMConfig.Hibernate.openSession()
    private val builder = session.criteriaBuilder

    private var isSubEntity = false
    private val mapOfSubEntity: MutableMap<String, Any> = mutableMapOf()

    fun <T> allTheRows(t: Class<T>) : List<T> {
        val criteriaQuery: CriteriaQuery<T> = getCriteriaQuery(t)

        val root: Root<T> = criteriaQuery.from(t)
        criteriaQuery.select(root)

        val query: Query<T> = session.createQuery(criteriaQuery)
        return query.resultList
    }

    fun <T> readRow(t: Class<T>) : String {
        val inserted = session.get(t, getPropertyNameOfUuid())
        return "Inserted: $inserted"
    }

    private fun getPropertyNameOfUuid(): String {
        return "profile_uuid"   //TODO
    }

    /**
     * - Describes whether a new object is valid for uniqueness in the entity hierarchy.
     * Uniqueness is contemplated in @Column, @JoinColumn and @Table#UniqueConstraint which includes the 2 above.
     * - OBS:
     *  . It is not guaranteed that the value does not exist when an entity is being persisted
     *  . Only @return false is used if the uniqueness is not fulfilled, because with @return true it would stop searching in the
     * first that is valid.
     *  . Kotlin prohibits usage of a variable or a property inside its own initializer.
     * - TODO: Evaluate UniqueConstraints in one table and multiple tables
     */
    fun isUniquenessValid(instance: Any) : Boolean {
        val declaredFields = instance::class.java.declaredFields

        declaredFields.filter {
            !it.isAnnotationPresent(Id::class.java)
        }.map {  field ->
            if (!isUniquenessValidOfColumn(instance, field)) return false
            else if (!isUniquenessValidOfJoinColumn(instance, field))  return false
            else if (!isUniquenessValidOfJoinTable(instance, field)) return false
//            else if (!isUniquenessValidOfUniqueConstraint(instance, field)) return false
        }
        return true
    }

    private fun isUniquenessValidOfColumn(instance: Any, field: Field) : Boolean {
        if (isUniqueFieldOfColumn(field) ) {
            instance.propertyValue<Any?>(field.name)?.let { value ->
                if (isExistingField(instance::class.java, field.name, value)) return false
            }
        }
        return true
    }

    /**
     *  1. If the uniqueness of the sub-entity is valid: @return true
     *      . Is not necessary to know the uniqueness of the entity because a new sub-entity uuid is generated in entity
     *  2. If the uniqueness of the sub-entity is not valid: @return false
     *      . Depending on if the sub-entity has to be unique or not in the entity, some things or others will be done.
     */
    private fun isUniquenessValidOfJoinColumn(instance: Any, field: Field) : Boolean {
        val joinColumn = JoinColumn::class.java
        if (field.isAnnotationPresent(joinColumn)) {
            instance.propertyValue<Any?>(field.name)?.let { subEntity ->
                if (!isUniquenessValid(subEntity)) return false
            }
        }
        return true
    }

    private fun isUniquenessValidOfJoinTable(instance: Any, field: Field) : Boolean {
        val joinTable = JoinTable::class.java
        if (field.isAnnotationPresent(joinTable)) {
            instance.propertyValue<List<Any>>(field.name)?.let { subEntities ->
                subEntities.map { subEntity ->
                    if (!isUniquenessValid(subEntity)) return false
                }
            }
        }
        return true
    }

    private fun isUniquenessValidOfUniqueConstraint(instance: Any, field: Field) : Boolean {
        val table = Table::class.java

        return true
    }

    /**
     * Save analogous entity, that is, with existing sub-entity (not valid) that may or may not be unique in the entity
     * Precondition: The columns are valid
     *
     * TODO: WARNING: This reuse might not make sense because it would generate dependencies
     * e.g: Person with same location
     */
    @Throws(IllegalArgumentException::class)
    fun saveAnalogousEntity(instance: Any) {
        val declaredFields = instance::class.java.declaredFields
        val joinColumn = JoinColumn::class.java

        declaredFields.filter {
            !it.isAnnotationPresent(Id::class.java)
        }.map {  field ->
            if (isUniqueFieldOfColumn(field)) {
                instance.propertyValue<Any?>(field.name)?.let { value ->
                    if (isExistingField(instance::class.java, field.name, value)) throw IllegalArgumentException("@Column not valid")

//                For get UUID of sub-entity
                    if (isSubEntity) mapOfSubEntity[field.name] = value
                }

                /**
                 * If the uniqueness of the sub-entity is not valid: @return false
                 *   . Depending on if the sub-entity has to be unique or not in the entity, some things or others will be done.
                 */
            } else if (field.isAnnotationPresent(joinColumn)) {
                isSubEntity = true

                instance.propertyValue<Any>(field.name)?.let { subEntity ->
                    if (!isUniquenessValid(subEntity)) {

                        if (field.getAnnotation(joinColumn).unique) {

//                    val subEntityUUID = getUUID(subEntity)
                            val subEntityUUID = getUUID(subEntity::class.java, mapOfSubEntity)

                            if (isExistingField(instance::class.java, field.name, subEntityUUID))
                                throw IllegalArgumentException("Sub-entity must be unique in entity")

                        }
                    }
                }

                isSubEntity = false
            } else {

            }

        }
    }

    fun <T> getUUID(t: Class<T>, entity: MutableMap<String, Any>) : List<UUID> {
        val criteriaQuery: CriteriaQuery<T> = getCriteriaQuery(t)

        val root: Root<T> = criteriaQuery.from(t)
        criteriaQuery.select(root)

        var predicate: Predicate? = null
        entity.map { entry ->
            val predicateIterable: Predicate = builder.equal(root.get<Resource>(entry.key), entry.value)

            predicate = if (predicate != null) {
                builder.and(predicate, predicateIterable)
            } else {
                builder.and(predicateIterable)
            }
        }

        criteriaQuery.where(predicate)

        val query: Query<T> = session.createQuery(criteriaQuery)
        val results: List<T> = query.resultList
//        TODO
        return listOf()
    }

    fun getUUID(instance: Any) : UUID {
//        TODO
        return UUID.randomUUID()
    }

    private fun <T> isTableUniqueConstraintValid(candidateObject: Any) : Boolean {
        return false    //TODO
    }

    private fun isColumnValid(candidateObject: Any) : Boolean {
        val uniqueFields = getUniqueFieldsOfColumn(candidateObject::class.java)
        uniqueFields.map { field ->
            candidateObject.propertyValue<Any>(field.name)?.let { value ->
                if (isExistingField(candidateObject::class.java, field.name, value)) return false
            }
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

    private fun <T> areExistingFields(t: Class<T>, uniqueConstraint: UniqueConstraint) : Boolean {
        return false //TODO
    }

    /**
     * Search field with column that exists
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