@smoke @runTest @request_4_clarification_of_application_with_sending_2_eis
Feature: Request For Clarification Of The Application With Sending To The EIS
  #=====================================================================================================================
  # Запрос на разъяснение заявки с отправкой в ЕИС ( конкурентная закупка в соответствии с нормами 223-ФЗ )
  # ( https://ve-lab.visualstudio.com/kontur.market/_workitems/edit/331446 )
  # В системе созданы следующие пользователи:
  # Заказчик                   - "Заказчик Юридическое лицо 1"
  # Поставщик 1                - "Поставщик Юридическое лицо 1"
  # Поставщик 2                - "Поставщик Юридическое лицо 2"
  # Администратор ( Оператор ) - "Администратор"
  #=====================================================================================================================
  Scenario: Запрос на разъяснение заявки с отправкой в ЕИС ( конкурентная закупка в соответствии с нормами 223-ФЗ )
    Given Параметру 'Тип теста' установлено значение "Request For Clarification Of The Application With Sending To The EIS"
    And Все требуемые для теста сертификаты присутствуют на компьютере
    #*******************************************************************************************************************
    #
    #                 Заказчик проверяет "Настройки протоколов" в разделе "ОБЩИЕ НАСТРОЙКИ"
    #
    #*******************************************************************************************************************
    When "Заказчик" заходит в личный кабинет
    And 'Заказчик' переходит на страницу "МОЯ ОРГАНИЗАЦИЯ/ОБЩИЕ НАСТРОЙКИ"
    And 'Заказчик' открывает "Раздел Настройки протоколов" на странице 'Общие настройки'
    And 'Заказчик' проверяет что флаг "Флаг Отображать ценовые предложения в протоколе открытия доступа" на странице 'Общие настройки' "включен"
    #*******************************************************************************************************************
    #
    #                 Заказчик публикует извещение о проведении конкурса
    #                 ( конкурентная закупка в соответствии с нормами 223-ФЗ )
    #
    #*******************************************************************************************************************
    And 'Заказчик' переходит на страницу "ЗАКУПКИ/Заказчикам"
    And 'Заказчик А' открывает страницу создания извещения для конкурентной закупки "Конкурс"
    And 'Заказчик А' заполняет сведения о полном электронном конкурсе - конкурентная закупка:
    #
    #                 Общие сведения о закупке
    #
    And 'Заказчик А' переходит к разделу "Общие сведения о закупке":
    And 'Заказчик А' проверяет, что поле "Способ закупки" содержит значение "Конкурс"
    And 'Заказчик А' проверяет, что поле "Тип закупки" содержит значение "Закупка в соответствии с нормами 223-ФЗ (Конкурентная)"
    And 'Заказчик А' заполняет поле 'Наименование закупки' сгенерированным именем для типа закупки "Конкурс - Конкурентн."
    And 'Заказчик А' проверяет, что флажок "Многолотовая закупка" "не установлен"
    And 'Заказчик А' устанавливает флажок "Публикация протокола открытия доступа"
    And 'Заказчик А' устанавливает флажок "Публикация итогового протокола"
    And 'Заказчик А' проверяет, что флажок "Закупка с проведением предварительного этапа" "не установлен"
    And 'Заказчик А' устанавливает флажок "Закупка проводится вследствие аварии, чрезвычайной ситуации"
    #
    #                 Сведения о порядке подачи заявок
    #
    And 'Заказчик А' переходит к разделу "Сведения о порядке подачи заявок":
    And 'Заказчик А' заполняет дату и время "начала подачи заявок" в "HOURS" со смещением "-1" от времени публикации
    And 'Заказчик А' заполняет дату и время "окончания подачи заявок" в "MONTHS" со смещением "+1" от времени публикации
    #
    #                 Сведения о порядке работы комиссии
    #
    And 'Заказчик А' переходит к разделу "Сведения о порядке работы комиссии":
    And 'Заказчик А' заполняет дату и время "рассмотрения заявок" в "MONTHS" со смещением "+2" от времени публикации
    And 'Заказчик А' проверяет, что поле "Место рассмотрения заявок" содержит значение "ОКАТО: 18230501000"
    And 'Заказчик А' заполняет дату и время "подведения итогов" в "MONTHS" со смещением "+5" от времени публикации
    And 'Заказчик А' проверяет, что поле "Место подведения итогов" содержит значение "ОКАТО: 18230501000"
    #
    #                 Сведения о контактном лице
    #
    And 'Заказчик А' переходит к разделу "Сведения о контактном лице":
    And 'Заказчик А' заполняет поле 'Ответственное должностное лицо' "1"-м значением из списка
    And 'Заказчик А' нажимает кнопку "Заполнить контактную информацию"
    And 'Заказчик А' проверяет, что поле "Фамилия" содержит значение "ЗАКАЗЧИК1ALL223"
    And 'Заказчик А' проверяет, что поле "Имя" содержит значение "ЮРИДИЧЕСКОЕ"
    And 'Заказчик А' проверяет, что поле "Отчество" содержит значение "ЛИЦО"
    And 'Заказчик А' проверяет, что поле "Телефон (в международном формате)" содержит значение "+7 (978) 111-1111"
    And 'Заказчик А' проверяет, что поле "Адрес электронной почты" содержит значение "konturtester1@gmail.com"
    #-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
    #                 Сведения о лоте № 1 - Начало раздела
    #
    #             ______________________________________________________
    #            |    Вкладка [Общие сведения]
    #            |______________________________________________________
    And 'Заказчик А' переключается на вкладку лота "Общие сведения"
    #                 Общие сведения о лоте
    And 'Заказчик А' заполняет поле "Наименование" значением "Лот 1"
    And 'Заказчик А' устанавливает переключатель "Торги осуществляются" в положение "От цены за единицу продукции"
    And 'Заказчик А' заполняет поле со счётчиком "Начальная (максимальная) цена" значением "5"
    #                 Сведения о заключении договора
    And 'Заказчик А' заполняет поле со счётчиком "Количество участников, занявших места ниже первого," значением "3"
    And 'Заказчик А' заполняет поле со счётчиком "Срок направления договора (в днях)" значением "0"
    And 'Заказчик А' заполняет поле со счётчиком "Срок подписания договора участником (в днях)" значением "0"
    And 'Заказчик А' заполняет поле со счётчиком "Срок заключения договора (в днях)" значением "0"
    #             ______________________________________________________
    #            |    Вкладка [Товары, работы и услуги]
    #            |______________________________________________________
    And 'Заказчик А' переключается на вкладку лота "Товары, работы и услуги"
    #                 Товары, работы и услуги
    And 'Заказчик А' проверяет, что флажок "Лот является составным" "не установлен"
    #                 Изменение информации о товаре, работе или услуге
    And 'Заказчик А' заполняет поле "Наименование товара, работ, услуг" значением "Товар 1"
    And 'Заказчик А' заполняет поле лота Код ОКПД2 значением "36.00.11.000"
    #                                                      [Вода питьевая]
    And 'Заказчик А' заполняет поле лота Код ОКВЭД2 значением "01.11.13"
    #                                                      [Выращивание ржи]
    And 'Заказчик А' заполняет поле 'Единица измерения - код ОКЕИ' значением "Ампер (260)" из списка
    #                                                      Ампер (260)
    And 'Заказчик А' заполняет поле со счётчиком "Цена за единицу измерения" значением "5"
    And 'Заказчик А' заполняет поле со счётчиком "Количество" значением "1"
    And 'Заказчик А' переходит к полю "Дополнительные сведения"
    #             ______________________________________________________
    #            |    Вкладка [Сведения о заказчиках]
    #            |______________________________________________________
    And 'Заказчик А' переключается на вкладку лота "Сведения о заказчиках"
    #                 Сведения о заказчиках, подписывающих договоры
    And 'Заказчик А' проверяет, что флажок "Несколько заказчиков (совместная закупка)" "не установлен"
    And 'Заказчик А' проверяет, что флажок "Договор заключается с несколькими участниками" "не установлен"
    #                 Изменение информации о заказчике
    #And 'Заказчик А' выбирает второе по счёту значение в списке для поля 'Заказчик'
    And 'Заказчик А' заполняет поле со счётчиком "Обеспечение договора (%)" значением "1"
    And 'Заказчик А' переходит к полю "Место поставки товара, выполнения работ, оказания услуг"
    And 'Заказчик А' нажимает кнопку "Заполнить юридическим адресом"
    #             ______________________________________________________
    #            |    Вкладка [Документы лота]
    #            |______________________________________________________
    #             ______________________________________________________
    #            |    Вкладка [Дополнительные параметры лота]
    #            |______________________________________________________
    #
    #                 Сведения о лоте № 1 - Конец раздела
    #-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
    #
    #                 Документы
    #
    And 'Заказчик А' прикрепляет необходимые документы
    #
    #                 Публикация извещения и ожидание перехода в статус [Прием заявок]
    #
    And 'Заказчик А' публикует извещение
    #-------------------------------------------------------------------------------------------------------------------
    #                 Заказчик проверяет статус закупки и статус лота
    #-------------------------------------------------------------------------------------------------------------------
    #                 Лот № 1
    And 'Заказчик' переходит на страницу 'Мои закупки' и ищет опубликованное извещение
    And 'Заказчик' проверяет статус "Прием заявок" лота № "1" в опубликованном извещении
    #
    #                 Проверка входящего уведомления [Уведомление об опубликовании закупки № ]
    #
    And 'Заказчик' проверяет входящее уведомление "Уведомление о публикации закупки № "
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Администратор связывает опубликованную на площадке закупку с закупкой в ЕИС
    #                 ( присвоение закупке номера ЕИС для включения возможности подачи заявок )
    #
    #*******************************************************************************************************************
    When "Администратор" заходит в личный кабинет
    And 'Администратор' переходит на страницу "Поиск закупок"
    And 'Администратор' выполняет поиск по номеру закупки на странице 'Поиск закупок'
    And 'Администратор' переходит по ссылке с номером закупки в результатах поиска
    And 'Администратор' проверяет наличие кнопки 'Связать с закупкой в ЕИС' на карточке закупки
    And 'Администратор' связывает опубликованную на площадке закупку 'Конкурентная закупка - Конкурс' с закупкой в ЕИС
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Поставщик 1 подает заявку на участие в полном электронном конкурсе
    #                      ( конкурентная закупка в соответствии с нормами 223-ФЗ )
    #
    #*******************************************************************************************************************
    When "Поставщик Юридическое лицо 1" заходит в личный кабинет
    And 'Поставщик' переходит к списку закупок
    And 'Поставщик' находит созданную закупку
    And 'Поставщик' переходит по ссылке с номером закупки в результатах поиска
    And 'Поставщик' ожидает окончания загрузки страницы с деталями закупки 'Закупка № XXXX'
    And 'Поставщик' нажимает на кнопку 'Подать заявку' для лота № "1"
    And 'Поставщик' переходит к разделу заявки 'Сведения об участнике процедуры'
    And 'Поставщик' заполняет поле заявки 'Страна происхождения товара' значением 'Российская федерация'
    And 'Поставщик' заполняет поле заявки 'Предложение о цене договора' значением "4,00" руб.
    And 'Поставщик' заполняет поле заявки 'Номер контактного телефона'
    And 'Поставщик' прикрепляет документ в разделе заявки 'Сведения о товаре'
    And 'Поставщик' № "1" подтверждает заявку на участие для лота № "1"
    And 'Поставщик' проверяет статус заявки "Утверждена оператором"
    #
    #                 Проверка уведомления [Подтверждение получения заявки на участие в закупке № X по лоту № X]
    #
    And 'Поставщик' проверяет уведомление "Подтверждение получения заявки на участие в закупке № %s по лоту № 1"
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Поставщик 2 подает заявку на участие в полном электронном конкурсе
    #                      ( конкурентная закупка в соответствии с нормами 223-ФЗ )
    #
    #*******************************************************************************************************************
    When "Поставщик Юридическое лицо 2" заходит в личный кабинет
    And 'Поставщик' переходит к списку закупок
    And 'Поставщик' находит созданную закупку
    And 'Поставщик' переходит по ссылке с номером закупки в результатах поиска
    And 'Поставщик' ожидает окончания загрузки страницы с деталями закупки 'Закупка № XXXX'
    And 'Поставщик' нажимает на кнопку 'Подать заявку' для лота № "1"
    And 'Поставщик' переходит к разделу заявки 'Сведения об участнике процедуры'
    And 'Поставщик' заполняет поле заявки 'Страна происхождения товара' значением 'Российская федерация'
    And 'Поставщик' заполняет поле заявки 'Предложение о цене договора' значением "3,00" руб.
    And 'Поставщик' заполняет поле заявки 'Номер контактного телефона'
    And 'Поставщик' прикрепляет документ в разделе заявки 'Сведения о товаре'
    And 'Поставщик' № "2" подтверждает заявку на участие для лота № "1"
    And 'Поставщик' проверяет статус заявки "Утверждена оператором"
    #
    #                 Проверка уведомления [Подтверждение получения заявки на участие в закупке № X по лоту № X]
    #
    And 'Поставщик' проверяет уведомление "Подтверждение получения заявки на участие в закупке № %s по лоту № 1"
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Администратор меняет дату и время завершения приема заявок на текущие -> [Открытие доступа]
    #
    #*******************************************************************************************************************
    When "Администратор" заходит в личный кабинет
    And 'Администратор' переходит на страницу "Ускорение процедур"
    And 'Администратор' выполняет поиск по номеру закупки
    And 'Администратор' выполняет ускорение процедур
    And 'Администратор' проверяет статус лота "Открытие доступа" для лота с номером "1"
    And 'Администратор' переходит на страницу "Системные задачи"
    And 'Администратор' выбирает "Обновить статусы лотов конкурсов"
    And 'Пользователь' покидает систему
    #-------------------------------------------------------------------------------------------------------------------
    #                 Заказчик проверяет статус закупки и статус лота
    #-------------------------------------------------------------------------------------------------------------------
    #                 Лот № 1
    When "Заказчик" заходит в личный кабинет
    And 'Заказчик' заполняет поле 'Номер закупки' и нажимает на кнопку 'Поиск'
    And 'Заказчик' открывает извещение по ссылке с номером извещения в результатах поиска
    And 'Заказчик' проверяет статус закупки "Опубликована" и статус лота "Открытие доступа" для "1" лотов
    #*******************************************************************************************************************
    #
    #                 Заказчик публикует протокол открытия доступа
    #
    #*******************************************************************************************************************
    And 'Заказчик' переходит по ссылке "Откройте доступ к заявкам" для лота "1"
    And 'Заказчик' проверяет, что открыта страница "Один из протоколов"
    And 'Заказчик' переходит к просмотру раздела протокола "Общие сведения о протоколе"
    And 'Заказчик' проверяет статус протокола - 'Формирование'
    And 'Заказчик' переходит к просмотру раздела протокола "Формирование протокола из шаблона"
    And 'Заказчик' нажимает на кнопку 'Сформировать и прикрепить' и проверяет наличие прикрепленного файла протокола
    And 'Заказчик' проверяет наличие дубликатов в списке шаблонов протоколов
    And 'Заказчик' нажимает на кнопку 'Подписать и опубликовать'
    And 'Заказчик' нажимает на кнопку 'Продолжить' в диалоговом окне 'Предупреждение'
    And 'Заказчик' нажимает на кнопку 'OK' в диалоговом окне 'Информация'
    Then Протокол перешел в статус "Утвержден"
    #
    #                 Проверка входящего уведомления [Уведомление о публикации протокола открытия доступа закупки № ]
    #
    And 'Заказчик' проверяет входящее уведомление "Уведомление о публикации протокола открытия доступа закупки № %s"
    #-------------------------------------------------------------------------------------------------------------------
    #                 Заказчик проверяет статус закупки и статус лота
    #-------------------------------------------------------------------------------------------------------------------
    #                 Лот № 1
    And 'Заказчик' переходит на страницу 'Мои закупки' и ищет опубликованное извещение
    And 'Заказчик' открывает извещение по ссылке с номером извещения в результатах поиска
    And 'Заказчик' проверяет статус закупки "Опубликована" и статус лота "Рассмотрение заявок" для "1" лотов
    #*******************************************************************************************************************
    #
    #                 Заказчик публикует протокол рассмотрения и оценки заявок конкурса
    #
    #*******************************************************************************************************************
    And 'Заказчик' переходит по ссылке "Рассмотрите заявки" для лота "1"
    And 'Заказчик' проверяет, что открыта страница "Один из протоколов"
    And 'Заказчик' переходит к просмотру раздела протокола "Общие сведения о протоколе"
    And 'Заказчик' проверяет статус протокола - 'Формирование'
    And 'Заказчик' переходит к просмотру раздела протокола "Формирование протокола из шаблона"
    And 'Заказчик' нажимает на кнопку 'Допустить всех' для каждого из "1" лотов
    And 'Пользователь' перемещается к концу страницы
    And 'Заказчик' переходит к просмотру раздела протокола "Общие сведения о лоте"
    And 'Заказчик' добавляет к протоколу рассмотрения и оценки заявок конкурса переторжку
    And 'Заказчик' проверяет дату начала переторжки
    And 'Заказчик' переходит к просмотру раздела протокола "Формирование протокола из шаблона"
    And 'Заказчик' нажимает на кнопку 'Сформировать и прикрепить' и проверяет наличие прикрепленного файла протокола
    And 'Заказчик' проверяет наличие дубликатов в списке шаблонов протоколов
    And 'Заказчик' нажимает на кнопку 'Подписать и опубликовать'
    And 'Заказчик' нажимает на кнопку 'Продолжить' в диалоговом окне 'Предупреждение'
    And 'Заказчик' нажимает на кнопку 'OK' в диалоговом окне 'Информация'
    Then Протокол перешел в статус "Утвержден"
    #
    #                 Проверка входящего уведомления [Уведомление о публикации протокола рассмотрения заявок закупки №]
    #
    And 'Заказчик' проверяет входящее уведомление "Уведомление о публикации протокола рассмотрения и оценки заявок закупки № %s"
    #-------------------------------------------------------------------------------------------------------------------
    #                 Заказчик проверяет статус закупки и статус лота
    #-------------------------------------------------------------------------------------------------------------------
    #                 Лот № 1
    And 'Заказчик' переходит на страницу 'Мои закупки' и ищет опубликованное извещение
    And 'Заказчик' открывает извещение по ссылке с номером извещения в результатах поиска
    And 'Заказчик' проверяет статус закупки "Опубликована" и статус лота "Ожидает переторжку" для "1" лотов
    #*******************************************************************************************************************
    #
    #                 Заказчик отправляет запрос на разъяснение заявки Поставщику 1
    #
    #*******************************************************************************************************************
    And 'Заказчик' открывает извещение по ссылке с номером извещения в результатах поиска
    # Просмотр извещения
    And 'Заказчик' переходит к просмотру раздела "Сведения о протоколах"
    And 'Заказчик' нажимает на кнопку "Открыть форму протокола рассмотрения и оценки заявок"
    # Протокол рассмотрения и оценки заявок
    And 'Заказчик' проверяет, что открыта страница "Один из протоколов"
    And 'Заказчик' переходит к просмотру раздела протокола "Общие сведения о протоколе"
    And 'Заказчик' проверяет статус протокола - 'Утвержден'
    And 'Заказчик' переходит к просмотру раздела протокола "Сведения протокола рассмотрения и оценки заявок"
    And 'Заказчик' переходит по ссылке 'Отправить' в 1 строке столбца 'Запрос на разъяснение заявки' на странице протокола
    And 'Пользователь' переключается на новую вкладку в браузере
    # Запрос на разъяснение заявки - создание
    And 'Заказчик' проверяет, что открыта страница "Запрос на разъяснение заявки - создание"
    And 'Заказчик' переходит к просмотру раздела страницы 'Запрос на разъяснение заявки' "Общие сведения о закупке":
    And 'Заказчик' проверяет что номер закупки на странице 'Запрос на разъяснение заявки' верен
    And 'Заказчик' проверяет, что поле "Номер редакции извещения" страницы 'Запрос на разъяснение заявки' содержит "1"
    And 'Заказчик' проверяет что наименование закупки на странице 'Запрос на разъяснение заявки' верно
    And 'Заказчик' проверяет, что поле "Номер лота" страницы 'Запрос на разъяснение заявки' содержит "1"
    And 'Заказчик' проверяет, что поле "Наименование лота" страницы 'Запрос на разъяснение заявки' содержит "Лот 1"
    And 'Заказчик' переходит к просмотру раздела страницы 'Запрос на разъяснение заявки' "Запрос на разъяснение заявки на участие в закупке":
    And 'Заказчик' проверяет, что поле "Участник закупки" страницы 'Запрос на разъяснение заявки' содержит не пустое значение
    And 'Заказчик' проверяет, что поле "Номер заявки" страницы 'Запрос на разъяснение заявки' содержит "1"
    And 'Заказчик' заполняет поле 'Срок предоставления ответа' на странице 'Запрос на разъяснение заявки'
    And 'Заказчик' заполняет поле "Текст запроса" на странице 'Запрос на разъяснение заявки' строкой в 2000 байт
    And 'Заказчик' проверяет, что поле "осталось" страницы 'Запрос на разъяснение заявки' содержит "1000"
    And 'Заказчик' переходит к просмотру раздела страницы 'Запрос на разъяснение заявки' "Документы запроса":
    And 'Заказчик' прикрепляет документ в разделе 'Документы запроса' на странице 'Запрос на разъяснение заявки'
    And 'Заказчик' проверяет возможность скачать документы запроса со страницы 'Запрос на разъяснение заявки'
    And 'Заказчик' переходит к просмотру раздела страницы 'Запрос на разъяснение заявки' "Заголовок страницы":
    And 'Заказчик' отправляет запрос на разъяснение заявки со страницы 'Запрос на разъяснение заявки'
    # Разъяснение заявки
    And 'Заказчик' проверяет, что открыта страница "Разъяснение заявки"
    And 'Заказчик' сохраняет в параметрах теста номер запроса на разъяснение заявки на странице 'Разъяснение заявки'
    And 'Заказчик' переходит к просмотру раздела страницы 'Разъяснение заявки' "Заголовок страницы":
    And 'Заказчик' нажимает на кнопку "Внести изменения" на странице 'Разъяснение заявки'
    # Запрос на разъяснение заявки - редактирование
    And 'Заказчик' проверяет, что открыта страница "Запрос на разъяснение заявки - редактирование"
    And 'Заказчик' переходит к просмотру раздела страницы 'Запрос на разъяснение заявки' "Общие сведения о закупке":
    And 'Заказчик' проверяет что номер закупки на странице 'Запрос на разъяснение заявки' верен
    And 'Заказчик' проверяет, что поле "Номер редакции извещения" страницы 'Запрос на разъяснение заявки' содержит "1"
    And 'Заказчик' проверяет что наименование закупки на странице 'Запрос на разъяснение заявки' верно
    And 'Заказчик' проверяет, что поле "Номер лота" страницы 'Запрос на разъяснение заявки' содержит "1"
    And 'Заказчик' проверяет, что поле "Наименование лота" страницы 'Запрос на разъяснение заявки' содержит "Лот 1"
    And 'Заказчик' переходит к просмотру раздела страницы 'Запрос на разъяснение заявки' "Запрос на разъяснение заявки на участие в закупке":
    And 'Заказчик' проверяет, что поле "Участник закупки" страницы 'Запрос на разъяснение заявки' содержит не пустое значение
    And 'Заказчик' проверяет, что поле "Номер заявки" страницы 'Запрос на разъяснение заявки' содержит "1"
    And 'Заказчик' проверяет, что поле "Срок предоставления ответа (МСК)" страницы 'Запрос на разъяснение заявки' содержит не пустое значение
    And 'Заказчик' заполняет поле "Текст запроса" на странице 'Запрос на разъяснение заявки' строкой в 1000 байт
    And 'Заказчик' проверяет, что поле "осталось" страницы 'Запрос на разъяснение заявки' содержит "0"
    And 'Заказчик' переходит к просмотру раздела страницы 'Запрос на разъяснение заявки' "Заголовок страницы":
    And 'Заказчик' отправляет запрос на разъяснение заявки со страницы 'Запрос на разъяснение заявки'
    # Разъяснение заявки
    And 'Заказчик' проверяет, что открыта страница "Разъяснение заявки"
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Поставщик 1 отправляет разъяснение заявки Заказчику
    #
    #*******************************************************************************************************************
    When "Поставщик Юридическое лицо 1" заходит в личный кабинет
    # Запросы на разъяснение заявок
    And 'Поставщик' переходит на страницу "Запросы на разъяснение заявок"
    And 'Поставщик' находит отправленный Заказчиком запрос используя номер закупки, сохраненный ранее в параметрах теста
    And 'Поставщик' переходит по ссылке с номером запроса в результатах поиска
    # Запрос на разъяснение заявки в режиме просмотра
    And 'Поставщик' проверяет, что открыта страница "Запрос на разъяснение заявки в режиме просмотра"
    And 'Поставщик' проверяет реквизиты и статус запроса на разъяснение заявки со строкой в 3000 байт
    And 'Поставщик' нажимает на кнопку "Ответить на запрос" на странице 'Запрос на разъяснение заявки'
    # Запрос на разъяснение заявки в режиме редактирования
    And 'Поставщик' проверяет, что открыта страница "Запрос на разъяснение заявки в режиме редактирования"
    And 'Поставщик' переходит к просмотру раздела запроса на разъяснение "Общие сведения о закупке":
    And 'Поставщик' проверяет что номер закупки на странице запроса на разъяснение верен
    And 'Поставщик' переходит к просмотру раздела запроса на разъяснение "Разъяснение заявки":
    And 'Поставщик' заполняет поле "Текст ответа" на странице запроса на разъяснение строкой в 2000 байт
    And 'Поставщик' проверяет, что поле "Текст ответа" на странице запроса на разъяснение содержит строку в 2000 байт
    And 'Поставщик' проверяет, что поле "Текст ответа осталось" на странице запроса на разъяснение содержит "0"
    And 'Поставщик' прикрепляет документ в разделе 'Разъяснение заявки' на странице запроса на разъяснение
    And 'Поставщик' отправляет запрос на разъяснение заявки
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Заказчик получает разъяснение заявки от Поставщика 1
    #
    #*******************************************************************************************************************
    When "Заказчик" заходит в личный кабинет
    # Входящие сообщения
    And 'Заказчик' проверяет последнее из входящих уведомлений "Уведомление о направлении разъяснения по заявке на участие в закупке № %s" по номеру закупки
    And 'Заказчик' переходит по первой верхней ссылке в колонке 'Входящий номер' результатов поиска уведомлений
    # Просмотр сообщения
    And 'Заказчик' проверяет, что открыта страница "Просмотр сообщения"
    And 'Заказчик' переходит к просмотру разъяснения заявки по ссылке на странице 'Просмотр сообщения'
    And 'Пользователь' переключается на новую вкладку в браузере
    # Разъяснение заявки
    And 'Заказчик' проверяет, что открыта страница "Разъяснение заявки"
    And 'Заказчик' переходит к просмотру раздела страницы 'Разъяснение заявки' "Текст разъяснения":
    And 'Заказчик' проверяет, что поле "Текст разъяснения" на странице 'Разъяснение заявки' содержит строку в 2000 байт
    And 'Заказчик' проверяет текст ссылки для скачивания первого сверху прикрепленного файла в разделе 'Документы разъяснения' на странице 'Разъяснение заявки'
    And 'Заказчик' проверяет отображение кнопки 'Показать' для первого сверху прикрепленного файла в разделе 'Документы разъяснения' на странице 'Разъяснение заявки'
    And 'Заказчик' проверяет возможность скачать прикрепленные документы в разделе 'Документы разъяснения' на странице 'Разъяснение заявки'
    And 'Пользователь' покидает систему
#    До этого момента тест отлажен
#    Убрать или закомментировать эту строку после завершения работы над тестом
#    And 'Пользователь' сохраняет значение "1100" как параметр "CommonClarificationRequestNumber" автоматического теста
#    And 'Пользователь' сохраняет значение "80317" как параметр "PurchaseNumber" автоматического теста
#    And 'Пользователь' сохраняет значение "Тестовый конкурс (Конкурентная закупка) от 28.09.2020 12:58:845." как параметр "PurchaseName" автоматического теста
