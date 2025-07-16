package com.github.sagilith

import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.client.MinecraftClient

fun interface PlayerReflectInterface {
  fun moving(player: ClientPlayerEntity): Boolean
}

public object PlayerReflect : PlayerReflectInterface {
  var x: Double? = null
  var y: Double? = null
  var z: Double? = null

  var pitch: Float? = null
  var yaw: Float? = null

  override fun moving(player: ClientPlayerEntity): Boolean {
    val mc = MinecraftClient.getInstance()

    val moving = player.getX() != x ||
      player.getY() != y ||
      player.getZ() != z ||
      player.getPitch() != pitch ||
      player.getYaw() != yaw ||
      mc.interactionManager!!.isBreakingBlock() ||
      mc.mouse.wasLeftButtonClicked() ||
      mc.mouse.wasRightButtonClicked() ||
      player.isCreative() ||
      !player.isAlive() ||
      mc.currentScreen != null


    x = player.getX()
    y = player.getY()
    z = player.getZ()

    pitch = player.getPitch()
    yaw = player.getYaw()

    return moving
  }
}


