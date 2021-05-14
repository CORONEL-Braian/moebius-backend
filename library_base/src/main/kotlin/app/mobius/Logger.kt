package app.mobius

import java.util.logging.Logger

//TODO: Migrate to feature
fun <T> loggerFor(clazz: Class<T>) = Logger.getLogger("Mobius | ${clazz.simpleName}")