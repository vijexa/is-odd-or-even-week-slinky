package io.github.vijexa.isoddorevenweek

import scalacss.DevDefaults._
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.core.facade.Fragment
import slinky.core.facade.Hooks._
import slinky.web.html._

import java.time.Duration
import java.time.Instant
import scala.scalajs.js.timers._

object BirthdayHandlerStyles extends StyleSheet.Inline {
  import dsl._

  val messageStyle = style(
    fontSize(1.25.em),
  )

  this.addToDocument()
}

@react object BirthdayHandler {
  import BirthdayHandlerStyles._

  def formatDuration(duration: Duration): String = {
    import duration._
    f"${toHours % 24}%02d:${toMinutes % 60}%02d:${toMillis / 1000 % 60}%02d"
  }

  val component = FunctionalComponent[Unit] { _ =>
    val (date, setDate) = useState(Instant.now())

    val birthdayDate = Instant.parse("2021-10-24T07:00:00Z")

    val durationLeft = Duration.between(date, birthdayDate)

    useEffect { () =>
      val interval = setInterval(1000)(setDate(Instant.now()))

      () => clearInterval(interval)
    }

    Fragment(
      div(
        if (durationLeft.isNegative() && durationLeft.toHours() > -48)
          Fragment(
            s"Happy birthday!💕 Click the present and say hi to proceed: ",
            a("🎁", href := "https://vk.com/im?media=&sel=-201659292"),
          )
        else if (durationLeft.toHours() < 24 && durationLeft.toHours() >= 0)
          s"${formatDuration(durationLeft)} left until your birthday present quest starts"
        else "",
        className := messageStyle.htmlClass,
      ),
    )
  }
}
