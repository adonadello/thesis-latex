case object StopException extends Exception
case object RestartException extends Exception
case object ResumeException extends Exception

class HelloWorldActor extends Actor {
  def receive: Receive = {
    case _ =>
      // Logica dell'attore figlio
  }
}

class HelloWorldSupervisorActor extends Actor {

  // Creazione dell'attore figlio
  val child = context.actorOf(Props(new HelloWorldActor), "greeter-actor")

  // Avvio supervisione
  context.watch(child)

  // Definizione della strategia di supervisione
  override def supervisorStrategy: SupervisorStrategy =
    AllForOneStrategy() {
      case StopException => SupervisorStrategy.Stop
      case RestartException => SupervisorStrategy.Restart
      case ResumeException => SupervisorStrategy.Resume
      case _: Exception => SupervisorStrategy.Escalate
    }

  // Inoltro al figlio il messaggio ricevuto
  override def receive: Receive = {
    case msg => child forward msg
  }
}