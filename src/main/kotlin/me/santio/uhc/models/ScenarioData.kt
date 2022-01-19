package me.santio.uhc.models

import lombok.Getter
import lombok.Setter
import java.util.UUID
import me.santio.uhc.states.ScoreboardState
import org.bukkit.entity.Player
import org.bukkit.Bukkit
import net.md_5.bungee.api.chat.BaseComponent
import me.santio.uhc.UHC
import java.util.HashMap
import me.santio.uhc.models.ScenarioData
import java.util.Locale
import me.santio.uhc.Game
import me.santio.uhc.exceptions.DuplicateScenarioException
import org.bukkit.event.HandlerList
import org.apache.commons.lang.WordUtils

class ScenarioData {
    @Setter
    @Getter
    private var description: String

    @Setter
    @Getter
    private var value: Any? = null

    @Getter
    private val key: String

    constructor(key: String, description: String) {
        this.key = key
        this.description = description
    }

    constructor(key: String, description: String, value: Any?) {
        this.key = key
        this.description = description
        this.value = value
    }

    val wrappedDescription: Array<String>
        get() = WordUtils.wrap(getDescription(), 30).split("\n").toTypedArray()
}