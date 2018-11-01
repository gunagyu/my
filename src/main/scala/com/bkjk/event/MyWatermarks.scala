package com.bkjk.event

import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks

/**
  * Created by 楊 on 2018/10/28 0028.
  * 自定义的周期水印生成器
  */
class MyWatermarks extends AssignerWithPeriodicWatermarks[String]{
/*  val maxOutOfOrderness = 3500L;//3.5s

  override def getCurrentWatermark = {

  }

  override def extractTimestamp(t: String, l: Long) = {

  }*/
}
