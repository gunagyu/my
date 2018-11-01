package test

import org.apache.flink.api.scala._

/**
  * Created by root on 12/15/15.
  */
object WordCount {
  def main(args: Array[String]): Unit = {

    case  class WC(val word:String,val count:Int){
      def this(){
        this(null,-1)
      }
    }

    //获取环境
    val env  = ExecutionEnvironment.getExecutionEnvironment
    val text = env.fromElements( "who's there?"+
      "I think I hear them.Stand ho!").flatMap(_.split("\\s")).map{
      x=>
        new WC(x,1)
    }
    val count = text.groupBy(_.word)reduce{
      (w1,w2) => new WC(w1.word,w1.count+w2.count)
    }

count.print()
    val count = text.flatMap{
      _.toLowerCase.split("\\W+")filter(_.nonEmpty)
    }.map((_,1))
        .groupBy(0).sum(1)
    count.print()

  }
}