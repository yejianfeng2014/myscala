package akka.dt.app.java.actors;

import akka.actor.ActorRef;
import akka.actor.Deploy;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.dt.app.java.messages.Result;

/**
 * Created by Administrator on 2016/11/11.
 * 写完了但是有错误
 */
public class MasterActor extends UntypedActor {


        // 创建  actor
        private ActorRef  aggregateActor =getContext().actorOf(
                Props.create(AggregateActor.class),"aggregate");



        private  ActorRef  reduceActor=getContext().actorOf(
                new Props(new  UntypedActorFactory(){
                    public  UntypedActor create(){
                        return  new MapActor(aggregateActor);
                    }
                }),"reduce");


    private  ActorRef mapActor=getContext().actorOf(
            new Props(new  UntypedActorFactory(){
                public  UntypedActor create(){
                    return  new MapActor(reduceActor);
                }
            }),"map");






    public void onReceive(Object message) throws Throwable {
        if (message instanceof  String){
            mapActor.tell(message);
        }else  if (message instanceof Result){
            aggregateActor.tell(message);
        }else{
            unhandled(message);
        }

    }
}
