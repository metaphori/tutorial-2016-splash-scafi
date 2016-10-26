package splash16

import it.unibo.scafi.incarnations.BasicSimulationIncarnation._

/**
  * @author Roberto Casadei
  *
  */

trait AggregateAPI { self: Constructs with Builtins =>
  import Builtins.OrderingFoldable

  def isSource = sense[Boolean]("source")
  def isDest = sense[Boolean]("destination")
  def isObstacle = sense[Boolean]("obstacle")

  def nbrRange():Double = nbrvar[Double](NBR_RANGE_NAME)

  /**
    * Gradient cast.
    * @param source represents the source field. Locally, it indicates if
    *               the device is a source for the gradientcast.
    * @param field represents the field of initial values.
    * @param acc is the accumulation function applied along the gradient.
    * @param metric represents the notion of distance.
    * @tparam V is the type of the "points" of the value field.
    * @return globally, a field of values calculated by accumulation along
    *         the gradient; locally, the accumulated value for each device.
    */
  def G[V: OrderingFoldable](source: Boolean, field: V, acc: V => V, metric: => Double): V =
  rep( (Double.MaxValue, field) ){ dv =>
    mux(source) {
      (0.0, field)
    } {
      minHoodPlus {
        val (d, v) = nbr { (dv._1, dv._2) }
        (d + metric, acc(v)) }
    }}._2

  def distanceTo(source:Boolean): Double =
    G[Double](source,0, _ + nbrRange(), nbrRange())

  def broadcast[V: OrderingFoldable](source:Boolean, field: V):V =
    G[V](source,field, x=>x , nbrRange())

  def distanceBetween(source:Boolean, target:Boolean):Double =
    broadcast(source, distanceTo(target))

  def channel(source:Boolean, target:Boolean, width:Double): Boolean =
    distanceTo(source) + distanceTo(target) <=
      distanceBetween(source,target) + width
}
