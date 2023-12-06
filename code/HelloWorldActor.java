// Actor object
object HelloWorldActor {
  // Factory method per istanziare l'attore con un parametro name
  def props(name: String): Props = Props(new HelloWorldActor(name))

  // Definizione del messaggio
  case class Greet(name: String)
}

// Actor class
class HelloWorldActor(name: String) extends Actor {
  def receive: Receive = {
    // Ricezione messaggio
    case HelloWorldActor.Greet(_) =>
      println(s"Hello, $name!")
  }
}