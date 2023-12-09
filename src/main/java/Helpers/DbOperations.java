package Helpers;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import java.sql.*;
import java.util.List;

/**
 * Класс для работы с базами данных тестовых стендов (SQL-запросы).
 * Created by Vladimir V. Klochkov on 28.11.2018.
 * Updated by Vladimir V. Klochkov on 10.03.2021.
 */
public class DbOperations {
    /*******************************************************************************************************************
     * Константы класса.
     ******************************************************************************************************************/
    private static final String CONNECTION_STRING = "jdbc:sqlserver://%s;database=%s;user=%s;password=%s;";

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final ConfigContainer config = ConfigContainer.getInstance(); // экземпляр ConfigContainer
    private Connection connection;                                     // Объект для установки соединения с базой данных

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    /**
     * Устанавливает соединение с сервером базы данных.
     *
     * @param sqlDatabaseName имя базы данных
     * @return ссылка на экземпляр этого класса
     */
    public DbOperations connectToDatabase(String sqlDatabaseName) {
        if (connection == null) {
            String sqlServerAddress = config.getConfigParameter("SqlServerAddress");
            String sqlUserLogin = config.getConfigParameter("SqlUserLogin");
            String sqlUserPassword = config.getConfigParameter("SqlUserPassword");
            String connectionString =
                    String.format(CONNECTION_STRING, sqlServerAddress, sqlDatabaseName, sqlUserLogin, sqlUserPassword);

            System.out.printf("[ИНФОРМАЦИЯ]: сформирована строка [%s] для соединения с сервером.%n", connectionString);

            try {
                System.out.println("[ИНФОРМАЦИЯ]: попытка установить соединение с сервером.");
                connection = DriverManager.getConnection(connectionString);
                System.out.println("[ИНФОРМАЦИЯ]: соединение с сервером установлено успешно.");
            } catch (SQLException sqlException) {
                System.err.println("[ОШИБКА]: не удалось установить соединение с сервером.");
                sqlException.printStackTrace();
            }
        }

        return this;
    }

    /**
     * Выполняет SQL-запрос SELECT к серверу базы данных.
     *
     * @param sqlSelect             текст SQL-запроса SELECT
     * @param expectedResultsetSize ожидаемый размер возвращаемого набора данных или 0 если размер неизвестен
     * @param fields                список названий полей, значения которых требуется вывести в консоль
     * @return ссылка на экземпляр этого класса
     */
    public DbOperations executeSelectQuery(String sqlSelect, int expectedResultsetSize, List<String> fields) {
        try {
            Statement statement = connection.createStatement();
            System.out.println("[ИНФОРМАЦИЯ]: попытка выполнить SQL-запрос SELECT к серверу:");
            System.out.println(StringUtils.repeat(">", 150));
            System.out.println(sqlSelect);
            System.out.println(StringUtils.repeat("<", 150));
            ResultSet resultSet = statement.executeQuery(sqlSelect);

            // В любом случае возвращаемый набор данных не должен быть NULL
            Assert.assertNotNull("[ОШИБКА]: сервер вернул NULL вместо набора данных", resultSet);

            // В любом случае возвращаемый набор данных не должен быть пустым
            int actualResultsetSize = 0;
            while (resultSet.next()) {
                for (String field : fields)
                    System.out.printf("[ИНФОРМАЦИЯ]: поле [%s] имеет значение [%s].%n", field, resultSet.getString(field));
                actualResultsetSize++;
            }
            Assert.assertNotEquals("[ОШИБКА]: сервер вернул пустой набор данных",
                    0, actualResultsetSize);

            // Если требуется проверить размер возвращаемого набора данных
            if (expectedResultsetSize != 0)
                Assert.assertEquals("[ОШИБКА]: сервер вернул набор данных некорректного размера",
                        expectedResultsetSize, actualResultsetSize);

            System.out.printf("[ИНФОРМАЦИЯ]: SQL-запрос SELECT к серверу выполнен успешно, возвращено [%d] записей.%n",
                    actualResultsetSize);
            resultSet.close();
            statement.close();
        } catch (SQLException sqlException) {
            System.err.println("[ОШИБКА]: не удалось выполнить SQL-запрос SELECT к серверу.");
            sqlException.printStackTrace();
        }

        return this;
    }

    /**
     * Выполняет SQL-запрос UPDATE к серверу базы данных.
     *
     * @param sqlUpdate текст SQL-запроса UPDATE
     * @return ссылка на экземпляр этого класса
     */
    public DbOperations executeUpdateQuery(String sqlUpdate) {
        try {
            Statement statement = connection.createStatement();
            System.out.println("[ИНФОРМАЦИЯ]: попытка выполнить SQL-запрос UPDATE к серверу:");
            System.out.println(StringUtils.repeat(">", 150));
            System.out.println(sqlUpdate);
            System.out.println(StringUtils.repeat("<", 150));
            boolean result = statement.execute(sqlUpdate);
            Assert.assertFalse("[ОШИБКА]: выполнение UPDATE-запросов не возвращает наборы данных", result);
            System.out.println("[ИНФОРМАЦИЯ]: SQL-запрос UPDATE к серверу выполнен успешно.");
            statement.close();
        } catch (SQLException sqlException) {
            System.err.println("[ОШИБКА]: не удалось выполнить SQL-запрос UPDATE к серверу.");
            sqlException.printStackTrace();
        }

        return this;
    }

    /**
     * Выполняет SQL-запрос DELETE к серверу базы данных.
     *
     * @param sqlDelete текст SQL-запроса DELETE
     * @return ссылка на экземпляр этого класса
     */
    public DbOperations executeDeleteQuery(String sqlDelete) {
        try {
            Statement statement = connection.createStatement();
            System.out.println("[ИНФОРМАЦИЯ]: попытка выполнить SQL-запрос DELETE к серверу:");
            System.out.println(StringUtils.repeat(">", 150));
            System.out.println(sqlDelete);
            System.out.println(StringUtils.repeat("<", 150));
            boolean result = statement.execute(sqlDelete);
            Assert.assertFalse("[ОШИБКА]: выполнение DELETE-запросов не возвращает наборы данных", result);
            System.out.println("[ИНФОРМАЦИЯ]: SQL-запрос DELETE к серверу выполнен успешно.");
            statement.close();
        } catch (SQLException sqlException) {
            System.err.println("[ОШИБКА]: не удалось выполнить SQL-запрос DELETE к серверу.");
            sqlException.printStackTrace();
        }

        return this;
    }

    /**
     * Выполняет SQL-запросы SELECT и DELETE ( только если есть что удалять ) к серверу базы данных.
     *
     * @param sqlSelect текст SQL-запроса SELECT
     * @param sqlDelete текст SQL-запроса DELETE
     * @param fields    список названий полей, значения которых требуется вывести в консоль
     * @return ссылка на экземпляр этого класса
     */
    public DbOperations executeDeleteQueryIfThereIsAnythingToDelete(
            String sqlSelect, String sqlDelete, List<String> fields) {
        int actualResultsetSize = 0;

        try {
            Statement statement = connection.createStatement();
            System.out.println("[ИНФОРМАЦИЯ]: попытка выполнить SQL-запрос SELECT к серверу:");
            System.out.println(StringUtils.repeat(">", 150));
            System.out.println(sqlSelect);
            System.out.println(StringUtils.repeat("<", 150));
            ResultSet resultSet = statement.executeQuery(sqlSelect);

            // В любом случае возвращаемый набор данных не должен быть NULL
            Assert.assertNotNull("[ОШИБКА]: сервер вернул NULL вместо набора данных", resultSet);

            // Подсчитываем количество записей в возвращаемом наборе данных
            while (resultSet.next()) {
                for (String field : fields)
                    System.out.printf("[ИНФОРМАЦИЯ]: поле [%s] имеет значение [%s].%n", field, resultSet.getString(field));
                actualResultsetSize++;
            }

            // Выводим в консоль статистику выполнения SQL-запроса SELECT
            System.out.printf("[ИНФОРМАЦИЯ]: SQL-запрос SELECT к серверу выполнен успешно, возвращено [%d] записей.%n",
                    actualResultsetSize);
            resultSet.close();
            statement.close();
        } catch (SQLException sqlException) {
            System.err.println("[ОШИБКА]: не удалось выполнить SQL-запрос SELECT к серверу.");
            sqlException.printStackTrace();
        }

        // Если есть записи для удаления, то удаляем их все
        if (actualResultsetSize > 0) {
            System.out.printf("[ИНФОРМАЦИЯ]: SQL-запрос SELECT к серверу возвратил [%d] записей для удаления.%n",
                    actualResultsetSize);
            this.executeDeleteQuery(sqlDelete);
        } else {
            System.out.println("[ИНФОРМАЦИЯ]: база данных не содержит искомых записей для удаления.");
        }

        return this;
    }

    /**
     * Закрывает соединение с сервером базы данных.
     */
    public void closeConnectionToDatabase() {
        try {
            System.out.println("[ИНФОРМАЦИЯ]: попытка закрыть соединение с сервером.");
            connection.close();
            System.out.println("[ИНФОРМАЦИЯ]: соединение с сервером закрыто успешно.");
        } catch (SQLException sqlException) {
            System.err.println("[ОШИБКА]: не удалось закрыть соединение с сервером.");
            sqlException.printStackTrace();
        }
    }
}
