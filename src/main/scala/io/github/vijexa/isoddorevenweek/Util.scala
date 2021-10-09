package io.github.vijexa.isoddorevenweek

import slinky.core.annotations.react
import slinky.web.html._
import slinky.core.FunctionalComponent
import slinky.core.facade.Fragment
import slinky.core.facade.Hooks._

import scalacss.DevDefaults._
import eu.timepit.refined._
import eu.timepit.refined.api.Refined
import eu.timepit.refined.auto._
import eu.timepit.refined.numeric._
import eu.timepit.refined.collection._
import eu.timepit.refined._
import eu.timepit.refined.auto._
import eu.timepit.refined.numeric._
import eu.timepit.refined.api.{RefType, Refined}
import eu.timepit.refined.boolean._
import eu.timepit.refined.char._
import eu.timepit.refined.collection._
import eu.timepit.refined.generic._
import eu.timepit.refined.string._
import shapeless.{::, HNil}
import scala.scalajs.js.annotation._
import scala.scalajs.js
import slinky.core.ExternalComponent
import slinky.core.ReactComponentClass
import java.util.Date
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.time.Instant
import java.time.ZoneOffset
import java.time.ZoneId
import java.time.LocalDateTime
import java.time.DayOfWeek

import typings.reactUseCookie

object Util {

  @js.native
  @JSImport("ordinal", JSImport.Default)
  def ordinal(n: Int): String = js.native

  def useCookie(name: String, value: String) = {
    val tuple = typings.reactUseCookie.mod.default(name, value)
    (tuple._1, tuple._2)
  }

  implicit def jsDateToLocalDate(jsDate: js.Date): LocalDate =
    LocalDate.of(jsDate.getFullYear.toInt, jsDate.getMonth.toInt + 1, jsDate.getDate.toInt)

  def localDateToJsDate(locaDate: LocalDate): js.Date =
    new js.Date(locaDate.toString)
}
