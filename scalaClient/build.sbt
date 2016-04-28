name := "scalaClient"

version := "1.0"

scalaVersion := "2.11.8"

mainClass in Compile := Some("com.BookStore.Main")

unmanagedJars in Compile <<= baseDirectory map { base => (base ** "*.jar").classpath }

libraryDependencies ++= Seq("com.caucho" % "hessian" % "4.0.38")

