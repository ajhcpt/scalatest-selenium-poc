import java.io.PrintWriter

import org.openqa.selenium.WebDriver
import org.scalatest._
import org.scalatest.concurrent.Eventually
import org.scalatest.concurrent.IntegrationPatience
import org.scalatest.events.Event
import org.scalatest.events.TestFailed
import org.scalatest.selenium.Driver
import org.scalatest.selenium.WebBrowser

trait CommonSelenium extends FeatureSpec with WebBrowser with Driver with Eventually with Matchers with IntegrationPatience with BeforeAndAfterAll {
  self: Driver =>

  override def run(testName: Option[String], args: Args): Status = {
    val rep = new MyReporter(args.reporter, this)
    super.run(testName, args.copy(reporter = rep))
  }

  setCaptureDir("/temp/capture")

  val baseUrl: String = "http://www.github.com/"

  override def afterAll {
    quit()
  }

  def maximize() = webDriver.manage().window().maximize()
}

class MyReporter(val aggregateReporter: Reporter, web: WebBrowser)(implicit val webDriver: WebDriver) extends Reporter {
  override def apply(event: Event): Unit = {
    aggregateReporter.apply(event)
    event match {
      case e: TestFailed => {
        System.err.println("test failed " + e.suiteName + "/" + e.testName)
        web.captureTo(e.suiteName + "." + e.testName + ".png")
        println("html written to " + writeToFile("/temp/capture/" + e.suiteName + "." + e.testName + ".html", web.pageSource))
      }
      case _ => // ignore
    }
  }

  private def writeToFile(f: String, s: String) = { Some(new PrintWriter(f)).foreach { p => p.write(s); p.close }; f }
}