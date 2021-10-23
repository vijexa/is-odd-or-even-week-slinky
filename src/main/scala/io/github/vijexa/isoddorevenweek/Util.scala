package io.github.vijexa.isoddorevenweek

import java.time.LocalDate
import scala.annotation.nowarn
import scala.scalajs.js
import scala.scalajs.js.annotation._

object Util {

  @nowarn
  @js.native
  @JSImport("ordinal", JSImport.Default)
  def ordinal(n: Int): String = js.native

  def useCookie(name: String, value: String) = {
    val tuple = typings.reactUseCookie.mod.default(name, value)
    (tuple._1, tuple._2)
  }

  implicit def jsDateToLocalDate(jsDate: js.Date): LocalDate =
    LocalDate.of(jsDate.getFullYear().toInt, jsDate.getMonth().toInt + 1, jsDate.getDate().toInt)

  def localDateToJsDate(locaDate: LocalDate): js.Date =
    new js.Date(locaDate.toString)
}
