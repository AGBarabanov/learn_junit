package org.example.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SimpleTest {

    @DisplayName("Проверка, чего-то[1]")
    @Test
    void firstTest(){
        Assertions.assertEquals(1, 1, "1 не равен тому, что мы получили");
    }

    @Disabled("JIRA-2342")
    @DisplayName("Проверка, чего-то[2]")
    @Test
    void secondTest(){
        Assertions.assertEquals(1, 1, "1 не равен тому, что мы получили");
    }


}
