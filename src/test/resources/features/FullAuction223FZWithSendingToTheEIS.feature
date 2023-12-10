@smoke @runTest @auction_223fz_with_sending_to_the_eis
Feature: Full Auction 223 FZ With Sending To The EIS
  #=====================================================================================================================
  # Проведение аукциона по 223-ФЗ с отправкой в ЕИС ( неконкурентный )
  # ( https://ve-lab.visualstudio.com/kontur.market/_workitems/edit/281318 )
  # В системе созданы следующие пользователи:
  # Заказчик                   - "Заказчик Юридическое лицо 1"
  # Поставщик 1                - "Поставщик Юридическое лицо 1"
  # Поставщик 2                - "Поставщик Юридическое лицо 2"
  # Администратор ( Оператор ) - "Администратор"
  #=====================================================================================================================
  Scenario: Полный электронный аукцион ( неконкурентная закупка в соответствии с нормами 223-ФЗ с отправкой в ЕИС )
    Given Параметру 'Тип теста' установлено значение "Full Auction 223FZ With Sending To The EIS"
    And Все требуемые для теста сертификаты присутствуют на компьютере
    #*******************************************************************************************************************
    #
    #                 Заказчик публикует извещение о проведении аукциона
    #                 ( неконкурентная закупка в соответствии с нормами 223-ФЗ )
    #
    #*******************************************************************************************************************
    When "Заказчик" заходит в личный кабинет
    And 'Заказчик А' открывает страницу создания извещения для неконкурентной закупки "Аукцион"
    And 'Заказчик А' заполняет сведения о процедуре: "Закупка по 223-ФЗ - Аукцион"
    #
    #                 Общие сведения о закупке
    #
    And 'Заказчик А' переходит к разделу "Общие сведения о закупке":
    And 'Заказчик А' проверяет, что поле "Способ закупки" содержит значение "Аукцион"
    And 'Заказчик А' проверяет, что поле "Тип закупки" содержит значение "Закупка в соответствии с нормами 223-ФЗ"
    And 'Заказчик А' заполняет поле 'Наименование закупки' сгенерированным именем для типа закупки "Аукцион 223-ФЗ - отпр. в ЕИС"
    And 'Заказчик А' проверяет, что флажок "Многолотовая закупка" "не установлен"
    And 'Заказчик А' проверяет, что флажок "Публикация итогового протокола" "не установлен"
    And 'Заказчик А' устанавливает флажок "Закупка проводится вследствие аварии, чрезвычайной ситуации"
    And 'Заказчик А' проверяет, что флажок "Возможность участия в процедуре без ЭЦП" "не установлен"
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
    And 'Заказчик А' заполняет дату и время "проведения аукциона" в "MONTHS" со смещением "+3" от времени публикации
    And 'Заказчик А' заполняет дату и время "подведения итогов" в "MONTHS" со смещением "+5" от времени публикации
    And 'Заказчик А' заполняет поле со счётчиком "Регламентный срок заключения договора - От" значением " "
    And 'Заказчик А' заполняет поле со счётчиком "Регламентный срок заключения договора - До" значением " "
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
    And 'Заказчик А' заполняет поле со счётчиком "Количество" значением "1"
    #             ______________________________________________________
    #            |    Вкладка [Сведения об аукционе]
    #            |______________________________________________________
    And 'Заказчик А' переключается на вкладку лота "Сведения об аукционе"
    #                 Сведения об аукционе
    And 'Заказчик А' сбрасывает флажок "Аукцион с продлением"
    And 'Заказчик А' заполняет дату и время "окончания аукциона" в "MONTHS" со смещением "+4" от времени публикации
    #             ______________________________________________________
    #            |    Вкладка [Сведения о заказчиках]
    #            |______________________________________________________
    And 'Заказчик А' переключается на вкладку лота "Сведения о заказчиках"
    #                 Сведения о заказчиках, подписывающих договоры
    And 'Заказчик А' проверяет, что флажок "Несколько заказчиков (совместная закупка)" "не установлен"
    And 'Заказчик А' проверяет, что флажок "Договор заключается с несколькими участниками" "не установлен"
    #                 Изменение информации о заказчике
    And 'Заказчик А' выбирает второе по счёту значение в списке для поля 'Заказчик'
    And 'Заказчик А' заполняет поле со счётчиком "Обеспечение заявки (%)" значением "1"
    And 'Заказчик А' заполняет поле со счётчиком "Обеспечение договора (%)" значением "1"
    And 'Заказчик А' нажимает кнопку "Заполнить юридическим адресом"
    #             ______________________________________________________
    #            |    Вкладка [Документы лота]
    #            |______________________________________________________
    #             ______________________________________________________
    #            |    Вкладка [Дополнительные параметры лота]
    #            |______________________________________________________
    #
    #
    #                 Сведения о лоте № 1 - Конец раздела
    #-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
    #
    #                 Документы
    #
    And 'Заказчик А' прикрепляет необходимые документы
    #
    #                 Сохранение черновика извещения
    #
    And 'Заказчик А' сохраняет черновик извещения
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
    #-------------------------------------------------------------------------------------------------------------------
    #                 Проверка входящего уведомления [Уведомление об опубликовании закупки № ]
    #-------------------------------------------------------------------------------------------------------------------
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
    And 'Администратор' связывает опубликованную на площадке закупку 'Неконкурентная закупка - Аукцион' с закупкой в ЕИС
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Заказчик проверяет наличие номера ЕИС на опубликованном извещении
    #
    #*******************************************************************************************************************
    When "Заказчик" заходит в личный кабинет
    And 'Заказчик' заполняет поле 'Номер закупки' и нажимает на кнопку 'Поиск'
    And 'Заказчик' открывает извещение по ссылке с номером извещения в результатах поиска
    And 'Заказчик' проверяет, что поле "Номер закупки в ЕИС" на странице 'Просмотр извещения' содержит не пустое значение
    And 'Заказчик' проверяет статус закупки "Опубликована" и статус лота "Прием заявок" для "1" лотов
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Поставщик 1 подает заявку на участие в полном электронном аукционе
    #                      ( неконкурентная закупка в соответствии с нормами 223-ФЗ )
    #
    #*******************************************************************************************************************
    When "Поставщик Юридическое лицо 1" заходит в личный кабинет
    #                 Лот № 1
    And 'Поставщик' переходит к списку закупок
    And 'Поставщик' находит созданную закупку
    And 'Поставщик' переходит по ссылке с номером закупки в результатах поиска
    And 'Поставщик' ожидает окончания загрузки страницы с деталями закупки 'Закупка № XXXX'
    And 'Поставщик' нажимает на кнопку 'Подать заявку' для лота № "1"
    And 'Поставщик' заполняет поле заявки 'Номер контактного телефона'
    And 'Поставщик' прикрепляет документ в разделе заявки 'Сведения о товаре'
    And 'Поставщик' заполняет поле заявки 'Страна происхождения товара' значением 'Российская федерация'
    And 'Поставщик' № "1" подтверждает заявку на участие для лота № "1"
    And 'Поставщик' проверяет статус заявки "Утверждена оператором"
    #-------------------------------------------------------------------------------------------------------------------
    #                 Проверка уведомления [Подтверждение получения заявки на участие в закупке № X по лоту № X]
    #-------------------------------------------------------------------------------------------------------------------
    And 'Поставщик' проверяет уведомление "Подтверждение получения заявки на участие в закупке № %s по лоту № 1"
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Поставщик 2 подает заявку на участие в полном электронном аукционе
    #                      ( неконкурентная закупка в соответствии с нормами 223-ФЗ )
    #
    #*******************************************************************************************************************
    When "Поставщик Юридическое лицо 2" заходит в личный кабинет
    #                 Лот № 1
    And 'Поставщик' переходит к списку закупок
    And 'Поставщик' находит созданную закупку
    And 'Поставщик' переходит по ссылке с номером закупки в результатах поиска
    And 'Поставщик' ожидает окончания загрузки страницы с деталями закупки 'Закупка № XXXX'
    And 'Поставщик' нажимает на кнопку 'Подать заявку' для лота № "1"
    And 'Поставщик' заполняет поле заявки 'Номер контактного телефона'
    And 'Поставщик' прикрепляет документ в разделе заявки 'Сведения о товаре'
    And 'Поставщик' заполняет поле заявки 'Страна происхождения товара' значением 'Российская федерация'
    And 'Поставщик' № "2" подтверждает заявку на участие для лота № "1"
    And 'Поставщик' проверяет статус заявки "Утверждена оператором"
    #-------------------------------------------------------------------------------------------------------------------
    #                 Проверка уведомления [Подтверждение получения заявки на участие в закупке № X по лоту № X]
    #-------------------------------------------------------------------------------------------------------------------
    And 'Поставщик' проверяет уведомление "Подтверждение получения заявки на участие в закупке № %s по лоту № 1"
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Администратор меняет дату и время завершения приема заявок на текущие -> [Рассмотрение заявок]
    #
    #*******************************************************************************************************************
    When "Администратор" заходит в личный кабинет
    And 'Администратор' переходит на страницу "Ускорение процедур"
    And 'Администратор' выполняет поиск по номеру закупки
    And 'Администратор' выполняет ускорение процедур
    And 'Администратор' проверяет статус лота "Рассмотрение заявок" для лота с номером "1"
    And 'Администратор' переходит на страницу "Системные задачи"
    And 'Администратор' выбирает "Обновить статусы лотов аукционов"
    And 'Пользователь' покидает систему
    #-------------------------------------------------------------------------------------------------------------------
    #                 Заказчик проверяет статус закупки и статус лота
    #-------------------------------------------------------------------------------------------------------------------
    When "Заказчик" заходит в личный кабинет
    And 'Заказчик' заполняет поле 'Номер закупки' и нажимает на кнопку 'Поиск'
    And 'Заказчик' открывает извещение по ссылке с номером извещения в результатах поиска
    And 'Заказчик' проверяет статус закупки "Опубликована" и статус лота "Рассмотрение заявок" для "1" лотов
    #*******************************************************************************************************************
    #
    #                 Заказчик прубликует протокол рассмотрения заявок
    #
    #*******************************************************************************************************************
    And 'Заказчик' открывает извещение по ссылке с номером извещения в результатах поиска
    And 'Заказчик' переходит к просмотру раздела "Сведения о протоколах"
    And 'Заказчик' нажимает на кнопку "Открыть форму протокола рассмотрения заявок"
    And 'Заказчик' проверяет статус протокола - 'Формирование'
    And 'Заказчик' нажимает на кнопку 'Допустить всех' для каждого из "1" лотов
    And 'Заказчик' нажимает на кнопку 'Сформировать и прикрепить' для каждого из "1" лотов
    And 'Заказчик' проверяет наличие дубликатов в списке шаблонов протоколов
    And 'Заказчик' проверяет прикрепленные файлы "1" протокола рассмотрения заявок
    And 'Заказчик' публикует протокол рассмотрения заявок
    Then Протокол перешел в статус "Утвержден"
    #-------------------------------------------------------------------------------------------------------------------
    #                 Проверка входящего уведомления [Уведомление о публикации протокола рассмотрения заявок закупки №]
    #-------------------------------------------------------------------------------------------------------------------
    And 'Заказчик' проверяет входящее уведомление "Уведомление о публикации протокола рассмотрения заявок закупки № %s"
    #-------------------------------------------------------------------------------------------------------------------
    #                 Заказчик проверяет статус закупки и статус лота
    #-------------------------------------------------------------------------------------------------------------------
    And 'Заказчик' переходит на страницу 'Мои закупки' и ищет опубликованное извещение
    And 'Заказчик' открывает извещение по ссылке с номером извещения в результатах поиска
    And 'Заказчик' проверяет статус закупки "Опубликована" и статус лота "Ожидает начала торгов" для "1" лотов
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Администратор меняет дату и время начала торгов на текущие -> [Идут торги]
    #
    #*******************************************************************************************************************
    When "Администратор" заходит в личный кабинет
    And 'Администратор' переходит на страницу "Ускорение процедур"
    And 'Администратор' выполняет поиск по номеру закупки
    And 'Администратор' проверяет статус лота "Ожидает начала торгов" для лота с номером "1" без нажатия кнопки 'Обновить'
    And 'Администратор' выполняет ускорение процедур
    And 'Администратор' переходит на страницу "Системные задачи"
    And 'Администратор' выбирает "Обновить статусы лотов аукционов"
    And 'Администратор' переходит на страницу "Ускорение процедур"
    And 'Администратор' выполняет поиск по номеру закупки
    And 'Администратор' получает значение поля 'Дата начала торгов'
    And 'Администратор' проверяет статус лота "Идут торги" для лота с номером "1" без нажатия кнопки 'Обновить'
    And 'Администратор' проверяет статус торга "Идут торги"
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Ход торгов
    #
    #*******************************************************************************************************************
    #-------------------------------------------------------------------------------------------------------------------
    #                 Поставщик 2
    #-------------------------------------------------------------------------------------------------------------------
    When "Поставщик Юридическое лицо 2" заходит в личный кабинет
    And 'Поставщик' переходит к списку закупок
    And 'Поставщик' находит созданную закупку
    And 'Поставщик' проверяет количество лотов "1" в закупке
    #                 Лот № 1
    And 'Поставщик' открывает лот с номером "1" в закупке
    And 'Поставщик' нажимает на кнопку 'Информация о торгах' для лота с номером "1" в закупке
    And 'Поставщик' нажимает на кнопку 'Подача ценовых предложений'
    And 'Поставщик' вводит цену "4,8" в поле Предложение цены
    And 'Поставщик' подает ценовое предложение
    Then Ценовое предложение отобразилось в списке лучших ценовых предложений "4,8"
    And 'Поставщик' завершает ход торгов
    And 'Пользователь' покидает систему
    #-------------------------------------------------------------------------------------------------------------------
    #                 Поставщик 1
    #-------------------------------------------------------------------------------------------------------------------
    When "Поставщик Юридическое лицо 1" заходит в личный кабинет
    And 'Поставщик' переходит к списку закупок
    And 'Поставщик' находит созданную закупку
    And 'Поставщик' проверяет количество лотов "1" в закупке
    #                 Лот № 1
    And 'Поставщик' открывает лот с номером "1" в закупке
    And 'Поставщик' нажимает на кнопку 'Информация о торгах' для лота с номером "1" в закупке
    And 'Поставщик' нажимает на кнопку 'Подача ценовых предложений'
    And 'Поставщик' вводит цену "4,7" в поле Предложение цены
    And 'Поставщик' подает ценовое предложение
    Then Ценовое предложение отобразилось в списке лучших ценовых предложений "4,7"
    And 'Поставщик' завершает ход торгов
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Администратор меняет дату и время подведения итогов на текущие -> [Подведение итогов]
    #
    #*******************************************************************************************************************
    When "Администратор" заходит в личный кабинет
    And 'Администратор' переходит на страницу "Ускорение процедур"
    And 'Администратор' выполняет поиск по номеру закупки
    And 'Администратор' проверяет статус лота "Идут торги" для лота с номером "1" без нажатия кнопки 'Обновить'
    And 'Администратор' проверяет статус торга "Идут торги"
    And 'Администратор' завершает торги
    And 'Администратор' проверяет статус лота "Подведение итогов" для лота с номером "1"
    And 'Администратор' проверяет статус торга "Закрыт"
    And 'Пользователь' покидает систему
    #-------------------------------------------------------------------------------------------------------------------
    #                 Заказчик проверяет статус закупки и статус лота
    #-------------------------------------------------------------------------------------------------------------------
    When "Заказчик" заходит в личный кабинет
    And 'Заказчик' заполняет поле 'Номер закупки' и нажимает на кнопку 'Поиск'
    And 'Заказчик' открывает извещение по ссылке с номером извещения в результатах поиска
    And 'Заказчик' проверяет статус закупки "Опубликована" и статус лота "Подведение итогов" для "1" лотов
    And 'Заказчик' открывает извещение по ссылке с номером извещения в результатах поиска
    #*******************************************************************************************************************
    #
    #                 Заказчик публикует протокол проведения аукциона
    #
    #*******************************************************************************************************************
    And 'Заказчик' нажимает на кнопку "Открыть форму протокола проведения аукциона"
    And 'Заказчик' проверяет лучшую цену "4,7" в колонке 'Лучшее ценовое предложение' для "1" лота
    And 'Заказчик' проверяет, что открыта страница "Один из протоколов"
    And 'Заказчик' нажимает на кнопку 'Сформировать и прикрепить' для каждого из "1" лотов
    And 'Заказчик' проверяет наличие дубликатов в списке шаблонов протоколов
    And 'Заказчик' проверяет прикрепленные файлы "1" протокола рассмотрения заявок
    And 'Заказчик' публикует протокол проведения электронного аукциона
    Then Протокол перешел в статус "Утвержден"
    #-------------------------------------------------------------------------------------------------------------------
    #                 Заказчик проверяет статус закупки и статус лота
    #-------------------------------------------------------------------------------------------------------------------
    And 'Заказчик' переходит на страницу 'Мои закупки' и ищет опубликованное извещение
    And 'Заказчик' открывает извещение по ссылке с номером извещения в результатах поиска
    And 'Заказчик' проверяет статус закупки "Опубликована" и статус лота "Заключение договоров" для "1" лотов
    #-------------------------------------------------------------------------------------------------------------------
    #                 Проверка входящего уведомления [Уведомление о публикации протокола проведения аукциона закупки № ]
    #-------------------------------------------------------------------------------------------------------------------
    And 'Заказчик' проверяет входящее уведомление "Уведомление о публикации протокола проведения аукциона закупки № %s"
    #*******************************************************************************************************************
    #
    #                 Заказчик направляет договор Поставщику 1
    #
    #*******************************************************************************************************************
    And 'Заказчик' переходит на страницу 'Мои закупки' и ищет опубликованное извещение
    And 'Заказчик' переходит по ссылке "Направьте договор" для лота "1"
    And 'Заказчик' нажимает на кнопку 'Продолжить' в диалоговом окне 'Предупреждение'
    And 'Пользователь' ожидает "60" секунд
    And 'Заказчик' проверяет, что открыта страница "Заказчик-Направьте договор"
    And 'Заказчик' переходит к просмотру раздела договора "Сведения о договоре"
    And 'Заказчик' проверяет статус договора "Договор формируется"
    And 'Заказчик' переходит к просмотру раздела договора "Документы"
    And 'Заказчик' прикрепляет файл с проектом договора
    And 'Заказчик' проверяет дату прикрепления файла с проектом договора
    And 'Заказчик' проверяет статус договора "Договор формируется"
    And 'Заказчик' направляет договор Поставщику
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Поставщик 1 подписывает договор с Заказчиком
    #
    #*******************************************************************************************************************
    When "Поставщик Юридическое лицо 1" заходит в личный кабинет
    And 'Поставщик' переходит на страницу 'Мои договоры'
    And 'Поставщик' производит поиск договора
    And 'Поставщик' принимает и подписывает договор
    #-------------------------------------------------------------------------------------------------------------------
    #                 Проверка уведомления [Извещение об отправке на подпись договора по закупке № X (лот № X)]
    #-------------------------------------------------------------------------------------------------------------------
    And 'Поставщик' проверяет уведомление "Извещение об отправке на подпись договора по закупке  № %s (лот № 1)"
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Администратор меняет дату и время начала регламентного срока договора на текущую
    #
    #*******************************************************************************************************************
    When "Администратор" заходит в личный кабинет
    And 'Администратор' переходит на страницу "Ускорение процедур"
    And 'Администратор' выполняет поиск по номеру закупки
    And 'Администратор' проверяет статус лота "Заключение договоров" для лота с номером "1" без нажатия кнопки 'Обновить'
    And 'Администратор' проверяет статус торга "Закрыт"
    And 'Администратор' нажимает кнопку "Крайний срок заключения договора - Текущая" в строке 1 таблицы 'Управление договорами'
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Заказчик подписывает договор с Поставщиком 1
    #
    #*******************************************************************************************************************
    When "Заказчик" заходит в личный кабинет
    And 'Заказчик' заполняет поле 'Номер закупки' и нажимает на кнопку 'Поиск'
    And 'Заказчик' переходит по ссылке "Подпишите договор" для лота "1"
    And 'Заказчик' проверяет, что открыта страница "Заказчик-Подпишите договор"
    And 'Заказчик' переходит к просмотру раздела договора "Сведения о договоре"
    And 'Заказчик' проверяет статус договора "Договор подписан участником"
    And 'Заказчик' подписывает договор
    #-------------------------------------------------------------------------------------------------------------------
    #                 Заказчик проверяет статус закупки и статус лота
    #-------------------------------------------------------------------------------------------------------------------
    #                 Лот № 1
    And 'Заказчик' переходит на страницу 'Мои закупки' и ищет опубликованное извещение
    And 'Заказчик' открывает извещение по ссылке с номером извещения в результатах поиска
    And 'Заказчик' проверяет статус закупки "Завершена" и статус лота "Завершена" для "1" лотов
    #-------------------------------------------------------------------------------------------------------------------
    #                 Проверка входящего уведомления [Извещение о подписании участником договора по закупке № ]
    #-------------------------------------------------------------------------------------------------------------------
    And 'Заказчик' проверяет входящие уведомления "Извещение о подписании участником договора по закупке № %s (лот № 1)" в количестве "1" сообщений
    And 'Пользователь' покидает систему
