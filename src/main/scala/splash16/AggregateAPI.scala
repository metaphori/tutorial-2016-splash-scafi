package splash16

import it.unibo.scafi.incarnations.BasicSimulationIncarnation._

/**
  * @author Roberto Casadei
  *
  */

trait AggregateAPI { self: Constructs with Builtins =>
  def isSource = sense[Boolean]("source")
  def isDest = sense[Boolean]("destination")
}
