name := "retroboard"

version := "0.0.1"

organization := "com.github.notyy"

scalaVersion := "2.10.1"

resolvers ++= Seq("snapshots"     at "http://oss.sonatype.org/content/repositories/snapshots",
                "releases"        at "http://oss.sonatype.org/content/repositories/releases",
                "googlecode"      at "http://sass-java.googlecode.com/svn/repo"
                )

seq(com.github.siasia.WebPlugin.webSettings :_*)

scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature", "-language:postfixOps")

libraryDependencies ++= {
  val liftVersion = "2.5-RC5"
  Seq(
    "net.liftweb"       %% "lift-webkit"         % liftVersion        % "compile",
    "net.liftweb"       %% "lift-wizard"         % liftVersion        % "compile",
    "org.eclipse.jetty" % "jetty-webapp"         % "8.1.7.v20120910"  % "container,test",
    "org.eclipse.jetty.orbit" % "javax.servlet"  % "3.0.0.v201112011016" % "container,test" artifacts Artifact("javax.servlet", "jar", "jar"),
    "org.slf4j"         % "slf4j-api"            % "1.7.5",
    "ch.qos.logback"    % "logback-classic"      % "1.0.6",
    "org.scalatest"     %% "scalatest"           % "2.0.M5b" % "test",
    "com.h2database"    % "h2"                   % "1.3.167",
    "net.liftweb" 	    %% "lift-squeryl-record" % liftVersion,
    "net.liftweb"       %% "lift-testkit"        % liftVersion % "test"
  )
}

parallelExecution in Test := false

