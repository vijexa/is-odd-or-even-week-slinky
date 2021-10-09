package io.github.vijexa.isoddorevenweek

import slinky.core._
import slinky.core.annotations.react
import slinky.web.html._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scalacss.DevDefaults._

@JSImport("resources/App.css", JSImport.Default)
@js.native
object AppCSS extends js.Object

@JSImport("resources/logo.svg", JSImport.Default)
@js.native
object ReactLogo extends js.Object

@react class App extends StatelessComponent {
  type Props = Unit

  private val css = AppCSS

  def render() = {
    div(className := "App")(
      header(className := "App-header")(
        h1(className := "App-title")("Is it odd or even academic week? 😤😤"),
        h2(className := "App-sub-title")("We have an answer! 🥰"),
      ),
      p(className := "App-intro")(
        "Please select date when you started studying below ",
      ),
      DateHandler(),
      div(className := "footer")(
        """Made for my lovely girlfriend because I had nothing better to do
          and wanted to check out ScalaJS and all that stuff""",
        br(),
        span("by ", a(href := "https://github.com/vijexa")("@vijexa")),
      ),
    )

    //BuildingComponent.make(DatePicker(date => println(date), new js.Date))
  }
}
