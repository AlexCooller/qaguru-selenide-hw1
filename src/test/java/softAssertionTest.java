import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class softAssertionTest {

    @BeforeAll
    static void onSettings() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
void  selenideTest(){
        open("/selenide/selenide");
        $("#wiki-tab").click();
        $(".markdown-body").shouldHave(text("Soft assertions")).shouldBe(visible);
        $(".markdown-body").$(byText("Soft assertions")).click();
        $("#user-content-3-using-junit5-extend-test-class").scrollTo();
        $(".markdown-body").shouldHave(text("3. Using JUnit5 extend test class:"));

        String textJunit5 = """
                @ExtendWith({SoftAssertsExtension.class})
                class Tests {
                  @Test
                  void test() {
                    Configuration.assertionMode = SOFT;
                    open("page.html");
                
                    $("#first").should(visible).click();
                    $("#second").should(visible).click();
                  }
                }
                """;

        $("#wiki-body").shouldBe(text(textJunit5));



    }

}
