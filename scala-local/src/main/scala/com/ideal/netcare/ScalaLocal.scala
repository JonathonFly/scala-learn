package com.ideal.netcare

import akka.actor.Actor.Receive
import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.collection.script.Start

/**
 * Created by root on 2016/5/4.
 */
object ScalaLocal {

  /*def withScanner(f:File,op: Scanner => Unit) ={
    val scanner=new Scanner(f.bufferedReader)
    try {
      op(scanner)
    }
    finally{
      scanner.close()
    }
  }*/

  /* class Person(firstName: String, secondName: String) {
     private var age = 0

     def age_ = age

     def _age(newAge: Int) = age = newAge

     def fullName() = firstName + " " + secondName

     override def toString() = fullName()

   }*/

  /*def WithClose(closeable: {def close():Unit},op:{def close():Unit}=>Unit): Unit ={
    try {
      op(closeable)
    }
    finally{
      closeable.close()
    }
  }

  class Connection{
    def close()=println("close connection.")
  }*/

  /*def fib(in: Any): Long = in match {
    case 0 => 0L
    case 1 => 1L
    case n: Long => fib(n - 1) + fib(n - 2)
    case _ => 0L
  }

  def fib1(in: Int): Long = {
    var res = 0L
    if (in == 0) res = 0L
    if (in == 1) res = 1L
    var first = 0L
    var second = 1L
    for(i <- 2 to in){
      res = first + second
      first = second
      second = res
    }
    return res
  }*/

  /*class ScalaCurrentVersion(val url:String){
    lazy val source={
      println("fetching from url...")
      Source.fromURL(url).getLines().toList
    }
    lazy val majorVersion=source.find(_.contains("version.major"))
    lazy val minorVersion=source.find(_.contains("version.minor"))
  }*/


  def main(args: Array[String]) {

    implicit val system=akka.actor.ActorSystem("RemoteSystem",ConfigFactory.load.getConfig("remote"))

    class EchoServer extends Actor{
      override def receive: Receive = {
        case msg :String =>println("echo "+msg)
      }
    }

    val server =system.actorOf(Props[EchoServer],name="echoServer")

    val echoClient=system.actorSelection("akka://RemoteSystem@127.0.0.1:2552/user/echoServer")

    echoClient ! "Hi Remote"
    system.shutdown()


    /*val words=List("tom is a stupid boy","I have huge eyes","are you serious","totally kidding me")
    def wordcount(line:String): Int ={
      line.split("\\s+").count("are"== _)
    }

    val num=words.par.map(wordcount).par.reduceLeft(_ + _)

    println("num = "+num)*/

    /*val urls=List("http://scala-lang.org","https://github.com/yankay/scala-tour")

    def fromUrl(url:String)=scala.io.Source.fromURL(url).getLines().mkString("\n")

    val t= System.currentTimeMillis()
    urls.map(fromUrl(_))
    println("getSource cost time:"+(System.currentTimeMillis()-t)+"ms")

    val t1= System.currentTimeMillis()
    urls.par.map(fromUrl(_))
    println("parallel getSource cost time:"+(System.currentTimeMillis()-t1)+"ms")*/


    /*implicit val ec = scala.concurrent.ExecutionContext.Implicits.global
    implicit val system = akka.actor.ActorSystem()

    val versionUrl = "https://github.com/JonathonFly/scala/blob/master/build.number"
    val fromUrl = actor(new Act {
      become { case url: String => {
        val list=scala.io.Source.fromURL(url).getLines().toList
        val majorVersion=list.filter(_.contains("version.major"))
        val minorVersion=list.filter(_.contains("version.minor"))
        sender ! "majorVersion = "+majorVersion+"\n minorVersion = "+minorVersion
      }
      }
    })

    val version = fromUrl.ask(versionUrl)(akka.util.Timeout(10 * 1000))
    version onComplete{
      case msg => println(msg.getOrElse());system.shutdown()
    }*/


    /*implicit val system=ActorSystem()
    class EchoServer(name:String) extends Actor {
      override def receive: Receive = {
        case msg:String =>println("Server: "+name+" echo: "+msg+" thread: "+Thread.currentThread().getName)
      }
    }

    val echoServers=(1 to 10).map(x => system.actorOf(Props(new EchoServer(x.toString)).withDispatcher(CallingThreadDispatcher.Id)))
    (1 to 10).foreach(msg =>echoServers(scala.util.Random.nextInt(10)) ! msg.toString)
    system.shutdown()*/

    /* implicit val system = ActorSystem()
     val echoServer =actor(new Act{
       become{
         case msg=>println("echo "+msg)
       }
     })

     echoServer ! "hi"
     system.shutdown()*/


    /* val version=new ScalaCurrentVersion("https://github.com/JonathonFly/scala/blob/master/build.number")
     println("get scala version from "+version.url)
     version.majorVersion.foreach(println _)
     version.minorVersion.foreach(println _)*/

    /*val list=List("Tom and  Jerry","Jerry sucks","Iron Man","Johnathan has a huge bomb")

    def wordCount(in : String):Int= in.split("\\s+").count(_.contains("an"))

    val counts=list.map(wordCount(_)).reduceLeft(_+_)
    println(counts)*/

    /*val num = 44
    val before = System.currentTimeMillis()
    println(fib(num))
    val after = System.currentTimeMillis()
    println("递归fib(" + num + ")用时" + (after - before) + "ms")
    val before1 = System.currentTimeMillis()
    println(fib1(num))
    val after1 = System.currentTimeMillis()
    println("非递归fib1(" + num + ")用时" + (after1 - before1) + "ms")*/

    /* def square1(a: Int) = a * a

     val square2 = (a: Int) => a * a

     def square3(a: Int) = {
       a * a
     }

     def addOne(f: Int => Int, a: Int) = f(a) + 1

     println("square1(2)=" + square1(2))
     println("square2(2)=" + square2(2))
     println("square3(2)=" + square3(2))
     println("addOne(square1,2)=" + addOne(square1, 2))*/

    /*withScanner(File("D:/test/1.txt"),scanner => {
      while(scanner.hasNext()) {
        println("word is :" + scanner.next())
      }
    })*/

    /*val logEnable=false
    def log(msg: => String)={
      if(logEnable) println(msg)
    }
    val myMsg="Hello buddy!"
    log(myMsg + 11/0)*/

    /* val syf:Person=new Person("sun","yunfei")

     println("Person:"+syf.toString())
     syf._age(26)
     println("age:"+syf.age_)*/

    /*val conn:Connection=new Connection()
    WithClose(conn,conn=>println("do something with Connection"))*/

  }
}
