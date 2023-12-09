package Helpers;

import org.apache.commons.lang3.StringUtils;

/**
 * Класс для работы с проверками, которые не прерывают выполнение теста.
 * Created by Vladimir V. Klochkov on 18.10.2016.
 * Updated by Vladimir V. Klochkov on 12.08.2020.
 */
public class SoftAssert
{
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private static final Integer repeatNumber = 150; // Длина строки-разделителя в символах
    private static final String delimiter = "#";     // Символ, из которого состоит строка-разделитель

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    /**
     * Возвращает строку-разделитель.
     * @return строка-разделитель
     */
    private static String getDelimiterString() { return StringUtils.repeat(delimiter, repeatNumber); }

    /**
     * Внутренний метод. Проверяет два объекта на идеентичность. Перенесен из класса [Assert.java].
     * @param expected ожидаемое значение
     * @param actual   актуальное значение
     * @return true если оба объекта идеентичны
     */
    private static boolean isEquals(Object expected, Object actual) { return expected.equals(actual); }

    /**
     * Внутренний метод. Проверяет два объекта на идеентичность с учетом null. Перенесен из класса [Assert.java].
     * @param expected ожидаемое значение
     * @param actual   актуальное значение
     * @return true если оба объекта идеентичны или оба объекта имеют пустые (null) указатели
     */
    private static boolean equalsRegardingNull(Object expected, Object actual)
    {
        if (expected == null) return actual == null;
        else return isEquals(expected, actual);
    }

    /**
     * Внутренний метод. Используется для вывода в консоль сообщения об ошибке при сравнении двух объектов.
     * @param methodName      имя вызывающего метода (префикс сообщения об ошибке)
     * @param compareType     тип сравнения двух объектов (совпадение или несовпадение)
     * @param customerMessage дополнительное пользовательское сообщение об ошибке при сравнении двух объектов
     * @param expectedValue   ожидаемое значение для информативности
     * @param actualValue     актуальное значение для информативности
     */
    private static void printCompareErrorMessage(
            String methodName, String compareType, String customerMessage, Object expectedValue, Object actualValue)
    {
        String cleanMessage = customerMessage == null ? "" : customerMessage;
        System.err.println(getDelimiterString());
        System.err.println(methodName + compareType);
        System.err.println(methodName + cleanMessage);
        System.err.println(methodName + "Ожидаемое значение: [" + expectedValue +
                "], актуальное значение: [" + actualValue + "].");
        System.err.println(getDelimiterString());
    }

    /**
     * Проверяет два объекта на идеентичность с учетом null. Если объекты не идеентичны в консоль записывается
     * соответствующее сообщение об ошибке.
     * @param message  пользовательское сообщение об ошибке
     * @param expected ожидаемое значение
     * @param actual   актуальное значение
     */
    public static void assertEquals(String message, Object expected, Object actual)
    {
        if (!equalsRegardingNull(expected, actual))
            printCompareErrorMessage(">>> (assertEquals) ", "Сравниваемые значения не совпадают !",
                    message, expected, actual);
    }

    /**
     * Проверяет два объекта на неидеентичность с учетом null. Если объекты идеентичны в консоль записывается
     * соответствующее сообщение об ошибке.
     * @param message  пользовательское сообщение об ошибке
     * @param expected ожидаемое значение
     * @param actual   актуальное значение
     */
    public static void assertNotEquals(String message, Object expected, Object actual)
    {
        if (equalsRegardingNull(expected, actual))
            printCompareErrorMessage(">>> (assertNotEquals) ", "Сравниваемые значения совпадают !",
                    message, expected, actual);
    }

    /**
     * Проверяет условие на истину. Если условие не истинно в консоль записывается
     * соответствующее сообщение об ошибке.
     * @param message   пользовательское сообщение об ошибке
     * @param condition проверяемое условие
     */
    public static void assertTrue(String message, boolean condition)
    {
        if (!condition)
            printCompareErrorMessage(">>> (assertTrue) ", "Условие ложно !", message, "true", "false");
    }

    /**
     * Проверяет условие на ложь. Если условие не ложно в консоль записывается
     * соответствующее сообщение об ошибке.
     * @param message   пользовательское сообщение об ошибке
     * @param condition проверяемое условие
     */
    public static void assertFalse(String message, boolean condition)
    {
        if (condition)
            printCompareErrorMessage(">>> (assertFalse) ", "Условие истинно !", message, "false", "true");
    }
}
