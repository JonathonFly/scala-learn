package com.ideal.netcare.extractors

/**
 * Created by syf on 2016/5/19.
 */
case class User(firstName: String, lastName: String, score: Int)

object ExtractorTest1 {
  def advance(xs: List[User]) = xs match {
    case User(_, _, score1) :: User(_, _, score2) :: _ => score1 - score2
    case _ => 0
  }

  def main(args: Array[String]) {
    val user1 = new User("ming", "xiao", 50)
    val user2 = new User("liu", "xiao", 60)
    val userList = List(user1, user2)
    println("advance users score = " + advance(userList))
  }
}

object ExtractorTest2 {

  trait User {
    def name: String
  }

  class FirstUser(val name: String) extends User

  class SecondUser(val name: String) extends User

  object FirstUser {
    def unapply(user: FirstUser): Option[String] = Some(user.name)
  }

  object SecondUser {
    def unapply(user: SecondUser): Option[String] = Some(user.name)
  }

  def main(args: Array[String]) {
    val user: User = new FirstUser("Tom")
    user match {
      case FirstUser(name) => println("Hello " + name)
      case SecondUser(name) => println("Welcome back, dear" + name)
    }
  }
}

object ExtractorTest3 {

  trait User {
    def name: String

    def score: Int
  }

  class FirstStudent(val name: String, val score: Int, val age: Int) extends User

  class SecondStudent(val name: String, val score: Int) extends User

  object FirstStudent {
    def unapply(user: FirstStudent): Option[(String, Int, Int)] = Some((user.name, user.score, user.age))
  }

  object SecondStudent {
    def unapply(user: SecondStudent): Option[(String, Int)] = Some((user.name, user.score))
  }

  def main(args: Array[String]) {
    val user:User=new FirstStudent("Jonathan",66,17)
    user match {
      case FirstStudent(name,score,age)=>println(s"FirstStudent name = $name, score = $score, age = $age")
      case SecondStudent(name, _)=>print(s"SecondStudent, Hello $name.")
    }
  }

}