package akka.dt.app.java;

/**
 * Created by Administrator on 2016/11/11.
 */
public class WordCount {

    private String word;
    private Integer count;

    public WordCount(String inword, Integer incount) {
        this.word = inword;
        this.count = incount;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
