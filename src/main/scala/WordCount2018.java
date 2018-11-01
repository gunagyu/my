import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * Created by 楊 on 2018/10/27 0027.
 */
public class WordCount2018 {
    public static void main(String[] args) throws Exception {
        //加载环境
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        //载入创建初始输入数据
        DataSet<String> text = env.fromElements(
                "who's there?",
                "I think I hear them.Stand ho!"
        );
        //指定转换算子,数据的转换方式
        DataSet<Tuple2<String, Integer>> wordCounts =
                text.flatMap(new LineSplitter())
                        .groupBy(0)
                        .sum(1);
        //输出,print底层会调用execute方法
        wordCounts.print();
        //执行方法
       // env.execute("word count example");
    }

    public static class LineSplitter implements FlatMapFunction<String, Tuple2<String, Integer>> {
        public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
            for (String word : s.split(" ")) {
                collector.collect(new Tuple2<String, Integer>(word, 1));
            }
        }
    }
}
