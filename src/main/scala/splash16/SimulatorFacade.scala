package splash16

import it.unibo.scafi.incarnations.BasicSimulationIncarnation._

/**
  * @author Roberto Casadei
  *
  */

object SimulatorFacade {
  def run(ap: AggregateProgram, net: NETWORK): Unit ={
    var initTime = java.lang.System.currentTimeMillis()
    var v = initTime
    val numIterations = 100000
    val sampleIterations = 1000
    val sampleContextDevId = 0
    net.executeMany(
      node = ap,
      size = numIterations,
      action = (n,i) => {
        if (i % sampleIterations == 0) {
          println(s"-----------------------------Iteration ${i/sampleIterations}-----------------------------")
          println(net)
          val newv = java.lang.System.currentTimeMillis()
          println(s"\nDelta time for $sampleIterations rounds (ms): ${(newv-v)}")
          println("Sample context: " + net.context(sampleContextDevId) + "\n")
          v=newv
        }
      })
    println("---------------------------------------------------------------")
    println(s"Stopped after $numIterations iterations (total time: ${v-initTime} msecs).")
  }
}
