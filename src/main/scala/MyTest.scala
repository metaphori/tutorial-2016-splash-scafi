import it.unibo.scafi.incarnations.BasicSimulationIncarnation._

object MyAggregateProgram extends AggregateProgram {
  def gradient(source: Boolean): Double =
    rep(Double.MaxValue){
      distance => mux(source) { 0.0 } {
        foldhood(Double.MaxValue)(Math.min(_,_))(nbr{distance}+nbrvar[Double](NBR_RANGE_NAME))
      }
    }

  def isSource = sense[Boolean]("source")

  override def main() = gradient(isSource)
}


object MyTest extends App {

  val net = simulatorFactory.gridLike(
    n = 10,
    m = 10,
    stepx = 1,
    stepy = 1,
    eps = 0.0,
    rng = 1.1)

  net.addSensor(name = "source", value = false)
  net.chgSensorValue(name = "source", ids = Set(3), value = true)

  var v = java.lang.System.currentTimeMillis()

  net.executeMany(
    node = MyAggregateProgram,
    size = 100000,
    action = (n,i) => {
      if (i % 1000 == 0) {
        println(net)
        val newv = java.lang.System.currentTimeMillis()
        println(newv-v)
        println(net.context(4))
        v=newv
      }
    })
}

