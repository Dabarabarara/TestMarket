package Helpers;

import org.junit.Assert;

import java.util.Hashtable;

/**
 * Класс для работы со словарями строковых элементов (локаторы, URL и прочие).
 * Created by Vladimir V. Klochkov on 19.01.2017.
 * Updated by Vladimir V. Klochkov on 22.04.2021.
 */
public class Dictionary {
    /*******************************************************************************************************************
     * Константы класса.
     ******************************************************************************************************************/
    private static final String pref = ">>> (Dictionary): ";

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final Hashtable<String, String> dictionary = new Hashtable<>();

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    /**
     * Добавляет в словарь новый элемент.
     *
     * @param key   ключ элемента
     * @param value значение элемента
     */
    public void add(String key, String value) {
        // Проверка переданных параметров
        Assert.assertNotNull("ключ для поиска в словаре - null !", key);
        Assert.assertFalse("ключ для поиска в словаре - пустая строка !", key.isEmpty());
        if (value == null || value.trim().isEmpty()) {
            System.out.println(pref + "значение для ключа [" + key +
                    "] отсутствует - пара не будет добавлена в словарь.");
            return;
        }

        // Проверка на дубликат ключа
        boolean keyAbsent = dictionary.get(key) == null;
        Assert.assertTrue(pref + "[" + key + "] - попытка добавить в словарь уже существующий ключ !", keyAbsent);

        // Добавляем пару в словарь
        dictionary.put(key, value);
    }

    /**
     * Ищет элемент в словаре и возвращает его значение.
     *
     * @param key ключ элемента
     * @return значение элемента
     */
    public String find(String key) {
        // Проверка словаря
        Assert.assertFalse(pref + "Словарь пуст !", dictionary.isEmpty());

        // Проверка переданного параметра
        Assert.assertNotNull("ключ для поиска в словаре - null !", key);
        Assert.assertFalse("ключ для поиска в словаре - пустая строка !", key.isEmpty());

        // Поиск в словаре
        String value = dictionary.get(key);

        // Проверка результата поиска
        Assert.assertNotNull(pref + "[" + key + "] - требуемый ключ не найден в словаре !", value);

        return value;
    }

    /**
     * Возвращает текущее количество элементов в словаре.
     *
     * @return текущее количество элементов в словаре
     */
    public int size() {
        return dictionary.size();
    }
}
