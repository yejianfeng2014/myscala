package akka.dt.app.java.messages;

import akka.dt.app.java.WordCount;

import java.util.List;

/**
 * Created by Administrator on 2016/11/11.
 */
public class MapData {

    private List<WordCount> dataList;

    public MapData(List dataList) {
        this.dataList = dataList;
    }

    public List<WordCount> getDataList() {
        return dataList;
    }
}
