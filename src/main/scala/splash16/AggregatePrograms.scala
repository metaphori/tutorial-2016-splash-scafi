package splash16

import it.unibo.scafi.incarnations.BasicSimulationIncarnation._

/**
  * @author Roberto Casadei
  *
  */

object AggregatePrograms {

  def program1_simple = new AggregateProgram {
    def main = 1+1
  }

  def program2_simple = new AggregateProgram {
    def main = mid()
  }

  def program3_rep = new AggregateProgram {
    def main = rep(0)(_+1)
  }

  def program4_nbr = new AggregateProgram {
    def main = foldhood(0)(_+_){ nbr(1) }
  }

  def program5_sense = new AggregateProgram {
    def main = sense[Double]("temperature")
  }

  def program6_nbrvar = new AggregateProgram {
    def main = foldhood(Double.MinValue)(Math.max(_,_)){
      nbrvar[Double](NBR_RANGE_NAME)
    }
  }

  def program7_branch = new AggregateProgram {
    def main = branch(sense[Boolean]("source")) {
      "X"
    }{
      "o"
    }
  }

  def program8_fun = new AggregateProgram {
    def foldhoodMinus[A](init: => A)(acc: (A,A) => A)(ex: => A): A =
      foldhood(init)(acc){ mux(mid()==nbr(mid())){ init }{ ex } }

    def main = foldhoodMinus(0)(_+_){ nbr(1) }
  }

  def program9_gradient = new AggregateProgram with AggregateAPI {
    def gradient(source: Boolean): Double = rep(Double.MaxValue){
        distance => mux(source) { 0.0 } {
          foldhood(Double.MaxValue)(Math.min(_,_))(nbr{distance}+nbrvar[Double](NBR_RANGE_NAME))
        }
      }

    def main = gradient(isSource)
  }

  def program10_channel = new AggregateProgram with AggregateAPI {
    val width = 0.4

    def main = {
      val belongsToChannel = branch(isObstacle){ false }{ channel(isSource, isDest, width) }
      if(isSource) "START"
      else if(isDest) "END  "
      else if(isObstacle) "|||||"
      else if(belongsToChannel) "  x  "
      else "    "
    }
  }
}
