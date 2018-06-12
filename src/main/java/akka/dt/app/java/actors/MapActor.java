package akka.dt.app.java.actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.dt.app.java.WordCount;
import akka.dt.app.java.messages.MapData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Administrator on 2016/11/11.
 */
public class MapActor extends UntypedActor {
        private  ActorRef reduceActor=null;
            String[] STOP_WORDS={"a","b","is"};

    private  List<String>  STOP_WORDS_LIST= Arrays.asList(STOP_WORDS);

            public  MapActor(ActorRef inReduceActor){
                    reduceActor=inReduceActor;
            }


        public void onReceive (Object message) throws  Exception{
            if (message instanceof  String){

                String work=(String) message;
                MapData data= evaluatorException(work);

                //reduceActor.tell(data);
                // TODO: 2016/11/12    修改了方法
                reduceActor.tell(data,reduceActor);

            }else {

                unhandled(message);
            }
    }



    private MapData evaluatorException (String line) {

        List<WordCount> datalist=new ArrayList<WordCount>();
        StringTokenizer parser=new StringTokenizer(line);

        while (parser.hasMoreTokens()){
            String word =parser.nextToken().toLowerCase();
            if (!STOP_WORDS_LIST.contains(word)){
                datalist.add(new WordCount(word,Integer.valueOf(1)));
            }
        }
        return new MapData(datalist);
    }

    }
