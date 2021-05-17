package app.mobius

import java.util.logging.Logger

//TODO: Migrate to feature-logger
//TODO: Receive as param: feature name ...
fun <T> loggerFor(clazz: Class<T>) = Logger.getLogger("Mobius-Backend | ${clazz.simpleName}")