@smoke @runTest @plans_download_from_the_EIS
Feature: Plans Download From The EIS
  #=====================================================================================================================
  # Планы - загрузка с ЕИС
  # ( https://ve-lab.visualstudio.com/kontur.market/_workitems/edit/344477 )
  # В системе создан следующий пользователь:
  # Заказчик - "Заказчик 1 для работы с ЕИС" ( особый сертификат, он такой у нас один на всех )
  #=====================================================================================================================
  Scenario: Планы - загрузка с ЕИС
    Given Параметру 'Тип теста' установлено значение "Plans Download From The EIS"
    And Все требуемые для теста сертификаты присутствуют на компьютере
    #*******************************************************************************************************************
    #
    #                 Заказчик создает новый [План закупки товаров (работ, услуг)]
    #
    #*******************************************************************************************************************
    When "Заказчик 1 для работы с ЕИС" заходит в личный кабинет
    #
    #                 Мои закупки
    #
    And 'Заказчик' переходит на страницу "ПЛАНЫ ЗАКУПОК" с именем URL для проверки "Планы закупок"
    #
    #                 Планы закупок
    #
    And 'Заказчик' нажимает на кнопку "Добавить план закупки" на странице 'Планы закупок'
    #
    #                 Создание плана закупки
    #
    And 'Заказчик' проверяет, что открыта страница "Создание плана закупки"
    #                 Создание плана закупки - Общие сведения о плане закупки
    And 'Заказчик' переходит к просмотру раздела "Общие сведения о плане закупки" на странице 'Создание плана закупки'
    And 'Заказчик' заполняет поле 'Наименование' сгенерированным именем на странице 'Создание плана закупки'
    And 'Заказчик' проверяет, что поле "Заказчик" содержит не пустое значение на странице 'Создание плана закупки'
    And 'Заказчик' проверяет, что поле "Вид" содержит значение "План закупки товаров (работ, услуг)" на странице 'Создание плана закупки'
    And 'Заказчик' заполняет дату "Период C" в "MONTHS" со смещением "+0" от текущей на странице 'Создание плана закупки'
    And 'Заказчик' заполняет дату "Период По" в "MONTHS" со смещением "+1" от текущей на странице 'Создание плана закупки'
    And 'Заказчик' заполняет дату "Дата утверждения" в "MONTHS" со смещением "+0" от текущей на странице 'Создание плана закупки'
    And 'Заказчик' заполняет год "Отчетный год" в "YEARS" со смещением "+0" от текущего на странице 'Создание плана закупки'
    #                 Создание плана закупки - Подвал страницы
    And 'Заказчик' переходит к просмотру раздела "Подвал страницы" на странице 'Создание плана закупки'
    And 'Заказчик' нажимает на кнопку "Продолжить" на странице 'Создание плана закупки'
    And 'Заказчик' нажимает на кнопку "Продолжить (формирование черновика)" в окне диалога 'Сохранено'
    #
    #                 Редактирование плана закупки
    #
    And 'Заказчик' проверяет, что открыта страница "Редактирование плана закупки"
    #                 Редактирование плана закупки - Общие сведения о плане закупки
    And 'Заказчик' переходит к просмотру раздела "Общие сведения о плане закупки" на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что "Наименование" содержит параметр теста "TradePlanName" на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что поле "Заказчик" содержит не пустое значение на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что поле "Вид" содержит значение "План закупки товаров (работ, услуг)" на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет дату в поле "Период C" в "MONTHS" со смещением "+0" от текущей на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет дату в поле "Период По" в "MONTHS" со смещением "+1" от текущей на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет дату в поле "Дата утверждения" в "MONTHS" со смещением "+0" от текущей на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет год в поле "Отчетный год" в "YEARS" со смещением "+0" от текущего на странице 'Редактирование плана закупки'
    #                 Редактирование плана закупки - Импорт позиций
    And 'Заказчик' переходит к просмотру раздела "Импорт позиций" на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что переключатель "Загрузка из Excel-файла" отмечен на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что переключатель "Загрузка с ЕИС" не отмечен на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что кнопка "Загрузить файл" отображается и доступна на странице 'Редактирование плана закупки'
    And 'Заказчик' устанавливает переключатель "Загрузка" в положение "Загрузка с ЕИС" на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что переключатель "Загрузка из Excel-файла" не отмечен на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что переключатель "Загрузка с ЕИС" отмечен на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что кнопка "Импортировать" отображается и доступна на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что поле "Введите URL плана закупок на ЕИС" доступно для редактирования на странице 'Редактирование плана закупки'
    And 'Заказчик' заполняет поле "Введите URL плана закупок на ЕИС" значением "TradePlanToImportPositionsUrl" из файла конфигурации на странице 'Редактирование плана закупки'
    And 'Заказчик' нажимает на кнопку "Импортировать" на странице 'Редактирование плана закупки'
    And 'Заказчик' нажимает на кнопку 'OK' в диалоговом окне 'Информация' с текстом "План закупок был импортирован"
    #
    #                 Просмотр плана закупок
    #
    And 'Заказчик' проверяет, что открыта страница "Просмотр плана закупок"
    And 'Заказчик' проверяет, что таблица 'Позиции плана' содержит 1 строк на странице 'Просмотр плана закупок'
    And 'Заказчик' выбирает первую позицию в таблице 'Позиции плана' на странице 'Просмотр плана закупок'
    And 'Заказчик' проверяет, что кнопка "Создать извещение верхняя кнопка" отображается и доступна на странице 'Просмотр плана закупок'
    And 'Пользователь' покидает систему