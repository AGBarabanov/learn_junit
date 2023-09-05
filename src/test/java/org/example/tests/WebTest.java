package org.example.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

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
    @Tag("BLOCKER")
    void searchGoogleTest(){
        $("[name=q]").setValue("selenide").pressEnter();
        $("#rcnt").shouldHave(text("https://ru.selenide.org"));
    }

    @Test
    @Tag("BLOCKER")
    void googlePhotoPopupTest(){
        $(".nDcEnd").click();
        $(byText("Ищите по изображениям с Google Объективом")).shouldBe(visible);
    }
}
