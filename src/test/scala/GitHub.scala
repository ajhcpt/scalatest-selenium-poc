import org.junit.runner.RunWith
import org.openqa.selenium.By
import org.scalatest.{FunSuite, GivenWhenThen}
import org.scalatest.selenium.Chrome
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GitHubChrome extends GitHub with Chrome

abstract class GitHub extends CommonSelenium with GivenWhenThen {
  System.setProperty("webdriver.chrome.driver", "C:\\Users\\andrew\\workspace\\scalatest-selenium-poc\\drivers\\chromedriver.exe");

  feature("git hub") {
    scenario("login to github") {
      Given("the github url")
      go to baseUrl

      When("the sign in button is clicked")
      click on cssSelector("body header div div div a.btn.site-header-actions-btn.mr-2")

      And("the username and password is filled in")
      textField(CssSelectorQuery("#login_field")).value = "ajhcpt"
      webDriver.findElement(By.cssSelector("#password")).sendKeys("data09base")
      click on "#login form div.auth-form-body input.btn.btn-primary.btn-block"

      Then("the user will be signed it")
    }
  }
}
