package akka.dt.app.java.actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.dt.app.java.WordCount;
import akka.dt.app.java.messages.MapData;
import akka.dt.app.java.messages.ReduceData;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/11/11.
 */
public class ReudceActor  extends UntypedActor{
    private ActorRef  aggregateActor=null;

    public ReudceActor(ActorRef inaggregateActor) {
        this.aggregateActor = inaggregateActor;
    }

    public void onReceive(Object message) throws Throwable {
            if (message instanceof MapData){

                MapData  mapData=(MapData)message;
                // reduce the incoming word
                ReduceData reduceData=reduce(mapData.getDataList());
                // TODO: 2016/11/12   修改了方法
                aggregateActor.tell(reduceData,aggregateActor);
            }else{
                unhandled(message);
            }
    }


    private ReduceData reduce(List<WordCount> datalist){
        HashMap <String,Integer>  reduceMap=new HashMap<String, Integer>();
                for (WordCount  wordCount:datalist){
                    if (reduceMap.containsKey(wordCount.getWord())){
                            Integer  value=(Integer) reduceMap.get(wordCount.getWord());
                    value++;
                    reduceMap.put(wordCount.getWord(),value);
                    }else {
                        reduceMap.put(wordCount.getWord(),Integer.valueOf(1));
                    }
                }
        return  new ReduceData(reduceMap)   ;
    }
}
