package com.bkjk.flinkstream

import java.util.Properties

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer08

import scala.util.parsing.json.JSONObject

/**
  * Created by 楊 on 2018/10/27 0027.
  */
class StreamLearn {
  def main(args: Array[String]): Unit = {
    //获取流式环境
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    //设置打印日志
    env.getConfig.enableSysoutLogging()
    //设置窗口的时间单位

    //设置全局并发数
    env.setParallelism(2)
    val properties = new Properties()
    //kafka启动参数
    properties.setProperty("broker.servers","kafka bootstrap.servers")
    //kafka消息主题与应用名(使用自定义工具类)
    val manager = new FlinkKafkaManager("kafka.topic","app.name",properties)
    //使用JsonObject反序列化接收kafka
    val consumer = manager.build(Class[JSONObject])
    //从最新的消息开始接收
    consumer.setStartFromEarliest()
    //获取DataStream
    val dataStream = env.addSource(consumer)

  }
}
