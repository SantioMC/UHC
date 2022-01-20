package me.santio.uhc.models

import org.apache.commons.lang.WordUtils

class ScenarioData(val key: String, val description: String, var value: Any?) {

    val wrappedDescription: Array<String>
        get() = WordUtils.wrap(description, 30).split("\n").toTypedArray()
}