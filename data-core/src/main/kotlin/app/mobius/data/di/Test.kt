package app.mobius.data.di

import org.hibernate.boot.model.naming.Identifier
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment

class CamelCaseToSnakeCaseNamingStrategy : PhysicalNamingStrategyStandardImpl() {
    override fun toPhysicalCatalogName(
            name: Identifier?,
            context: JdbcEnvironment?): Identifier? {
        return formatIdentifier(
                super.toPhysicalCatalogName(name, context)
        )
    }

    override fun toPhysicalSchemaName(
            name: Identifier?,
            context: JdbcEnvironment?): Identifier? {
        return formatIdentifier(
                super.toPhysicalSchemaName(name, context)
        )
    }

    override fun toPhysicalTableName(
            name: Identifier?,
            context: JdbcEnvironment?): Identifier? {
        return formatIdentifier(
                super.toPhysicalTableName(name, context)
        )
    }

    override fun toPhysicalSequenceName(
            name: Identifier?,
            context: JdbcEnvironment?): Identifier? {
        return formatIdentifier(
                super.toPhysicalSequenceName(name, context)
        )
    }

    override fun toPhysicalColumnName(
            name: Identifier?,
            context: JdbcEnvironment?): Identifier? {
        return formatIdentifier(
                super.toPhysicalColumnName(name, context)
        )
    }

    private fun formatIdentifier(
            identifier: Identifier?): Identifier? {
        return if (identifier != null) {
            val name: String = identifier.getText()
            val formattedName = name
                    .replace(
                            CAMEL_CASE_REGEX.toRegex(),
                            SNAKE_CASE_PATTERN)
                    .toLowerCase()
            if (formattedName != name) Identifier.toIdentifier(
                    formattedName,
                    identifier.isQuoted()
            ) else identifier
        } else {
            null
        }
    }

    companion object {
        val INSTANCE = CamelCaseToSnakeCaseNamingStrategy()
        const val CAMEL_CASE_REGEX = "([a-z]+)([A-Z]+)"
        const val SNAKE_CASE_PATTERN = "$1\\_$2"
    }
}