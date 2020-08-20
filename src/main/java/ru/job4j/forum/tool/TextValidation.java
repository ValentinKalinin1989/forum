package ru.job4j.forum.tool;

/**
 * validation for text
 */
public class TextValidation {

    /**
     * check string length without spaces
     *
     * @param string - string to check
     * @return true if string length between 3 and 100 symbols
     */
    public static boolean checkStrLengthBetween3And100(String string) {
        String str = string.trim();
        return str.length() >= 3 && string.length() <= 100;
    }

    /**
     * check string includes only spaces
     *
     * @param string - string to check
     * @return true if string includes only spaces
     */
    public static boolean checkOnOnlySpaceInStr(String string) {
        String str = string.trim();
        return str.isEmpty();
    }
}
