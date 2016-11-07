scalaVersion := "2.11.8"

// Resolvers
resolvers += Resolver.sonatypeRepo("snapshots")
resolvers += Resolver.typesafeRepo("releases")

// Managed dependencies
val scafi_core  = "it.unibo.apice.scafiteam" % "scafi-core_2.11"  % "0.1.0"
val scafi_simulator  = "it.unibo.apice.scafiteam" % "scafi-simulator_2.11"  % "0.1.0"
val scafi_platform = "it.unibo.apice.scafiteam" % "scafi-distributed_2.11"  % "0.1.0"

libraryDependencies ++= Seq(scafi_core, scafi_simulator, scafi_platform)
