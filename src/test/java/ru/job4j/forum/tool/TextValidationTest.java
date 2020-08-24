package ru.job4j.forum.tool;

import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

class TextValidationTest {

    @Test
    void checkStrLengthBetween3And100() {
        String twoChar = "tg";
        String threeChar = "1234";
        String tenChar = "1234567890";
        String hundredOneChar = "1234567890"
                + "1234567890"
                + "1234567890"
                + "1234567890"
                + "1234567890"
                + "1234567890"
                + "1234567890"
                + "1234567890"
                + "1234567890"
                + "1234567890"
                + "1";
        String twoCharAndSpaces = "    tg       ";

        boolean firstResult = TextValidation.checkStrLengthBetween3And100(twoChar);
        boolean secondResult = TextValidation.checkStrLengthBetween3And100(threeChar);
        boolean thirdResult = TextValidation.checkStrLengthBetween3And100(tenChar);
        boolean fourthResult = TextValidation.checkStrLengthBetween3And100(hundredOneChar);
        boolean firthResult = TextValidation.checkStrLengthBetween3And100(hundredOneChar);

        assertThat(firstResult, is(false));
        assertThat(secondResult, is(true));
        assertThat(thirdResult, is(true));
        assertThat(fourthResult, is(false));
        assertThat(firthResult, is(false));
    }

    @Test
    void checkOnOnlySpaceInStr() {
        String spaces = "             ";

        boolean result = TextValidation.checkOnOnlySpaceInStr(spaces);

        assertThat(result, is(true));

    }
}