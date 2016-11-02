package splash16

/**
  * @author Roberto Casadei
  *
  */

object Runner extends App {
  //SimulatorFacade.run(AggregatePrograms.program_simple, Networks.gridManhattan)
  SimulatorFacade.run(AggregatePrograms.program10_channel, Networks.gridForChannel)
}
