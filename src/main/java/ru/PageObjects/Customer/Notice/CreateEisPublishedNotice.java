package ru.PageObjects.Customer.Notice;

import Helpers.ConfigContainer;
import Helpers.Timer;
import org.junit.Assert;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Этот класс содержит общие методы для связывания закупки в тестируемом приложении с номером от ЕИС через .xml-ответ.
 * Created by Vladimir V. Klochkov on 02.08.2018.
 * Updated by Vladimir V. Klochkov on 10.03.2021.
 */
abstract class CreateEisPublishedNotice {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    protected final Timer timer = new Timer();                              // Класс для работы со временными интервалами
    protected final ConfigContainer config = ConfigContainer.getInstance(); // Экземпляр ConfigContainer
    Date publishNoticeDate;                                                 // Дата публикации извещения о закупке

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    /**
     * Преобразует дату и время из параметра теста в строковом формате [03.08.2018 11:34:26] в формат даты и времени.
     *
     * @param parameter наименование параметра теста, который содержит дату и время в строковом формате
     * @return дата и время
     */
    Date getPublishNoticeDate(String parameter) throws Exception {
        String dateInString = config.getParameter(parameter);
        Date result = new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(dateInString);
        System.out.printf("[ИНФОРМАЦИЯ]: получена дата [%tc].%n", result);
        return result;
    }

    /**
     * Сохраняет значение поля [Дата публикации извещения о закупке].
     *
     * @param publishNoticeDate
     */
    void setPublishNoticeDate(Date publishNoticeDate) {
        this.publishNoticeDate = publishNoticeDate;
    }

    /**
     * Получает шаблон .xml-ответа от ЕИС из файла ресурсов проекта.
     *
     * @param configParameter имя параметра конфигурационного файла с путём к файлу шаблона .xml-ответа от ЕИС
     * @return шаблон .xml-ответа от ЕИС
     */
    public String getModel(String configParameter) throws IOException {
        // Получаем шаблон .xml-ответа как файл из ресурсов проекта в виде строки
        String pathToXmlModel = config.getConfigParameter(configParameter);
        String model = new String(Files.readAllBytes(Paths.get(pathToXmlModel)));
        Assert.assertTrue("[ОШИБКА]: из файла было считано 0 байт", model.length() > 0);
        return model;
    }

    /**
     * Записывает в файл по указанному пути .xml-ответ от ЕИС.
     *
     * @param model          .xml-ответа от ЕИС
     * @param fileNamePrefix префикс имени файла (тип процедуры), например [CompetitiveProcurementTenderNotice]
     * @return результат записи в файл .xml-ответа от ЕИС
     */
    public boolean putModel(String model, String fileNamePrefix) {
        // Получаем полный путь к выходному файлу с .xml-ответом от ЕИС
        String fileDateTime = timer.getCurrentDateTime("ddMMyyyyHHmmSS");
        String fileName = String.format("%s-%s.xml", fileNamePrefix, fileDateTime);
        config.setParameter(fileNamePrefix, fileName);
        String modelFile = String.format("target\\site\\cucumber-pretty\\%s", fileName);
        String fullPath = new File(modelFile).getAbsolutePath();
        System.out.printf("[ИНФОРМАЦИЯ]: полный путь к файлу с .xml-ответом от ЕИС [%s].%n", fullPath);

        // Проверяем существование файла с таким именем
        File file = new File(fullPath);
        if (file.exists() && file.isFile() && !file.delete()) return false;

        // Пытаемся создать новый файл с .xml-ответом от ЕИС
        try {
            System.out.printf("[ИНФОРМАЦИЯ]: формируем файл с .xml-ответом от ЕИС [%s].%n", fullPath);
            OutputStreamWriter outputStreamWriter =
                    new OutputStreamWriter(new FileOutputStream(fullPath), StandardCharsets.UTF_8);
            outputStreamWriter.append(model);
            outputStreamWriter.flush();
            outputStreamWriter.close();
            long timeout = 1;
            System.out.printf("[ИНФОРМАЦИЯ]: сформирован файл с .xml-ответом от ЕИС [%s].%n", fullPath);
            System.out.printf("[ИНФОРМАЦИЯ]: ожидаем %d мин. для проверки файла.%n", timeout);
            TimeUnit.MINUTES.sleep(timeout);
            file = new File(fullPath);
            if (file.exists() && file.isFile() && !file.delete()) return false;
            return true;
        } catch (Exception e) {
            // Если создать новый файл не удалось - возвращаем ошибку
            System.out.println("[ОШИБКА]: не удалось записать на диск новый файл с .xml-ответом от ЕИС.");
            System.out.println(e.toString());
            return false;
        }
    }

    /**
     * Производит замену указанного поля в шаблоне .xml-ответа от ЕИС на требуемое значение.
     *
     * @param model шаблон .xml-ответа от ЕИС
     * @param field поле
     * @param value значение
     * @return шаблон .xml-ответа от ЕИС, в котором указанное поле заменено требуемым значением
     */
    private String setFieldInModel(String model, String field, String value) {
        String fieldNotFound = "В шаблоне .xml-ответа не найдено поле для замены: %s.";
        String valueNotFound = "Замена поля: %s на требуемое значение не была произведена.";
        String infoMessage = "Произведена замена поля: %s на значение: [%s].";

        Assert.assertTrue("[ИНФОРМАЦИЯ]: " + String.format(fieldNotFound, field), model.contains(field));
        model = model.replace(field, value);
        Assert.assertTrue("[ИНФОРМАЦИЯ]: " + String.format(valueNotFound, field), model.contains(value));
        System.out.println("[ИНФОРМАЦИЯ]: " + String.format(infoMessage, field, value));

        return model;
    }

    /**
     * Производит замену указанного поля в шаблоне .xml-ответа от ЕИС на требуемое значение с сохранением значения в
     * параметрах текущего автоматического теста.
     *
     * @param model шаблон .xml-ответа от ЕИС
     * @param field поле
     * @param value значение
     * @param key   ключ для поиска в параметрах теста
     * @return шаблон .xml-ответа от ЕИС, в котором указанное поле заменено требуемым значением
     */
    String setFieldInModel(String model, String field, String value, String key) {
        config.setParameter(key, value);
        model = this.setFieldInModel(model, field, value);
        return model;
    }
}
