@smoke @runTest @sending_manual_notification_from_customers_personal_cabinet
Feature: Sending manual notification from the Customer's personal cabinet
  #=====================================================================================================================
  # Отправка ручных уведомлений из ЛК Заказчика
  # ( https://ve-lab.visualstudio.com/kontur.market/_workitems/edit/318487 )
  # В системе созданы следующие пользователи:
  # Заказчик 1 - "Заказчик Юридическое лицо 5" ( выполняет ручную отправку уведомления из личного кабинета Заказчику 2 )
  # Заказчик 2 - "Заказчик Юридическое лицо 6" ( проверяет наличие уведомления, отправленного вручную Заказчиком 1 )
  #=====================================================================================================================
  Scenario: Отправка ручных уведомлений из ЛК Заказчика
    Given Параметру 'Тип теста' установлено значение "Sending Manual Notification From The Customer's Personal Cabinet"
    And Все требуемые для теста сертификаты присутствуют на компьютере
    #*******************************************************************************************************************
    #
    #                 Заказчик 2 сохраняет в параметрах теста количество непрочитанных входящих уведомлений
    #
    #*******************************************************************************************************************
    When "Заказчик Юридическое лицо 6" заходит в личный кабинет
    And 'Заказчик' сохраняет в параметрах теста количество непрочитанных входящих уведомлений
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Заказчик 1 выполняет ручную отправку уведомления из личного кабинета Заказчику 2
    #
    #*******************************************************************************************************************
    When "Заказчик Юридическое лицо 5" заходит в личный кабинет
    And 'Заказчик' выполняет ручную отправку уведомления из личного кабинета для организации с ИНН "1859759712"
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Заказчик 2 проверяет наличие уведомления, отправленного вручную Заказчиком 1
    #
    #*******************************************************************************************************************
    When "Заказчик Юридическое лицо 6" заходит в личный кабинет
    And 'Заказчик' проверяет, что количество непрочитанных входящих уведомлений увеличилось
    And 'Заказчик' проверяет наличие входящего уведомления по его номеру, сохраненному в параметрах теста
    And 'Пользователь' покидает систему
