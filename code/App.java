class HelloWorldActor extends Actor {
  def receive: Receive = {
    case Greet(name) =>
      println(s"Hello, $name!")
  }
}

object HelloWorldActor {
    def props(): Props = Props(new HelloWorldActor)
}

object App {
    def main(args: Array[String]): Unit = {
        // Creazione di Akka system
        val system = ActorSystem("hello-world")

        // Creazione dell'attore HelloWorld
        val greeter = system.actorOf(HelloWorldActor.props(), "greeter")

        // Invio messaggio fire-and-forget
        greeter ! Greet("Elon")

        // Attendi la terminazione del sistema
        scala.io.StdIn.readLine("Press Enter to terminate...")
        system.terminate()
    }
}