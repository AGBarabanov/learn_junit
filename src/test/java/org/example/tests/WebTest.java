package org.example.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebTest {

    @BeforeAll
    public static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.browser = "firefox";
        Configuration.browserSize = "1920x1080";
    }

    @BeforeEach
    void setUpEach(){
        open("https://www.google.com/");
    }

    @DisplayName("Проверка наличия урла selenide.org" +
                "в результатах выдачи гугла по запросу 'selenide'")
    @Test
    @Tags({@Tag("BLOCKER"), @Tag("REGRESS")})
    void searchGoogleSelenideTest(){
        $("[name=q]").setValue("selenide").pressEnter();
        $("#rcnt").shouldHave(text("https://ru.selenide.org"));
    }


    @DisplayName("Проверка наличия урла junit.org" +
            "в результатах выдачи гугла по запросу 'junit 5'")
    @Test
    @Tags({@Tag("BLOCKER"), @Tag("REGRESS")})
    void searchGoogleJUnitTest(){
        $("[name=q]").setValue("junit 5").pressEnter();
        $("#rcnt").shouldHave(text("https://junit.org"));
    }


    @ValueSource(strings = {"example", "example2"})
    @DisplayName("Проверка поп апа загрузки фото")
    @ParameterizedTest
    @Tags({@Tag("BLOCKER"), @Tag("REGRESS")})
    void googlePhotoPopupTest(String arg){
        $(".nDcEnd").click();
        $(byText("Ищите по изображениям с Google Объективом")).shouldBe(visible);
    }


   /*
     @CsvSource({
             "selenide, https://ru.selenide.org",
             "junit 5, https://junit.org"
     })
    */
    @ParameterizedTest(name = "Проверка наличия урла сайта {1} " +
            "в результатах выдачи гугла по запросу {0}")
    @CsvFileSource(
            resources = "/extest.csv",
            delimiter = '|'
    )
    @Tags({@Tag("BLOCKER"), @Tag("REGRESS")})
    public void searchGoogleTest(String searchQuery, String expectedUrl){
        $("[name=q]").setValue(searchQuery).pressEnter();
        $("#rcnt").shouldHave(text(expectedUrl));
    }
}
