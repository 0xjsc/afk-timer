package com.github.sagilith

import me.shedaniel.autoconfig.ConfigData
import me.shedaniel.autoconfig.annotation.Config

@Config(name = "afk-auto-kick")
data class MConfig(
  var afkTimeout: Int = 200
) : ConfigData
