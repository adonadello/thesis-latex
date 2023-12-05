//actor object
object HelloWorldActor{

    //istanziazione attore tramite factory method props
    def props(name:String):Props=Props(new HelloWorldActor())

    //definizione messaggio
    case class Greet(msg: String)

}

// actor class
class HelloWorldActor()extends Actor {

    def receive = {

        //ricezione messaggio
        case Greet(name) =>

            println("Hello " + name)

    }

}