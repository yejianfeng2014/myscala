package akka.dt.app.java.actors;

import akka.actor.UntypedActor;
import akka.dt.app.java.messages.ReduceData;
import akka.dt.app.java.messages.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/11.
 */
public class AggregateActor  extends UntypedActor{
            private  Map<String,Integer> finalReducedMap=new HashMap<String, Integer>();

    public void onReceive(Object message) throws Throwable {
            if (message instanceof ReduceData){

                ReduceData  reduceData=(ReduceData)message;
                aggregateActorInMemoryReduce(reduceData.getReduceDataList());


            }else  if (message instanceof Result){
                System.out.println(message.toString());
            }else {
                unhandled(message);
            }
    }


    private  void  aggregateActorInMemoryReduce(Map<String,Integer> reducelist){
        Integer count =null;
                    for(String key:reducelist.keySet()){
                        if (finalReducedMap.containsKey(key)){
                            count=reducelist.get(key)+finalReducedMap.get(key);
                            finalReducedMap.put(key,count);
                        }else{
                            finalReducedMap.put(key, reducelist.get(key));
                        }
                    }

    }
}
