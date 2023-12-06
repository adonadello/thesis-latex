case class Greet(name: String)

// Actor class
class HelloWorldSyncActor extends Actor {
  def receive: Receive = {
    // ricezione messaggio
    case Greet(name) =>
      sender() ! Greet(s"Hello $name, Hello world!")
  }
}

object App {
  def main(args: Array[String]): Unit = {
    // Creazione del sistema degli attori
    val system = ActorSystem("HelloWorldSystem")

    // Attivazione dell'attore sincrono
    val syncGreeter = system.actorOf(Props[HelloWorldSyncActor], "syncGreeter")
    implicit val timeout: Timeout = Timeout(1.second)

    // Invio messaggio request/response
    val future = syncGreeter ? Greet("Elon2")
    val result = Await.result(future, timeout.duration)

    // Stampa del risultato
    println(result)

    // Terminazione del sistema degli attori
    system.terminate()
  }
}