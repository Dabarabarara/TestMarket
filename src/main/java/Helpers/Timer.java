package Helpers;

import org.junit.Assert;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Класс для работы со временными интервалами (проверка длительности выполнения шагов и методов).
 * Created by Evgeniy Glushko on 25.07.2016.
 * Updated by Vladimir V. Klochkov on 19.03.2021.
 */
public class Timer {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private static long applicationStartTime = 0;               // Время начала работы теста
    private static Date publishNoticeDate;                      // Дата и время публикации извещения о закупке
    private static String LOGNAME;                              // Имя файла с журналом времени выполнения
    private final List<Long> startTimeList = new ArrayList<>(); // Стек для значений [начало временного интервала]
    private final List<Long> endTimeList = new ArrayList<>();   // Стек для значений [конец  временного интервала]
    private final static Map<String, String> monthsForReplacement =
            new HashMap<String, String>() {{
                put("января", "Январь");
                put("февраля", "Февраль");
                put("марта", "Март");
                put("апреля", "Апрель");
                put("мая", "Май");
                put("июня", "Июнь");
                put("июля", "Июль");
                put("августа", "Август");
                put("сентября", "Сентябрь");
                put("октября", "Октябрь");
                put("ноября", "Ноябрь");
                put("декабря", "Декабрь");
            }};                                                 // Список месяцев для замещения в датах приложения

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    /**
     * Возвращает случайное целое число с заданной разрядностью в виде строки.
     *
     * @param length число разрядов
     * @return случайное целое число с заданной разрядностью в виде строки
     */
    public String getRandomDecimalId(int length) {
        String id = String.valueOf(Math.random()).replace(".", "");
        while (length > id.length()) id += id;
        return id.substring(0, length);
    }

    /**
     * Возвращает строку, в которой название месяца заменено на приемлимое для текущего формата поля с датой.
     *
     * @param stringWithMonth строка, которая возможно содержит некорректное с точки зрения формата поля название месяца
     * @return строка, в которой название месяца заменено на приемлимое для текущего формата поля с датой
     */
    public String replaceMonthNameToCorrectValue(String stringWithMonth) {
        for (Map.Entry<String, String> entry : monthsForReplacement.entrySet())
            if (stringWithMonth.contains(entry.getKey()))
                return stringWithMonth.replace(entry.getKey(), entry.getValue());

        return stringWithMonth;
    }

    /**
     * Возвращает значение глобальной переменной [Дата и время публикации извещения о закупке].
     *
     * @return значение глобальной переменной [Дата и время публикации извещения о закупке]
     */
    public static Date getPublishNoticeDate() {
        if (Timer.publishNoticeDate == null) Timer.publishNoticeDate = new Date();
        return Timer.publishNoticeDate;
    }

    /**
     * Возвращает значение глобальной переменной [Дата и время публикации извещения о закупке] со сдвигом на указанное
     * число минут.
     *
     * @param minutes величина сдвига в минутах
     * @return значение глобальной переменной [Дата и время публикации извещения о закупке] со сдвигом на указанное
     * число минут
     */
    public static Date getPublishNoticeDate(int minutes) {
        if (Timer.publishNoticeDate == null) Timer.publishNoticeDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Timer.publishNoticeDate);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

    /**
     * Возвращает значение глобальной переменной [Дата и время публикации извещения о закупке] в формате
     * [ггггММддччммсс] - пример [20200917145621].
     *
     * @return значение глобальной переменной [Дата и время публикации извещения о закупке] в формате [ггггММддччммсс]
     */
    public String getPublishNoticeDateInFormatyyyyMMddhhmmss() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        return dateFormat.format(Timer.getPublishNoticeDate());
    }

    /**
     * Добавляет в стек начало временного интервала.
     */
    public void start() {
        if (applicationStartTime == 0) applicationStartTime = System.currentTimeMillis();
        this.startTimeList.add(System.currentTimeMillis());
        String currentDateTime = this.getCurrentDateTime("dd.MM.yyyy HH:mm:ss");
        System.out.printf("---> Текущее время: [%s].%n", currentDateTime);
    }

    /**
     * Добавляет в стек конец временного интервала.
     */
    private void end() {
        this.endTimeList.add(System.currentTimeMillis());
    }

    /**
     * Выводит в консоль общее затраченное время в секундах для шага теста из которого он был вызван.
     *
     * @param name имя шага
     */
    public void printStepTotalTime(String name) {
        this.end();

        int sSize = this.startTimeList.size();
        int eSize = this.endTimeList.size();

        if (sSize == eSize)
            System.out.printf("---> Стек регистрации времени сбалансирован: старт [%d] завершение [%d].%n", sSize, eSize);
        else
            System.err.printf("---> Размер стека регистрации времени: старт [%d] завершение [%d].%n", sSize, eSize);

        Assert.assertTrue(">>> (printStepTotalTime) Ошибка при работе, неверно инициализирован стек !",
                eSize > 0 && sSize >= eSize);

        int totalHandles = ConfigContainer.getInstance().getDriver().getWindowHandles().size();
        long timeFromStart = ((System.currentTimeMillis() - applicationStartTime) / 1000) / 60;
        long deltaTime = (this.endTimeList.get(eSize - 1) - this.startTimeList.get(sSize - 1)) / 1000;
        String currentDateTime = this.getCurrentDateTime("dd.MM.yyyy HH:mm:ss");

        this.startTimeList.remove(sSize - 1);   // Удаляем использованный элемент из стека старт
        this.endTimeList.remove(eSize - 1);     // Удаляем использованный элемент из стека финиш

        if (ConfigContainer.getInstance().getConfigParameter("LogStepsDuration").equals("on"))
            System.out.printf("---> Текущее время: [%s], общее время выполнения теста: [%d] мин., время выполнения " +
                            "шага: [%d] сек., число открытых вкладок: [%d].%n", currentDateTime, timeFromStart,
                    deltaTime, totalHandles);

        if (ConfigContainer.getInstance().getConfigParameter("WriteSeparateDurationLog").equals("on"))
            Assert.assertTrue("Не удалось сделать запись в журнал времени выполнения !",
                    this.logTime(name, deltaTime));
    }

    /**
     * Возвращает дату и время в виде строки, сдвинутую на заданный интервал в прошлое или будущее.
     *
     * @param date           оригинальная дата
     * @param shiftType      тип временного сдвига
     * @param amountAsString интервал (положительный или отрицательный), на который следует осуществить сдвиг
     * @return дата и время в виде строки, сдвинутая на заданный интервал в прошлое или будущее
     */
    public String getDateTimeShift(Date date, String shiftType, String amountAsString) {
        return this.getDateTimeShiftWithDateFormat(date, shiftType, amountAsString, "dd.MM.yyy HH:mm");
    }

    /**
     * Возвращает дату и время в виде строки, сдвинутую на заданный интервал в прошлое или будущее в предложенном формате.
     *
     * @param date             оригинальная дата
     * @param shiftType        тип временного сдвига
     * @param amountAsString   интервал (положительный или отрицательный), на который следует осуществить сдвиг
     * @param simpleDateFormat формат преобразования даты
     * @return дата и время в виде строки, сдвинутая на заданный интервал в прошлое или будущее
     */
    public String getDateTimeShiftWithDateFormat(Date date, String shiftType, String amountAsString,
                                                 String simpleDateFormat) {
        int amount = Integer.parseInt(amountAsString);                   // Преобразуем интервал из строки в число
        DateFormat dateFormat = new SimpleDateFormat(simpleDateFormat);  // Формат преобразования: Date -> String
        Calendar calendar = Calendar.getInstance();                      // Для сдвигов во времени нам нужен календарь

        if (date == null) date = new Date();
        calendar.setTime(date);

        switch (shiftType) {
            //----------------------------------------------------------------------------------------------------------
            case "SECONDS":
                calendar.add(Calendar.SECOND, amount);
                break;
            //----------------------------------------------------------------------------------------------------------
            case "MINUTES":
                calendar.add(Calendar.MINUTE, amount);
                break;
            //----------------------------------------------------------------------------------------------------------
            case "HOURS":
                calendar.add(Calendar.HOUR, amount);
                break;
            //----------------------------------------------------------------------------------------------------------
            case "DAYS":
                calendar.add(Calendar.HOUR, amount * 24);
                break;
            //----------------------------------------------------------------------------------------------------------
            case "WEEKS":
                calendar.add(Calendar.HOUR, (amount * 24) * 7);
                break;
            //----------------------------------------------------------------------------------------------------------
            case "MONTHS":
                calendar.add(Calendar.MONTH, amount);
                break;
            //----------------------------------------------------------------------------------------------------------
            case "YEARS":
                calendar.add(Calendar.YEAR, amount);
                break;
            //----------------------------------------------------------------------------------------------------------
            default:
                Assert.fail(String.format(
                        ">>> В метод (getDateTimeShift) передан некорректный параметр (shiftType): '%s'.", shiftType));
                break;
            //----------------------------------------------------------------------------------------------------------
        }

        return dateFormat.format(calendar.getTime());
    }

    /**
     * Возвращает дату в виде строки, сдвинутую на заданный интервал в прошлое или будущее.
     *
     * @param date           оригинальная дата
     * @param shiftType      тип временного сдвига
     * @param amountAsString интервал (положительный или отрицательный), на который следует осуществить сдвиг
     * @return дата в виде строки, сдвинутая на заданный интервал в прошлое или будущее
     */
    public String getDateShift(Date date, String shiftType, String amountAsString) {
        return this.getDateTimeShift(date, shiftType, amountAsString).substring(0, 10);
    }

    /**
     * Возвращает год в виде строки, сдвинутый на заданный интервал в прошлое или будущее.
     *
     * @param date           оригинальная дата
     * @param shiftType      тип временного сдвига
     * @param amountAsString интервал (положительный или отрицательный), на который следует осуществить сдвиг
     * @return год в виде строки, сдвинутый на заданный интервал в прошлое или будущее.
     */
    public String getYearShift(Date date, String shiftType, String amountAsString) {
        return this.getDateTimeShift(date, shiftType, amountAsString).substring(6, 10);
    }

    /**
     * Возвращает дату в виде строки, сдвинутую на заданный интервал в прошлое или будущее ( без разделителей ).
     *
     * @param date           оригинальная дата
     * @param shiftType      тип временного сдвига
     * @param amountAsString интервал (положительный или отрицательный), на который следует осуществить сдвиг
     * @return дата без разделителей в виде строки, сдвинутая на заданный интервал в прошлое или будущее
     */
    public String getDateShiftWithoutDelimiters(Date date, String shiftType, String amountAsString) {
        return this.getDateTimeShift(date, shiftType, amountAsString).substring(0, 10).replace(".", "");
    }

    /**
     * Возвращает дату и время в виде строки.
     *
     * @return дата и время в виде строки
     */
    public String getCurrentDateTime(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(new Date());
    }

    /**
     * Возвращает дату и время вчерашнего дня в виде строки.
     *
     * @return дата и время вчерашнего дня в виде строки
     */
    public String getYesterdayDateTime(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * Инициализирует журнал времени выполнения шагов и методов теста.
     *
     * @return результат инициализации журнала
     */
    public boolean initTimeProfileLog() {
        LOGNAME = "TARGET\\SITE\\CUCUMBER-PRETTY\\" +
                ConfigContainer.getInstance().getParameter("TestType") + " "
                + this.getCurrentDateTime("ddMMyyyyHHmmSS") + ".csv";
        if (ConfigContainer.getInstance().getConfigParameter("WriteSeparateDurationLog")
                .equals("off")) return true;
        String path = new File(LOGNAME).getAbsolutePath(); // Полный путь к файлу журнала
        File file = new File(path);
        if (file.exists() && file.isFile())                // Если файл уже существует - удаляем его
        {
            if (!file.delete()) return false;              // Если удалить файл не получилось - прерываем тест
        }
        try                                                // Пытаемся создать новый файл журнала
        {
            OutputStreamWriter outputStreamWriter =
                    new OutputStreamWriter(new FileOutputStream(path), "WINDOWS-1251");
            outputStreamWriter.append("\"Имя шага или метода\";\"Время выполнения, сек.\"\n");
            outputStreamWriter.flush();
            outputStreamWriter.close();
            TimeUnit.MILLISECONDS.sleep(500);       // Нужно для стабильной работы
        } catch (Exception ex)                               // Если создать новый файл не удалось - возвращаем ошибку
        {
            System.out.println(ex.toString());
            return false;
        }
        return true;                                       // Журнал был создан успешно
    }

    /**
     * Делает запись в журнал времени выполнения шагов и методов теста.
     *
     * @param name имя шага или метода
     * @param time время выполнения в секундах
     * @return результат записи в журнал
     */
    private boolean logTime(String name, Long time) {
        String path = new File(LOGNAME).getAbsolutePath();   // Полный путь к файлу журнала
        String line = "\"" + name + "\";" + time.toString() + "\n";
        File file = new File(path);
        if (!file.exists() || !file.isFile()) return false;  // Если файл не существует - прерываем тест
        try                                                  // Пытаемся сделать запись в журнал
        {
            FileOutputStream fileOutputStream = new FileOutputStream(path, true);
            Writer writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream, "WINDOWS-1251"));
            writer.write(line);
            writer.flush();
            writer.close();
            TimeUnit.MILLISECONDS.sleep(500);         // Нужно для стабильной работы
        } catch (Exception ex)                                 // Если сделать запись не удалось - возвращаем ошибку
        {
            System.out.println(ex.toString());
            return false;
        }
        return true;                                         // Запись в журнал была сделана успешно
    }

    /**
     * Преобразует дату в форматированный текстовый вид с секундами [2018-01-15T10:34:40].
     *
     * @param date дата
     * @return дата в форматированном текстовом виде
     */
    public String dateToResponseFormatWithSeconds(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return dateFormat.format(calendar.getTime()).replace(" ", "T");
    }

    /**
     * Преобразует дату в форматированный текстовый вид с секундами [2018-01-15T10:34:40].
     *
     * @param date   дата
     * @param amount количество месяцев, которое необходимо добавить к текущей дате
     * @return дата в форматированном текстовом виде
     */
    public String dateToResponseFormatWithSeconds(Date date, int amount) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+3"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, amount);
        return dateFormat.format(calendar.getTime()).replace(" ", "T");
    }

    /**
     * Преобразует дату в форматированный текстовый вид с секундами [2018-01-15T10:34:40].
     *
     * @param date   дата
     * @param type   единица измерения для инкремента ( например Calendar.MONTH )
     * @param amount шаг инкремента, который необходимо добавить к текущей дате
     * @return дата в форматированном текстовом виде
     */
    public String dateToResponseFormatWithSeconds(Date date, int type, int amount) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+3"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(type, amount);
        return dateFormat.format(calendar.getTime()).replace(" ", "T");
    }

    /**
     * Преобразует дату в форматированный текстовый вид [2018-02-10].
     *
     * @param date дата
     * @return дата в форматированном текстовом виде
     */
    public String dateOnlyToResponseFormat(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+3"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * Преобразует дату в форматированный текстовый вид [28.02.2019] для UI.
     *
     * @param date дата
     * @return дата в форматированном текстовом виде
     */
    public String dateOnlyToResponseFormat4Ui(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+3"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * Преобразует дату в форматированный текстовый вид [2018-02-10].
     *
     * @param date   дата
     * @param amount количество месяцев, которое необходимо добавить к текущей дате
     * @return дата в форматированном текстовом виде
     */
    public String dateOnlyToResponseFormat(Date date, int amount) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+3"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, amount);
        return dateFormat.format(calendar.getTime());
    }
}
