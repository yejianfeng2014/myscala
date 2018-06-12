import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.dt.app.java.actors.MasterActor;
import akka.dt.app.java.messages.Result;

/**
 * Created by Administrator on 2016/11/11.
 */
public class HelloAkka {

    public  static  void main(String[] args) throws  Exception{

        ActorSystem  _system=ActorSystem.create("hello akka");
      //  ActorRef  master =_system.actorOf(new Props(MasterActor.class),"master");
        ActorRef master=_system.actorOf(Props.create(MasterActor.class),"master");

        master.tell("hi, my name is jacka I,m so so so happy to be here",master);

            Thread.sleep(500);
      //  master.tell(new Result());

        master.tell(new Result(),master);

        Thread.sleep(500);
        _system.shutdown();
    }
}
