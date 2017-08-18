package funky.framework

import kotlin.reflect.KClass

object CommandRegister {

    var classes: ArrayList<KClass<*>> = ArrayList()

    fun register(vararg clazz: KClass<*>) {
        clazz.forEach {
            classes.add(it)
        }
    }
}