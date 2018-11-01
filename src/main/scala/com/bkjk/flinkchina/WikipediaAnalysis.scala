package com.bkjk.flinkchina




import org.apache.flink.api.common.functions.FoldFunction
import org.apache.flink.api.java.functions.KeySelector
import org.apache.flink.streaming.api.datastream.KeyedStream
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.connectors.wikiedits.{WikipediaEditEvent, WikipediaEditsSource}

/**
  * Created by 楊 on 2018/10/31.
  */
class WikipediaAnalysis {
  def main(args: Array[String]): Unit = {
    //获取环境
    val see = StreamExecutionEnvironment.getExecutionEnvironment
    //读取维基日志源
    val edits = see.addSource(new WikipediaEditsSource())

    //设定一个依据每个事件用户名（string类型）为Key的分组流
    val keyedEdits:KeyedStream[WikipediaEditEvent,String] = edits.keyBy(new KeySelector[WikipediaEditEvent,String] {
      override def getKey(in: WikipediaEditEvent): String = {
        return in.getUser
      }
    })

    val result = keyedEdits

  }
}
