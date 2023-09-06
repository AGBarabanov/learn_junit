package org.example.tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideWebTest {

    @BeforeAll
    public static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.browser = "firefox";
        Configuration.browserSize = "1920x1080";
    }

    @BeforeEach
    void setUpEach(){
        open("https://selenide.org/");
    }


    static Stream<Arguments> selenideButtonTestDataProvider(){
        return Stream.of(
                Arguments.of("RU", List.of("С чего начать?", "Док", "ЧАВО", "Блог", "Javadoc", "Пользователи", "Отзывы")),
                Arguments.of("EN", List.of("Quick start", "Docs", "FAQ", "Blog", "Javadoc", "Users", "Quotes"))
        );
    }

    @MethodSource("selenideButtonTestDataProvider")
    @ParameterizedTest(name = "Проверка наличия кнопок из списка {1} на сайте селенида в локале {0}")
    @Tags({@Tag("BLOCKER"), @Tag("REGRESS")})
    void selenideButtonTest(String locale, List<String> buttons){
        $$("#languages a").find(text(locale)).click();
        $$(".main-menu-pages a").filter(visible).shouldHave(texts(buttons));
    }

}
