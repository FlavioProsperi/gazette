package gazette

import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Calendar

import gazette.Client.GazetteAction

import scalaz.{Applicative, Disjunction}
import scalaz.concurrent.Task
import scalaz.syntax.apply._

object IO {
  def currentDate: Task[Date] =
    Task.delay {
      val format = new SimpleDateFormat("yyyy-MM-dd")
      Date.valueOf(format.format(Calendar.getInstance().getTime()))
    }

  def putStr(s: String): Task[Unit] = Task.delay(print(s))

  def gputStr(s: String): GazetteAction[Unit] = Applicative[GazetteAction].point(print(s))

  def putStrLn(s: String): Task[Unit] = Task.delay(println(s))

  def gputStrLn(s: String): GazetteAction[Unit] = Applicative[GazetteAction].point(println(s))

  def readLn: Task[String] = Task.delay(readLine())

  def greadLn: GazetteAction[String] = Applicative[GazetteAction].point(readLine())

  def prompt(s: String): Task[String] = putStr(s ++ ": ") *> readLn

  def gprompt(s: String): GazetteAction[String] = gputStr(s ++ ": ") *> greadLn
}
