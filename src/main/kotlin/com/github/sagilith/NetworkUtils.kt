package com.github.sagilith

import net.minecraft.text.Text
import net.minecraft.client.MinecraftClient

object NetworkUtils {
  val disconnectText = Text.of("Afk timeout expire")

  fun disconnect() {
    MinecraftClient
      .getInstance()
      .getNetworkHandler()
      ?.getConnection()
      ?.disconnect(disconnectText)
  }
}
