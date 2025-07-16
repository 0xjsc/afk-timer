package com.github.sagilith

import me.shedaniel.autoconfig.AutoConfig
import me.shedaniel.autoconfig.ConfigData
import me.shedaniel.autoconfig.annotation.Config
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi

import net.minecraft.client.gui.screen.Screen

import com.github.sagilith.MConfig
 
class AfkTimer : ModMenuApi {
  private var tick: Int = 0 

  val config get() = AutoConfig.getConfigHolder(MConfig::class.java).config

  fun increment(): Boolean = tick++ > config.afkTimeout
  fun reset() {
    tick = 0
  }
  
  override fun getModConfigScreenFactory() = ConfigScreenFactory { AutoConfig.getConfigScreen(MConfig::class.java, it).get() }
}
