package app.mobius.data.dataAccess

/**
 * Use vararg kotlin: https://stackoverflow.com/a/66998823/5279996
 */
object PackagesToScan {

    private const val APP_MOBIUS_ALL = "app.mobius.*"   //TODO: Delete in the future, keep only features packages

    private const val APP_MOBIUS_DOMAIN_ENTITY = "app.mobius.domain.entity"
    private const val APP_MOBIUS_CREDENTIAL_MANAGMENT_DOMAIN_ENTITY = "app.mobius.credentialManagment.domain.entity"

    var SUMMATION: Array<out String> = setPackagesToScan(
            APP_MOBIUS_ALL,
            APP_MOBIUS_DOMAIN_ENTITY,
            APP_MOBIUS_CREDENTIAL_MANAGMENT_DOMAIN_ENTITY
    )

}

private fun setPackagesToScan(vararg strings: String): Array<out String> {
    return strings
}