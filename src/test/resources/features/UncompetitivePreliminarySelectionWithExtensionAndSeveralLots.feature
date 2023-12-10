@smoke @runTest @uncompetitive_preliminary_selection_with_extension_and_several_lots
Feature: Uncompetitive Preliminary Selection With Extension And Several Lots
  #=====================================================================================================================
  # Предварительный отбор (с продлением) неконкурентный - многолотовая закупка
  # ( https://ve-lab.visualstudio.com/kontur.market/_workitems/edit/320405 )
  # В системе созданы следующие пользователи:
  # Заказчик                   - "Заказчик Юридическое лицо 1"
  # Поставщик 1                - "Поставщик Юридическое лицо 1"
  # Поставщик 2                - "Поставщик Юридическое лицо 2"
  # Администратор ( Оператор ) - "Администратор"
  #=====================================================================================================================
  Scenario: Неконкурентный предварительный отбор с продлением и многолотовой закупкой
    Given Параметру 'Тип теста' установлено значение "Uncompetitive Preliminary Selection With Extension And Several Lots"
    And Все требуемые для теста сертификаты присутствуют на компьютере
    #*******************************************************************************************************************
    #
    #                 Заказчик публикует извещение о проведении предварительного отбора (с продлением)
    #
    #*******************************************************************************************************************
    When "Заказчик" заходит в личный кабинет
    And 'Заказчик А' открывает страницу создания извещения для неконкурентной закупки "Предварительный отбор (с продлением)"
    And 'Заказчик А' заполняет сведения о процедуре: "Закупка по 223-ФЗ - Предварительный отбор (с продлением)"
    #
    #                 Общие сведения о закупке
    #
    And 'Заказчик А' переходит к разделу "Общие сведения о закупке":
    And 'Заказчик А' проверяет, что поле "Способ определения поставщика (подрядчика, исполнителя)" содержит значение "Предварительный отбор (с продлением)"
    And 'Заказчик А' проверяет, что поле "Тип закупки" содержит значение "Закупка в соответствии с нормами 223-ФЗ"
    And 'Заказчик А' заполняет поле 'Наименование процедуры' сгенерированным именем для типа закупки "Пред. отбор (с продл.) - Неконкур. - Многолот."
    And 'Заказчик А' заполняет поле "Описание закупки" значением "Автоматические тесты - Пред. отбор (с продл.) - Неконкур. - Многолот."
    And 'Заказчик А' устанавливает флажок "Многолотовая закупка"
    And 'Заказчик А' устанавливает флажок "Закупка проводится вследствие аварии, чрезвычайной ситуации"
    And 'Заказчик А' проверяет, что флажок "Возможность участия в процедуре без ЭЦП" "не установлен"
    #
    #                 Сведения о порядке подачи заявок
    #
    And 'Заказчик А' переходит к разделу "Сведения о порядке подачи заявок":
    And 'Заказчик А' заполняет дату и время "начала подачи заявок" в "HOURS" со смещением "+0" от времени публикации
    And 'Заказчик А' заполняет дату и время "последнего окончания подачи заявок" в "MONTHS" со смещением "+2" от времени публикации
    And 'Заказчик А' проверяет, что поле "Место подачи заявок" содержит значение "Электронная площадка РТС-тендер"
    And 'Заказчик А' проверяет, что поле "Порядок подачи заявок" содержит значение "В электронной форме"
    #
    #                 Сведения о порядке работы комиссии
    #
    And 'Заказчик А' переходит к разделу "Сведения о порядке работы комиссии":
    And 'Заказчик А' заполняет дату и время "следующего окончания подачи заявок" в "MONTHS" со смещением "+1" от времени публикации
    #
    #                 Сведения о предоставлении документации
    #
    And 'Заказчик А' переходит к разделу "Сведения о предоставлении документации":
    And 'Заказчик А' проверяет дату "Срок предоставления документации (МСК) От" в "MONTHS" со смещением "+0" от времени публикации
    And 'Заказчик А' проверяет дату "Срок предоставления документации (МСК) До" в "MONTHS" со смещением "+2" от времени публикации
    And 'Заказчик А' проверяет, что поле "Место предоставления документации" содержит значение "Электронная площадка РТС-тендер"
    And 'Заказчик А' проверяет, что поле "Порядок предоставления документации" содержит значение "В электронной форме"
    #
    #                 Сведения о порядке предоставления разъяснений
    #
    And 'Заказчик А' переходит к разделу "Сведения о порядке предоставления разъяснений":
    And 'Заказчик А' проверяет, что поле "Срок направления запроса о разъяснении документации (дней до окончания подачи заявок)" содержит значение "5"
    And 'Заказчик А' проверяет, что переключатель "Срок направления запроса о разъяснении документации - рабочих" отмечен
    And 'Заказчик А' проверяет, что переключатель "Срок направления запроса о разъяснении документации - календарных" не отмечен
    And 'Заказчик А' проверяет, что поле "Срок предоставления разъяснения документации" содержит значение "3"
    And 'Заказчик А' проверяет, что переключатель "Срок предоставления разъяснения документации - рабочих" отмечен
    And 'Заказчик А' проверяет, что переключатель "Срок предоставления разъяснения документации - календарных" не отмечен
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
    #                 Сведения о предмете предварительного отбора № 1 - Начало раздела
    #
    #             ______________________________________________________
    #            |    Вкладка [Общие сведения]
    #            |______________________________________________________
    And 'Заказчик А' переключается на вкладку лота "Общие сведения"
    #                 Общие сведения о лоте
    And 'Заказчик А' заполняет поле 'Наименование' значением "Предмет предварительного отбора" с сегодняшней датой и номером "1"
    And 'Заказчик А' заполняет поле со счётчиком "Начальная (максимальная) цена" значением "5"
    #             ______________________________________________________
    #            |    Вкладка [Товары, работы и услуги]
    #            |______________________________________________________
    And 'Заказчик А' переключается на вкладку лота "Товары, работы и услуги"
    #                 Товары, работы и услуги
    And 'Заказчик А' проверяет, что флажок "Предмет предварительного отбора является составным" "не установлен"
    #                 Изменение информации о товаре, работе или услуге
    And 'Заказчик А' заполняет поле "Наименование товара, работ, услуг" значением "Предмет предварительного отбора №1"
    And 'Заказчик А' заполняет поле лота Код ОКПД2 значением "36.00.11.000"
    #                                                      [Вода питьевая]
    And 'Заказчик А' заполняет поле лота Код ОКВЭД2 значением "01.11.13"
    #                                                      [Выращивание ржи]
    And 'Заказчик А' проверяет, что флажок "Невозможно определить количество (объем)" "не установлен"
    And 'Заказчик А' заполняет поле 'Единица измерения - код ОКЕИ' значением "Ампер (260)" из списка
    #                                                      Ампер (260)
    And 'Заказчик А' заполняет поле со счётчиком "Количество" значением "1"
    And 'Заказчик А' переходит к полю "Дополнительные сведения"
    And 'Заказчик А' заполняет поле "Дополнительные сведения" значением "Дополнительные сведения"
    #             ______________________________________________________
    #            |    Вкладка [Сведения о заказчиках]
    #            |______________________________________________________
    And 'Заказчик А' переключается на вкладку лота "Сведения о заказчиках"
    #                 Информации о заказчике
    And 'Заказчик А' нажимает кнопку "Заполнить юридическим адресом"
    And 'Пользователь' сохраняет копию экрана приложения
    #
    #                 Сведения о предмете предварительного отбора № 1 - Конец раздела
    #-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
    And 'Заказчик А' добавляет к закупке новый лот
    #-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
    #                 Сведения о предмете предварительного отбора № 2 - Начало раздела
    #
    #             ______________________________________________________
    #            |    Вкладка [Общие сведения]
    #            |______________________________________________________
    And 'Заказчик А' переключается на вкладку лота "Общие сведения"
    #                 Общие сведения о лоте
    And 'Заказчик А' заполняет поле 'Наименование' значением "Предмет предварительного отбора" с сегодняшней датой и номером "2"
    And 'Заказчик А' заполняет поле со счётчиком "Начальная (максимальная) цена" значением "5"
    #             ______________________________________________________
    #            |    Вкладка [Товары, работы и услуги]
    #            |______________________________________________________
    And 'Заказчик А' переключается на вкладку лота "Товары, работы и услуги"
    #                 Товары, работы и услуги
    And 'Заказчик А' проверяет, что флажок "Предмет предварительного отбора является составным" "не установлен"
    #                 Изменение информации о товаре, работе или услуге
    And 'Заказчик А' заполняет поле "Наименование товара, работ, услуг" значением "Предмет предварительного отбора №2"
    And 'Заказчик А' заполняет поле лота Код ОКПД2 значением "36.00.11.000"
    #                                                      [Вода питьевая]
    And 'Заказчик А' заполняет поле лота Код ОКВЭД2 значением "01.11.13"
    #                                                      [Выращивание ржи]
    And 'Заказчик А' проверяет, что флажок "Невозможно определить количество (объем)" "не установлен"
    And 'Заказчик А' заполняет поле 'Единица измерения - код ОКЕИ' значением "Ампер (260)" из списка
    #                                                      Ампер (260)
    And 'Заказчик А' заполняет поле со счётчиком "Количество" значением "1"
    And 'Заказчик А' переходит к полю "Дополнительные сведения"
    And 'Заказчик А' заполняет поле "Дополнительные сведения" значением "Дополнительные сведения"
    #             ______________________________________________________
    #            |    Вкладка [Сведения о заказчиках]
    #            |______________________________________________________
    And 'Заказчик А' переключается на вкладку лота "Сведения о заказчиках"
    #                 Информации о заказчике
    And 'Заказчик А' выбирает второе по счёту значение в списке для поля 'Заказчик'
    And 'Заказчик А' заполняет поле 'Регион' значением "Вологодск" во вкладке 'Сведения о заказчиках'
    And 'Заказчик А' нажимает кнопку "Заполнить юридическим адресом"
    #
    #                 Сведения о предмете предварительного отбора № 2 - Конец раздела
    #-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
    #
    #                 Документы
    #
    And 'Заказчик А' переходит к разделу "Документы":
    And 'Заказчик А' прикрепляет необходимые документы
    #
    #                 Сохранение черновика извещения
    #
    And 'Заказчик А' сохраняет черновик извещения
    #-------------------------------------------------------------------------------------------------------------------
    #                 Публикация извещения и ожидание перехода в статус [Прием заявок]
    #-------------------------------------------------------------------------------------------------------------------
    And 'Заказчик А' публикует извещение предварительного отбора
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
    And 'Администратор' связывает опубликованную на площадке закупку 'Неконкурентная закупка - Предварительный отбор с продлением и несколькими лотами' с закупкой в ЕИС
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Заказчик проверяет наличие номера ЕИС на опубликованном извещении
    #
    #*******************************************************************************************************************
    When "Заказчик" заходит в личный кабинет
    And 'Заказчик' заполняет поле 'Номер закупки' и нажимает на кнопку 'Поиск'
    And 'Заказчик' открывает извещение по ссылке с номером извещения в результатах поиска
    And 'Заказчик' проверяет, что поле "Номер процедуры в ЕИС" на странице 'Просмотр извещения' содержит не пустое значение
    And 'Заказчик' проверяет статус процедуры "Опубликована" и статус предмета предварительного отбора "Прием заявок" для "2" предметов
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Администратор меняет дата и время публикации уведомления об итогах рассмотрения заявок на
    #                                       Текущая-2мин. -> [Прием заявок]
    #
    #*******************************************************************************************************************
    When "Администратор" заходит в личный кабинет
    And 'Администратор' переходит на страницу "Ускорение процедур"
    And 'Администратор' выполняет поиск по номеру закупки
    And 'Администратор' нажимает на кнопку "Дата и время публикации уведомления об итогах рассмотрения заявок - Текущая-2мин." на странице 'Ускорение процедур'
    And 'Администратор' проверяет статус предмета предварительного отбора "Прием заявок" для предмета с номером "1" без нажатия кнопки 'Обновить'
    And 'Администратор' проверяет статус предмета предварительного отбора "Прием заявок" для предмета с номером "2" без нажатия кнопки 'Обновить'
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Заказчик изменяет дату и время следующего окончания подачи заявок
    #
    #*******************************************************************************************************************
    When "Заказчик" заходит в личный кабинет
    And 'Заказчик' заполняет поле 'Номер закупки' и нажимает на кнопку 'Поиск'
    And 'Заказчик' открывает извещение по ссылке с номером извещения в результатах поиска
    And 'Заказчик' проверяет, что открыта страница "Просмотр извещения"
    And 'Заказчик' нажимает на кнопку "Открыть форму уведомлений об итогах рассмотрения заявок"
    And 'Заказчик' заполняет поле "Дата и время следующего окончания подачи заявок" в "HOURS" со смещением "-1" от времени публикации на странице 'Уведомление об итогах рассмотрения заявок предварительного отбора'
    And 'Заказчик' прикрепляет файл уведомления на странице 'Уведомление об итогах рассмотрения заявок предварительного отбора'
    And 'Заказчик' нажимает на кнопку 'Подписать и опубликовать'
    And 'Заказчик' нажимает на кнопку 'Продолжить' в диалоговом окне 'Предупреждение'
    And 'Заказчик' нажимает на кнопку 'OK' в диалоговом окне 'Информация'
    Then Протокол перешел в статус "Утвержден"
    #
    #                                       Проверка входящего уведомления
    #     [Уведомление о публикации протокола об итогах рассмотрения заявок предварительного отбора процедуры № %s ]
    #
    And 'Заказчик' проверяет входящее уведомление "Уведомление о публикации протокола об итогах рассмотрения заявок предварительного отбора процедуры № %s"
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Поставщик 1 подает заявку на участие в предварительном отборе (с продлением)
    #
    #*******************************************************************************************************************
    When "Поставщик Юридическое лицо 1" заходит в личный кабинет
    #                 Предмет предварительного отбора № 1
    And 'Поставщик' переходит к списку закупок
    And 'Поставщик' находит созданную закупку
    And 'Поставщик' проверяет количество строк 2 в таблице 'Закупки' на странице 'Поиск закупок'
    And 'Поставщик' переходит по ссылке с номером закупки в результатах поиска для лота № "1"
    And 'Поставщик' ожидает окончания загрузки страницы с деталями процедуры 'Процедуры № XXXX'
    And 'Поставщик' нажимает на кнопку 'Подать заявку' для предмета предварительного отбора № "1"
    And 'Поставщик' прикрепляет документ в разделе заявки 'Сведения о товаре'
    And 'Поставщик' заполняет поле заявки 'Страна происхождения товара' значением 'Российская федерация'
    And 'Поставщик' переходит к разделу заявки 'Сведения об участнике процедуры'
    And 'Поставщик' заполняет поле заявки 'Номер контактного телефона'
    And 'Поставщик' № "1" подтверждает заявку на участие для предмета предварительного отбора № "1"
    #
    #                 Проверка уведомления ["Подтверждение получения заявки на участие в процедуре № X]
    #
    And 'Поставщик' проверяет уведомление "Подтверждение получения заявки на участие в процедуре № %s"
    #                 Предмет предварительного отбора № 2
    And 'Поставщик' переходит к списку закупок
    And 'Поставщик' находит созданную закупку
    And 'Поставщик' проверяет количество строк 2 в таблице 'Закупки' на странице 'Поиск закупок'
    And 'Поставщик' переходит по ссылке с номером закупки в результатах поиска для лота № "2"
    And 'Поставщик' ожидает окончания загрузки страницы с деталями процедуры 'Процедуры № XXXX'
    And 'Поставщик' нажимает на кнопку 'Подать заявку' для предмета предварительного отбора № "2"
    And 'Поставщик' прикрепляет документ в разделе заявки 'Сведения о товаре'
    And 'Поставщик' заполняет поле заявки 'Страна происхождения товара' значением 'Российская федерация'
    And 'Поставщик' переходит к разделу заявки 'Сведения об участнике процедуры'
    And 'Поставщик' заполняет поле заявки 'Номер контактного телефона'
    And 'Поставщик' № "1" подтверждает заявку на участие для предмета предварительного отбора № "2"
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Администратор меняет дата и время публикации уведомления об итогах рассмотрения заявок на
    #                                       Текущая-2мин. -> [Прием заявок]
    #
    #*******************************************************************************************************************
    When "Администратор" заходит в личный кабинет
    And 'Администратор' переходит на страницу "Ускорение процедур"
    And 'Администратор' выполняет поиск по номеру закупки
    And 'Администратор' нажимает на кнопку "Дата и время публикации уведомления об итогах рассмотрения заявок - Текущая-2мин." на странице 'Ускорение процедур'
    And 'Администратор' проверяет статус предмета предварительного отбора "Прием заявок" для предмета с номером "1" без нажатия кнопки 'Обновить'
    And 'Администратор' проверяет статус предмета предварительного отбора "Прием заявок" для предмета с номером "2" без нажатия кнопки 'Обновить'
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Заказчик публикует второе уведомление об итогах рассмотрения заявок предварительного отбора
    #
    #*******************************************************************************************************************
    When "Заказчик" заходит в личный кабинет
    And 'Заказчик' заполняет поле 'Номер закупки' и нажимает на кнопку 'Поиск'
    And 'Заказчик' открывает извещение по ссылке с номером извещения в результатах поиска
    And 'Заказчик' проверяет, что поле "Номер процедуры в ЕИС" на странице 'Просмотр извещения' содержит не пустое значение
    And 'Заказчик' проверяет статус процедуры "Опубликована" и статус предмета предварительного отбора "Прием заявок" для "2" предметов
    And 'Заказчик' открывает извещение по ссылке с номером извещения в результатах поиска
    And 'Заказчик' нажимает на кнопку "Открыть вторую форму уведомлений об итогах рассмотрения заявок"
    And 'Заказчик' проверяет, что открыта страница "Один из протоколов"
    #                 Заказчик добавляет в реестр предмет предварительного отбора № 1
    And 'Заказчик' переходит к разделу "Предмет предварительного отбора №1" на странице 'Уведомление об итогах рассмотрения заявок предварительного отбора'
    And 'Заказчик' проверяет, что таблица 'Сведения уведомления об итогах рассмотрения заявок' для раздела 'Предмет предварительного отбора' под номером "1" содержит 1 заявку
    And 'Заказчик' в разделе 'Предмет предварительного отбора' под номером "1" добавляет в реестр 1 заявку в таблице 'Сведения уведомления об итогах рассмотрения заявок'
    #                 Заказчик отклоняет предмет предварительного отбора № 2
    And 'Заказчик' переходит к разделу "Предмет предварительного отбора №2" на странице 'Уведомление об итогах рассмотрения заявок предварительного отбора'
    And 'Заказчик' проверяет, что таблица 'Сведения уведомления об итогах рассмотрения заявок' для раздела 'Предмет предварительного отбора' под номером "2" содержит 1 заявку
    And 'Заказчик' в разделе 'Предмет предварительного отбора' под номером "2" отклоняет 1 заявку в таблице 'Сведения уведомления об итогах рассмотрения заявок'
    And 'Заказчик' прикрепляет файл уведомления на странице 'Уведомление об итогах рассмотрения заявок предварительного отбора'
    And 'Заказчик' нажимает на кнопку 'Подписать и опубликовать'
    And 'Заказчик' нажимает на кнопку 'Продолжить' в диалоговом окне 'Предупреждение'
    And 'Заказчик' нажимает на кнопку 'OK' в диалоговом окне 'Информация'
    Then Протокол перешел в статус "Утвержден"
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Поставщик 1 подает заявку на участие в предварительном отборе (с продлением)
    #
    #*******************************************************************************************************************
    When "Поставщик Юридическое лицо 1" заходит в личный кабинет
    #                 Предмет предварительного отбора № 1
    And 'Поставщик' переходит к списку закупок
    And 'Поставщик' находит созданную закупку
    And 'Поставщик' проверяет количество строк 2 в таблице 'Закупки' на странице 'Поиск закупок'
    And 'Поставщик' переходит по ссылке с номером закупки в результатах поиска для лота № "1"
    And 'Поставщик' ожидает окончания загрузки страницы с деталями процедуры 'Процедуры № XXXX'
    And 'Поставщик' проверяет отсутсвие кнопки 'Подать заявку' для выбранного предмета предварительного отбора
    #                 Предмет предварительного отбора № 2
    And 'Поставщик' переходит к списку закупок
    And 'Поставщик' находит созданную закупку
    And 'Поставщик' проверяет количество строк 2 в таблице 'Закупки' на странице 'Поиск закупок'
    And 'Поставщик' переходит по ссылке с номером закупки в результатах поиска для лота № "2"
    And 'Поставщик' ожидает окончания загрузки страницы с деталями процедуры 'Процедуры № XXXX'
    And 'Поставщик' нажимает на кнопку 'Подать заявку' для предмета предварительного отбора № "2"
    And 'Поставщик' прикрепляет документ в разделе заявки 'Сведения о товаре'
    And 'Поставщик' заполняет поле заявки 'Страна происхождения товара' значением 'Российская федерация'
    And 'Поставщик' переходит к разделу заявки 'Сведения об участнике процедуры'
    And 'Поставщик' заполняет поле заявки 'Номер контактного телефона'
    And 'Поставщик' № "1" подтверждает заявку на участие для предмета предварительного отбора № "2"
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Поставщик 2 подает заявку на участие в предварительном отборе (с продлением)
    #
    #*******************************************************************************************************************
    When "Поставщик Юридическое лицо 2" заходит в личный кабинет
    #                 Предмет предварительного отбора № 1
    And 'Поставщик' переходит к списку закупок
    And 'Поставщик' находит созданную закупку
    And 'Поставщик' проверяет количество строк 2 в таблице 'Закупки' на странице 'Поиск закупок'
    And 'Поставщик' переходит по ссылке с номером закупки в результатах поиска для лота № "1"
    And 'Поставщик' ожидает окончания загрузки страницы с деталями процедуры 'Процедуры № XXXX'
    And 'Поставщик' нажимает на кнопку 'Подать заявку' для предмета предварительного отбора № "1"
    And 'Поставщик' прикрепляет документ в разделе заявки 'Сведения о товаре'
    And 'Поставщик' заполняет поле заявки 'Страна происхождения товара' значением 'Российская федерация'
    And 'Поставщик' переходит к разделу заявки 'Сведения об участнике процедуры'
    And 'Поставщик' заполняет поле заявки 'Номер контактного телефона'
    And 'Поставщик' № "2" подтверждает заявку на участие для предмета предварительного отбора № "1"
    #
    #                 Проверка уведомления ["Подтверждение получения заявки на участие в процедуре № X]
    #
    And 'Поставщик' проверяет уведомление "Подтверждение получения заявки на участие в процедуре № %s"
    #                 Предмет предварительного отбора № 2
    And 'Поставщик' переходит к списку закупок
    And 'Поставщик' находит созданную закупку
    And 'Поставщик' проверяет количество строк 2 в таблице 'Закупки' на странице 'Поиск закупок'
    And 'Поставщик' переходит по ссылке с номером закупки в результатах поиска для лота № "2"
    And 'Поставщик' ожидает окончания загрузки страницы с деталями процедуры 'Процедуры № XXXX'
    And 'Поставщик' нажимает на кнопку 'Подать заявку' для предмета предварительного отбора № "2"
    And 'Поставщик' прикрепляет документ в разделе заявки 'Сведения о товаре'
    And 'Поставщик' заполняет поле заявки 'Страна происхождения товара' значением 'Российская федерация'
    And 'Поставщик' переходит к разделу заявки 'Сведения об участнике процедуры'
    And 'Поставщик' заполняет поле заявки 'Номер контактного телефона'
    And 'Поставщик' № "2" подтверждает заявку на участие для предмета предварительного отбора № "2"
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Администратор меняет дата и время публикации уведомления об итогах рассмотрения заявок на
    #                                       Текущая-2мин. -> [Прием заявок]
    #
    #*******************************************************************************************************************
    When "Администратор" заходит в личный кабинет
    And 'Администратор' переходит на страницу "Ускорение процедур"
    And 'Администратор' выполняет поиск по номеру закупки
    And 'Администратор' нажимает на кнопку "Дата и время публикации уведомления об итогах рассмотрения заявок - Текущая-2мин." на странице 'Ускорение процедур'
    And 'Администратор' проверяет статус предмета предварительного отбора "Прием заявок" для предмета с номером "1" без нажатия кнопки 'Обновить'
    And 'Администратор' проверяет статус предмета предварительного отбора "Прием заявок" для предмета с номером "2" без нажатия кнопки 'Обновить'
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Заказчик публикует третье уведомление об итогах рассмотрения заявок предварительного отбора
    #
    #*******************************************************************************************************************
    When "Заказчик" заходит в личный кабинет
    And 'Заказчик' заполняет поле 'Номер закупки' и нажимает на кнопку 'Поиск'
    And 'Заказчик' открывает извещение по ссылке с номером извещения в результатах поиска
    And 'Заказчик' проверяет, что поле "Номер процедуры в ЕИС" на странице 'Просмотр извещения' содержит не пустое значение
    And 'Заказчик' проверяет статус процедуры "Опубликована" и статус предмета предварительного отбора "Прием заявок" для "2" предметов
    And 'Заказчик' открывает извещение по ссылке с номером извещения в результатах поиска
    And 'Заказчик' нажимает на кнопку "Открыть третью форму уведомлений об итогах рассмотрения заявок"
    And 'Заказчик' проверяет, что открыта страница "Один из протоколов"
    And 'Заказчик' переходит к просмотру раздела "Предмет предварительного отбора №1" на странице 'Уведомление об итогах рассмотрения заявок предварительного отбора'
    And 'Заказчик' проверяет, что таблица 'Сведения уведомления об итогах рассмотрения заявок' для раздела 'Предмет предварительного отбора' под номером "1" содержит 1 заявку
    And 'Заказчик' в разделе 'Предмет предварительного отбора' под номером "1" нажимает на кнопку 'Допустить всех'
    And 'Заказчик' переходит к просмотру раздела "Предмет предварительного отбора №2" на странице 'Уведомление об итогах рассмотрения заявок предварительного отбора'
    And 'Заказчик' проверяет, что таблица 'Сведения уведомления об итогах рассмотрения заявок' для раздела 'Предмет предварительного отбора' под номером "2" содержит 2 заявку
    And 'Заказчик' в разделе 'Предмет предварительного отбора' под номером "2" нажимает на кнопку 'Допустить всех'
    And 'Заказчик' переходит к просмотру раздела "Общие сведения об уведомлении" на странице 'Уведомление об итогах рассмотрения заявок предварительного отбора'
    And 'Заказчик' заполняет поле "Дата и время следующего окончания подачи заявок" в "HOURS" со смещением "-1" от времени публикации на странице 'Уведомление об итогах рассмотрения заявок предварительного отбора'
    And 'Заказчик' переходит к просмотру раздела "Прикрепление уведомления об итогах рассмотрения заявок предварительного отбора" на странице 'Уведомление об итогах рассмотрения заявок предварительного отбора'
    And 'Заказчик' прикрепляет файл уведомления на странице 'Уведомление об итогах рассмотрения заявок предварительного отбора'
    And 'Заказчик' нажимает на кнопку 'Подписать и опубликовать'
    And 'Заказчик' нажимает на кнопку 'Продолжить' в диалоговом окне 'Предупреждение'
    And 'Заказчик' нажимает на кнопку 'OK' в диалоговом окне 'Информация'
    Then Протокол перешел в статус "Утвержден"
    #*******************************************************************************************************************
    #
    #                 Заказчик отклоняет один из предметов предварительного отбора (первый)
    #
    #*******************************************************************************************************************
    And 'Заказчик' нажимает на ссылку в поле "Номер процедуры" на странице 'Уведомление об итогах рассмотрения заявок предварительного отбора'
    And 'Заказчик' проверяет, что открыта страница "Просмотр извещения (/Auctions/View/)"
    And 'Заказчик' переходит к просмотру раздела "Предмет предварительного отбора №1"
    And 'Заказчик' нажимает на кнопку "Завершить досрочно лот 1" для досрочного заершения лота на странице 'Просмотр извещения XXXX'
    And 'Заказчик' переходит на страницу 'Мои закупки'
    And 'Заказчик' заполняет поле 'Номер закупки' и нажимает на кнопку 'Поиск'
    And 'Заказчик' проверяет статус предмета предварительного отбора "Завершена" для "1" предмета на странице 'Мои закупки'
    And 'Заказчик' проверяет статус предмета предварительного отбора "Прием заявок" для "2" предмета на странице 'Мои закупки'
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Администратор меняет дату окончания приема заявок на текущую-2 -> [Завершен прием заявок]
    #
    #*******************************************************************************************************************
    When "Администратор" заходит в личный кабинет
    And 'Администратор' переходит на страницу "Ускорение процедур"
    And 'Администратор' выполняет поиск по номеру закупки
    And 'Администратор' проверяет статус предмета предварительного отбора "Завершена" для предмета с номером "1" без нажатия кнопки 'Обновить'
    And 'Администратор' проверяет статус предмета предварительного отбора "Прием заявок" для предмета с номером "2" без нажатия кнопки 'Обновить'
    And 'Администратор' нажимает на кнопку "Дата окончания приема заявок - Текущая-2мин." на странице 'Ускорение процедур'
    And 'Администратор' нажимает на кнопку "Обновить" на странице 'Ускорение процедур'
    And 'Администратор' проверяет статус предмета предварительного отбора "Завершен прием заявок" для предмета с номером "2" без нажатия кнопки 'Обновить'
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Заказчик публикует четвертое уведомление об итогах рассмотрения заявок предварительного отбора
    #
    #*******************************************************************************************************************
    When "Заказчик" заходит в личный кабинет
    And 'Заказчик' заполняет поле 'Номер закупки' и нажимает на кнопку 'Поиск'
    And 'Заказчик' проверяет статус предмета предварительного отбора "Завершена" для "1" предмета на странице 'Мои закупки'
    And 'Заказчик' проверяет статус предмета предварительного отбора "Завершен прием заявок" для "2" предмета на странице 'Мои закупки'
    And 'Заказчик' открывает извещение по ссылке с номером извещения в результатах поиска
    And 'Заказчик' нажимает на кнопку "Открыть четвертую форму уведомлений об итогах рассмотрения заявок"
    And 'Заказчик' проверяет, что открыта страница "Один из протоколов"
    And 'Заказчик' прикрепляет файл уведомления на странице 'Уведомление об итогах рассмотрения заявок предварительного отбора'
    And 'Заказчик' нажимает на кнопку 'Подписать и опубликовать'
    And 'Заказчик' нажимает на кнопку 'Продолжить' в диалоговом окне 'Предупреждение'
    And 'Заказчик' нажимает на кнопку 'OK' в диалоговом окне 'Информация'
    #*******************************************************************************************************************
    #
    #                 Заказчик проверяет, что статусы предметов предварительного отбора -> [Завершена]
    #
    #*******************************************************************************************************************
    And 'Заказчик' переходит на страницу 'Мои закупки'
    And 'Заказчик' заполняет поле 'Номер закупки' и нажимает на кнопку 'Поиск'
    And 'Заказчик' проверяет статус предмета предварительного отбора "Завершена" для "1" предмета на странице 'Мои закупки'
    And 'Заказчик' проверяет статус предмета предварительного отбора "Завершена" для "2" предмета на странице 'Мои закупки'
    #*******************************************************************************************************************
    #
    #                 Заказчик проверяет созданные записи о квалифицированной подрядной организации
    #
    #*******************************************************************************************************************
    And 'Заказчик' переходит на страницу "МОЯ ОРГАНИЗАЦИЯ/РЕЕСТР КВАЛИФИЦИРОВАННЫХ ПОДРЯДНЫХ ОРГАНИЗАЦИЙ" с именем URL для проверки "Реестр квалифицированных подрядных организаций"
    And 'Заказчик' заполняет поле 'Наименование перечня' значением "Предмет предварительного отбора" с текущей датой на странице 'Реестр квалифицированных подрядных организаций'
    And 'Заказчик' нажимает на кнопку "Найти" на странице 'Реестр квалифицированных подрядных организаций'
    And 'Заказчик' проверяет наличие записи о квалифицированной подрядной организации для предмета предварительного отбора под номером "1"
    And 'Заказчик' проверяет наличие записи о квалифицированной подрядной организации для предмета предварительного отбора под номером "2"
    And 'Пользователь' покидает систему
