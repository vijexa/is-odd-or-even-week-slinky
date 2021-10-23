package io.github.vijexa.isoddorevenweek

import io.github.vijexa.isoddorevenweek.Util._
import scalacss.DevDefaults._
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.core.facade.Fragment
import slinky.core.facade.Hooks._
import slinky.web.html._
import typings.reactDatePicker.components.ReactDatePicker

import java.time.LocalDate
import java.time.temporal.ChronoUnit
import scala.scalajs.js

object DateHandlerStyles extends StyleSheet.Inline {
  import dsl._

  val calendarContainer = style(
    width(80.%%),
    fontSize(1.25.em),
  )

  val weekMessageStyle = style(
    fontSize(1.25.em),
  )

  val boldTextStyle = style(
    fontWeight.bold,
  )

  this.addToDocument()
}

@react object DateHandler {
  import DateHandlerStyles._

  def getMondayDate(date: LocalDate): LocalDate =
    date.minusDays(date.getDayOfWeek.getValue - 1L)

  def countWeeks(startingDate: LocalDate, today: LocalDate): Int =
    ChronoUnit.WEEKS.between(getMondayDate(startingDate), getMondayDate(today)).toInt + 1

  def weeksMessage(weeks: Int) =
    span(
      s"${ordinal(weeks)} week, ",
      span(if (weeks % 2 == 0) "even" else "odd", className := boldTextStyle.htmlClass),
    )

  val component = FunctionalComponent[Unit] { _ =>
    val todayDate = new js.Date

    val (cookie, setCookie) = useCookie("selected", todayDate.toEpochDay.toString)
    val (selectedDate, setSelectedDate) =
      useState(cookie.toLongOption.map(LocalDate.ofEpochDay(_)).map(localDateToJsDate))

    val weeks = countWeeks(
      jsDateToLocalDate(selectedDate.getOrElse(todayDate)),
      jsDateToLocalDate(todayDate),
    )

    Fragment(
      ReactDatePicker
        .onChange {
          case (date: js.Date, _) =>
            setSelectedDate(Option(date))
            setCookie(Option(date).getOrElse(todayDate).toEpochDay.toString, ())
          case _ => ()
        }
        .value(selectedDate.getOrElse[js.Date](todayDate))
        .maxDate(todayDate)
        .format("yyyy-MM-dd")
        .className(calendarContainer.htmlClass),
      br(),
      br(),
      div(s"Today is ${todayDate: LocalDate}", className := weekMessageStyle.htmlClass),
      br(),
      div("it's ", weeksMessage(weeks), className := weekMessageStyle.htmlClass),
      div("next will be ", weeksMessage(weeks + 1)),
    )
  }
}
