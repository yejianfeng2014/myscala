package akka.dt.app.java.messages;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/11.
 */
public class ReduceData {
    private HashMap<String,Integer> reduceDataList;

    public ReduceData(HashMap<String, Integer> reduceDataList) {
        this.reduceDataList = reduceDataList;
    }

    public HashMap<String, Integer> getReduceDataList() {
        return reduceDataList;
    }
}
