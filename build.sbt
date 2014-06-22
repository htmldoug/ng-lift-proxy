name := "Lift 2.5 starter template"

version := "0.0.1"

organization := "net.liftweb"

scalaVersion := "2.9.1"

resolvers ++= Seq("snapshots"     at "http://oss.sonatype.org/content/repositories/snapshots",
                "releases"        at "http://oss.sonatype.org/content/repositories/releases"
                )

seq(webSettings :_*)

unmanagedResourceDirectories in Test <+= (baseDirectory) { _ / "src/main/webapp" }

scalacOptions ++= Seq("-deprecation", "-unchecked")

libraryDependencies ++= {
  val liftVersion = "2.5.1"
  val liftEdition = "2.5"
  Seq(
    "net.liftweb"             %% "lift-webkit"                        % liftVersion           % "compile",
    "net.liftmodules"         %% ("lift-jquery-module_"+liftEdition)  % "2.4"                 % "compile",
    "net.liftmodules"         %% ("ng_"+liftEdition)                  % "0.1.1"               % "compile",
    "ch.qos.logback"          % "logback-classic"                     % "1.0.6"               % "compile",
    "org.eclipse.jetty"       % "jetty-webapp"                        % "8.1.7.v20120910"     % "container,test",
    "org.eclipse.jetty.orbit" % "javax.servlet"                       % "3.0.0.v201112011016" % "container,test" artifacts Artifact("javax.servlet", "jar", "jar"),
    "org.specs2"              %% "specs2"                             % "1.12.1"              % "test"
  )
}

seq(jasmineSettings : _*)

appJsDir <+= (sourceDirectory in Compile) { _ / "webapp" / "scripts" }

appJsLibDir <+= (sourceDirectory in Compile) { _ / "webapp" / "scripts" }

jasmineTestDir <+= (sourceDirectory in Test) { _ / "resources" / "js" }

jasmineConfFile <+= (sourceDirectory in Test) { _ / "resources" / "js" / "test.dependencies.js" }

jasmineRequireJsFile <+= (sourceDirectory in Test) { _ / "resources" / "js" / "require-2.0.6.js" }

jasmineRequireConfFile <+= (sourceDirectory in Test) { _ / "resources" / "js" / "require.conf.js" }

(Keys.test in Test) <<= (Keys.test in Test) dependsOn (jasmine)

