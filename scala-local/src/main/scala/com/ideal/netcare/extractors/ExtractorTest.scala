package com.ideal.netcare.extractors

/**
 * Created by root on 2016/5/19.
 */
case class User(firstName: String, lastName: String, score: Int)

object ExtractorTest1 {
  def advance(xs: List[User]) = xs match {
    case User(_, _, score1) :: User(_, _, score2) :: _ => score1 - score2
    case _ => 0
  }
  def main(args: Array[String]) {
    val user1=new User("ming","xiao",50)
    val user2=new User("liu","xiao",60)
    val userList =List(user1,user2)
    println("advance users score = "+advance(userList))
  }
}

object ExtractorTest2{
  def main(args: Array[String]) {
    println("name")
  }
}