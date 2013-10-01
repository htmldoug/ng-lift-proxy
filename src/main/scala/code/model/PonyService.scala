package code.model

import scala.util.Random
import net.liftweb.common.{Box, Full}

object PonyService {
  private val ponies = Set(
    Pony("Twilight Sparkle", "/images/twilight.png"),
    Pony("Rainbow Dash", "/images/rainbowdash.jpg"),
    Pony("Fluttershy", "/images/fluttershy.png")
  )

  def getRandomPony: Box[Pony] = Full(Random.shuffle(ponies).head)
}

case class Pony(name: String, imageUrl: String)
