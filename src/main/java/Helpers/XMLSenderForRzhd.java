package Helpers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.javatuples.Triplet;
import org.junit.Assert;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Класс для отправки файлов XML по протоколу HTTP ( используя GET и POST запросы в Apache HttpClient ).
 * Created by Vladimir V. Klochkov on 19.02.2019.
 * Updated by Vladimir V. Klochkov on 10.03.2021.
 */
public class XMLSenderForRzhd {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final String postURL;  // URL для запроса POST
    private final String getURL;   // URL для запроса GET ( без GUID )
    private final String login;    // имя пользователя
    private final String password; // пароль пользователя
    private final String document; // полное имя .xml-файла
    private String guid;           // GUID, который будет получен из ответа на POST запрос ( нужен для GET-запроса )

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     ******************************************************************************************************************/
    /**
     * Инициализация данных, необходимых для отправки файлов XML по протоколу HTTP.
     *
     * @param postURL  URL для запроса POST
     * @param getURL   URL для запроса GET ( без GUID )
     * @param login    имя пользователя
     * @param password пароль пользователя
     * @param document полное имя .xml-файла
     */
    public XMLSenderForRzhd(String postURL, String getURL, String login, String password, String document) {
        //--------------------------------------------------------------------------------------------------------------
        this.postURL = postURL;
        this.getURL = getURL;
        this.login = login;
        this.password = password;
        this.document = document;
        this.guid = null;
        //--------------------------------------------------------------------------------------------------------------
        System.out.printf("[ИНФОРМАЦИЯ]: URL для запроса POST:             [%s].%n", this.postURL);
        System.out.printf("[ИНФОРМАЦИЯ]: URL для запроса GET ( без GUID ): [%s].%n", this.getURL);
        System.out.printf("[ИНФОРМАЦИЯ]: имя пользователя:                 [%s].%n", this.login);
        System.out.printf("[ИНФОРМАЦИЯ]: пароль пользователя:              [%s].%n", this.password);
        System.out.printf("[ИНФОРМАЦИЯ]: полное имя .xml-файла:            [%s].%n", this.document);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    /**
     * Отправляет .xml-файл от РЖД на площадку по протоколу HTTP ( используя POST запрос в Apache HttpClient ).
     *
     * @return ссылку на экземпляр этого класса
     */
    public XMLSenderForRzhd postRequest() throws Exception {
        // Создаем пустой POST-запрос
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(postURL);

        // Формируем тело POST-запроса
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("login", login);
        builder.addTextBody("password", password);
        builder.addBinaryBody("document", new File(document), ContentType.APPLICATION_XML, document);

        // Помещаем тело POST-запроса в сам POST-запрос
        HttpEntity multipart = builder.build();
        httpPost.setEntity(multipart);

        // Выполняем POST-запрос
        CloseableHttpResponse response = client.execute(httpPost);

        // Анализируем результат выполнения POST-запроса
        this.verifyStatusAndReasonPhrase(response);

        // Разбираем результат выполнения POST-запроса и сохраняем полученный GUID для GET-запроса
        Triplet<Integer, String, String> codeMessageGUID = this.getCodeMessageAndGuid(response);
        Assert.assertEquals("[ОШИБКА]: код ответа площадки некорректен",
                (Integer) 1, codeMessageGUID.getValue0());
        Assert.assertEquals("[ОШИБКА]: сообщение площадки некорректно",
                "Пакет взят в обработку", codeMessageGUID.getValue1());
        Assert.assertFalse("[ОШИБКА]: поле GUID не заполнено", codeMessageGUID.getValue2().isEmpty());
        System.out.println("[ИНФОРМАЦИЯ]: ----> POST-запрос был успешно принят площадкой.");
        System.out.println(StringUtils.repeat(":", 150));
        this.guid = codeMessageGUID.getValue2();

        // Закрываем POST-запрос
        client.close();

        return this;
    }

    /**
     * Получает статус обработки на площадке .xml-файла от РЖД по протоколу HTTP
     * ( используя GET запрос в Apache HttpClient ).
     */
    public void getResponce() throws Exception {
        // Предварительные проверки
        Assert.assertNotNull("[ОШИБКА]: поле GUID содержит null", this.guid);
        Assert.assertFalse("[ОШИБКА]: поле GUID не заполнено", this.guid.isEmpty());
        String url = getURL + guid;
        System.out.printf("[ИНФОРМАЦИЯ]: URL для запроса GET ( с GUID ): [%s].%n", url);

        // Создаем пустой GET-запрос
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);

        // Цикл ожидания окончания обработки POST-запроса
        int i = 1;                                                                    // счетчик попыток
        int nTries = 100;                                                             // количество попыток
        Triplet<Integer, String, String> codeMessageGUID = new Triplet<>(-1, "", ""); // код ответа, сообщение и GUID

        do {
            TimeUnit.SECONDS.sleep(15);                           // Выдерживаем паузу перед следующим запросом
            System.out.printf("[ИНФОРМАЦИЯ]: GET-запрос № [%d].%n", i); // Считаем GET-запросы
            CloseableHttpResponse response = client.execute(httpGet);    // Выполняем GET-запрос
            this.verifyStatusAndReasonPhrase(response);                  // Анализируем результат выполнения GET-запроса
            codeMessageGUID = this.getCodeMessageAndGuid(response);      // Разбираем результат выполнения GET-запроса
            if (!codeMessageGUID.getValue2().isEmpty())                  // Если GUID не пустой, проверяем его
                Assert.assertEquals("[ОШИБКА]: GET-запрос вернул некорректный GUID",
                        this.guid, codeMessageGUID.getValue2());
            i++;                                                         // Увеличиваем счетчик GET-запросов
        } while (!(codeMessageGUID.getValue0() == 0) && i <= nTries);

        // Проверяем корректное окончание обработки POST-запроса
        Assert.assertEquals("[ОШИБКА]: код ответа площадки некорректен",
                (Integer) 0, codeMessageGUID.getValue0());
        Assert.assertEquals("[ОШИБКА]: сообщение площадки некорректно",
                "Ok", codeMessageGUID.getValue1());
        System.out.println("[ИНФОРМАЦИЯ]: <---- POST-запрос был успешно обработан площадкой.");
        System.out.println(StringUtils.repeat(":", 150));

        // Закрываем GET-запрос
        client.close();
    }

    /**
     * Анализирует результат выполнения запроса на уровне протокола.
     *
     * @param response результат выполнения запроса
     */
    private void verifyStatusAndReasonPhrase(CloseableHttpResponse response) {
        int statusCode = response.getStatusLine().getStatusCode();
        String reasonPhrase = response.getStatusLine().getReasonPhrase();

        System.out.printf("[ИНФОРМАЦИЯ]: Код статуса       запроса: [%d].%n", statusCode);
        System.out.printf("[ИНФОРМАЦИЯ]: Сообщение статуса запроса: [%s].%n", reasonPhrase);

        Assert.assertEquals("[ОШИБКА]: код статуса запроса некорректен", 200, statusCode);
        Assert.assertEquals("[ОШИБКА]: сообщение статуса запроса некорректно", "OK", reasonPhrase);
    }

    /**
     * Разбирает результат выполнения запроса на уровне площадки и возвращает код ответа, сообщение и GUID.
     *
     * @param response результат выполнения запроса
     * @return код ответа, сообщение и GUID
     */
    private Triplet<Integer, String, String> getCodeMessageAndGuid(CloseableHttpResponse response) throws Exception {
        String responseString = new BasicResponseHandler().handleResponse(response);
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(responseString);

        int code = jsonObject.get("<Code>k__BackingField").getAsInt();
        String msg = jsonObject.get("<Message>k__BackingField").getAsString();
        String guid = jsonObject.get("<ResultMessage>k__BackingField").getAsString();

        System.out.printf("[ИНФОРМАЦИЯ]: Код ответа площадки: [%d].%n", code);
        System.out.printf("[ИНФОРМАЦИЯ]: Сообщение площадки:  [%s].%n", msg);
        System.out.printf("[ИНФОРМАЦИЯ]: GUID площадки:       [%s].%n", guid);

        return new Triplet<>(code, msg, guid);
    }
}
