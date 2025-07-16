package com.github.sagilith

import com.github.sagilith.PlayerReflect
import com.github.sagilith.AfkTimer
import com.github.sagilith.NetworkUtils
import com.github.sagilith.MConfig

import net.fabricmc.api.ModInitializer

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents

import net.minecraft.client.network.ClientPlayerEntity

import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer
import me.shedaniel.autoconfig.AutoConfig

object ShittyAhhAfk : ModInitializer {
  init {
    AutoConfig.register(MConfig::class.java, ::Toml4jConfigSerializer)
  }

  private var afkTimer = AfkTimer()

  private inline fun waitAfk(player: ClientPlayerEntity?, body: () -> Unit) {
    player?.takeUnless { PlayerReflect.moving(it) } ?: return afkTimer.reset()

    if (afkTimer.increment()) body()
  }

	override fun onInitialize() {
		ClientTickEvents.END_CLIENT_TICK.register {
      waitAfk(it.player) { 
        NetworkUtils.disconnect()
      }
    }
	}
}
