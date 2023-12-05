object App {
    
    def main(args: Array[String]) {

        //creazione Akka system
        val system = ActorSystem("hello-world")

        //creazione attore HelloWorld
        val greeter = system.actorOf(HelloWorldActor.props(), "greeter")

        //invio messaggio fire-and-forget
        greeter ! Greet("Elon")

        Await.ready(system.whenTerminated, Duration.Inf)

    }
}