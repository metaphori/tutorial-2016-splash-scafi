package splash16

import it.unibo.scafi.incarnations.BasicSimulationIncarnation._

import scala.util.Random

/**
  * @author Roberto Casadei
  *
  */

object Networks {
  def gridManhattan = {
    val net = simulatorFactory.gridLike(
      n = 10,
      m = 10,
      stepx = 1,
      stepy = 1,
      eps = 0.0,
      rng = 1.1)

    net.addSensor(name = "source", value = false)
    net.chgSensorValue(name = "source", ids = Set(3), value = true)
    net.addSensor(name = "destination", value = false)
    net.chgSensorValue(name = "destination", ids = Set(89), value = true)

    val rand = new Random()
    net.addSensor(name = "temperature", value = 0.0)
    (0 to 99).foreach(i => net.chgSensorValue(name = "temperature", ids = Set(i), value = rand.nextDouble()*100))

    net
  }

  def gridForChannel = {
    val net = simulatorFactory.gridLike(
      n = 10,
      m = 10,
      stepx = 1,
      stepy = 1,
      eps = 0.0,
      rng = 1.5)

    net.addSensor(name = "source", value = false)
    net.chgSensorValue(name = "source", ids = Set(3), value = true)
    net.addSensor(name = "destination", value = false)
    net.chgSensorValue(name = "destination", ids = Set(86), value = true)
    net.addSensor(name = "obstacle", value = false)
    net.chgSensorValue(name = "obstacle", ids = Set(44,45,46,54,55,56,64,65,66), value = true)

    net
  }
}
