package splash16

/**
  * @author Roberto Casadei
  *
  */

import scala.reflect.runtime.universe

object Runner extends App {
  //SimulatorFacade.run(AggregatePrograms.program_simple, Networks.gridManhattan)
  SimulatorFacade.run(AggregatePrograms.program9_gradient, Networks.gridManhattan)
}
