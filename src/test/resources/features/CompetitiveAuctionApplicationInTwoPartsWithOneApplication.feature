@smoke @runTest @comp_auction_2_parts_app_one_application
Feature: Competitive Auction Application In Two Parts - One Application
  #=====================================================================================================================
  # Проведение конкурентного аукциона ( заявка в двух частях ) - 1 заявка
  #( https://ve-lab.visualstudio.com/kontur.market/_workitems/edit/344145 )
  # В системе созданы следующие пользователи:
  # Заказчик                   - "Заказчик Юридическое лицо 1"
  # Поставщик 1                - "Поставщик Юридическое лицо 1"
  # Администратор ( Оператор ) - "Администратор"
  #=====================================================================================================================
  Scenario: Проведение конкурентного аукциона ( заявка в двух частях ) - 1 заявка
    Given Параметру 'Тип теста' установлено значение "Competitive Auction Application In Two Parts One Application"
    And Все требуемые для теста сертификаты присутствуют на компьютере
    #-------------------------------------------------------------------------------------------------------------------
    #                 Заказчик публикует извещение о проведении конкурентного аукциона ( заявка в двух частях )
    #-------------------------------------------------------------------------------------------------------------------
    When "Заказчик" заходит в личный кабинет
    And 'Заказчик А' открывает страницу создания извещения для конкурентной закупки "Аукцион (заявка в двух частях)"
    And 'Заказчик А' заполняет сведения о процедуре: "Конкурентная закупка по 223ФЗ - Аукцион (заявка в двух частях)"
    #
    #                 Общие сведения о закупке
    #
    And 'Заказчик А' переходит к разделу "Общие сведения о закупке":
    And 'Заказчик А' проверяет, что поле "Способ закупки" содержит значение "Аукцион (заявка в двух частях)"
    And 'Заказчик А' проверяет, что поле "Тип закупки" содержит значение "Закупка в соответствии с нормами 223-ФЗ (Конкурентная)"
    And 'Заказчик А' заполняет поле 'Наименование закупки' сгенерированным именем для типа закупки "Аукцион (Конкурентный заявка в двух частях)"
    And 'Заказчик А' проверяет, что флажок "Многолотовая закупка" "не установлен"
    And 'Заказчик А' проверяет, что флажок "Публикация итогового протокола" "не установлен"
    And 'Заказчик А' устанавливает флажок "Закупка проводится вследствие аварии, чрезвычайной ситуации"
    #
    #                 Сведения о порядке подачи заявок
    #
    And 'Заказчик А' переходит к разделу "Сведения о порядке подачи заявок":
    And 'Заказчик А' заполняет дату и время "начала подачи заявок" в "HOURS" со смещением "+0" от времени публикации
    And 'Заказчик А' заполняет дату и время "окончания подачи заявок" в "MONTHS" со смещением "+1" от времени публикации
    #
    #                 Сведения о порядке работы комиссии
    #
    And 'Заказчик А' переходит к разделу "Сведения о порядке работы комиссии":
    And 'Заказчик А' заполняет дату и время "рассмотрения заявок" в "MONTHS" со смещением "+2" от времени публикации
    And 'Заказчик А' заполняет дату и время "проведения аукциона" в "MONTHS" со смещением "+3" от времени публикации
    And 'Заказчик А' заполняет дату и время "подведения итогов" в "MONTHS" со смещением "+5" от времени публикации
    And 'Заказчик А' проверяет, что поле "Регламентный срок заключения договора - От" содержит значение "10"
    And 'Заказчик А' проверяет, что поле "Регламентный срок заключения договора - До" содержит значение "20"
    #
    #                 Сведения о предоставлении документации
    #
    And 'Заказчик А' переходит к разделу "Сведения о предоставлении документации":

    And 'Заказчик А' проверяет дату "Срок предоставления документации (МСК) От" в "MONTHS" со смещением "+0" от времени публикации
    And 'Заказчик А' проверяет дату "Срок предоставления документации (МСК) До" в "MONTHS" со смещением "+1" от времени публикации
    And 'Заказчик А' проверяет, что поле "Место предоставления документации" содержит значение "Электронная площадка РТС-тендер"
    And 'Заказчик А' проверяет, что поле "Порядок предоставления документации" содержит значение "В электронной форме"
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
    #                 Сведения о лоте № 1
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
    And 'Заказчик А' заполняет поле со счётчиком "Обеспечение договора (%)" значением "1"
    And 'Заказчик А' заполняет поле "Место поставки товара, выполнения работ, оказания услуг" значением "Место 1"
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
    And 'Администратор' связывает опубликованную на площадке закупку 'Конкурентная закупка - Аукцион заявка в двух частях' с закупкой в ЕИС
    And 'Пользователь' покидает систему
    #-------------------------------------------------------------------------------------------------------------------
    #                 Заказчик проверяет наличие номера ЕИС на опубликованном извещении
    #-------------------------------------------------------------------------------------------------------------------
    When "Заказчик" заходит в личный кабинет
    And 'Заказчик' заполняет поле 'Номер закупки' и нажимает на кнопку 'Поиск'
    And 'Заказчик' открывает извещение по ссылке с номером извещения в результатах поиска
    And 'Заказчик' проверяет, что поле "Номер закупки в ЕИС" на странице 'Просмотр извещения' содержит не пустое значение
    And 'Заказчик' проверяет статус закупки "Опубликована" и статус лота "Прием заявок" для "1" лотов
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Поставщик подает заявку на участие в полном электронном конкурсе
    #                      ( конкурентная закупка в соответствии с нормами 223-ФЗ )
    #
    #*******************************************************************************************************************
    When "Поставщик Юридическое лицо 1" заходит в личный кабинет
    And 'Поставщик' переходит к списку закупок
    And 'Поставщик' находит созданную закупку
    And 'Поставщик' проверяет количество строк 1 в таблице 'Закупки' на странице 'Поиск закупок'
    And 'Поставщик' переходит по ссылке с номером закупки в результатах поиска
    And 'Поставщик' ожидает окончания загрузки страницы с деталями закупки 'Закупка № XXXX'
    And 'Поставщик' нажимает на кнопку 'Подать заявку' для лота № "1"
    And 'Поставщик' прикрепляет документ в разделе заявки 'Первая часть заявки'
    And 'Поставщик' заполняет поле заявки 'Страна происхождения товара' значением 'Российская федерация'
    And 'Поставщик' заполняет поле заявки 'Номер контактного телефона'
    And 'Поставщик' № "1" подтверждает заявку на участие для лота № "1"
    And 'Поставщик' проверяет статус заявки "Утверждена оператором"
    #
    #                 Проверка уведомления [Подтверждение получения заявки на участие в закупке № X по лоту № X]
    #
    And 'Поставщик' проверяет уведомление "Подтверждение получения заявки на участие в закупке № %s по лоту № 1"
    And 'Пользователь' покидает систему
    #-------------------------------------------------------------------------------------------------------------------
    #                 Администратор меняет дату и время завершения приема заявок на текущие -> [Рассмотрение заявок]
    #-------------------------------------------------------------------------------------------------------------------
    When "Администратор" заходит в личный кабинет
    And 'Администратор' переходит на страницу "Ускорение процедур"
    And 'Администратор' выполняет поиск по номеру закупки
    And 'Администратор' выполняет ускорение процедур
    And 'Администратор' проверяет статус лота "Рассмотрение первых частей заявок" для лота с номером "1"
    And 'Администратор' переходит на страницу "Системные задачи"
    And 'Администратор' выбирает "Обновить статусы лотов аукционов"
    And 'Пользователь' покидает систему
    #-------------------------------------------------------------------------------------------------------------------
    #                 Заказчик публикует протокол рассмотрения заявок
    #-------------------------------------------------------------------------------------------------------------------
    When "Заказчик" заходит в личный кабинет
    And 'Заказчик' заполняет поле 'Номер закупки' и нажимает на кнопку 'Поиск'
    And 'Заказчик' открывает извещение по ссылке с номером извещения в результатах поиска
    And 'Заказчик' проверяет статус закупки "Опубликована" и статус лота "Рассмотрение первых частей заявок" для "1" лотов
    And 'Заказчик' переходит по ссылке "Рассмотрите заявки" для лота "1"
    And 'Заказчик' нажимает на кнопку 'Допустить всех' для каждого из "1" лотов
    And 'Заказчик' устанавливает значение переключателя 'Заключить договор с единственным участником' в положение 'Да'
    And 'Заказчик' нажимает на кнопку 'Сформировать и прикрепить' для каждого из "1" лотов
    And 'Заказчик' проверяет наличие дубликатов в списке шаблонов протоколов
    And 'Заказчик' проверяет прикрепленные файлы "1" протокола рассмотрения заявок
    And 'Заказчик' публикует протокол рассмотрения заявок
    Then Протокол перешел в статус "Утвержден"
    And 'Заказчик' проверяет, что в имени 1 столбца в таблице 'Сведения протокола рассмотрения единственной заявки' содержится строка "Порядковый номер заявки"
    And 'Заказчик' проверяет, что в имени 2 столбца в таблице 'Сведения протокола рассмотрения единственной заявки' содержится строка "Дата и время регистрации заявки"
    And 'Заказчик' проверяет, что в имени 3 столбца в таблице 'Сведения протокола рассмотрения единственной заявки' содержится строка "Наименование организации"
    And 'Заказчик' проверяет, что в имени 4 столбца в таблице 'Сведения протокола рассмотрения единственной заявки' содержится строка "Решение комиссии"
    And 'Заказчик' проверяет, что в имени 5 столбца в таблице 'Сведения протокола рассмотрения единственной заявки' содержится строка "Первая часть заявки"
    And 'Заказчик' проверяет, что в имени 6 столбца в таблице 'Сведения протокола рассмотрения единственной заявки' содержится строка "Вторая часть заявки"
    And 'Заказчик' проверяет, что в имени 7 столбца в таблице 'Сведения протокола рассмотрения единственной заявки' содержится строка "Сведения об участнике закупки"
    And 'Заказчик' проверяет, что в имени 8 столбца в таблице 'Сведения протокола рассмотрения единственной заявки' содержится строка "Субъект МСП"
    And 'Заказчик' проверяет, что в имени 9 столбца в таблице 'Сведения протокола рассмотрения единственной заявки' содержится строка "Проверить участника"
    And 'Заказчик' проверяет, что в имени 10 столбца в таблице 'Сведения протокола рассмотрения единственной заявки' содержится строка "Запрос на разъяснение заявки"
    #*******************************************************************************************************************
    #
    #                 Заказчик проверяет первую часть заявки
    #
    #*******************************************************************************************************************
    And 'Заказчик' открывает 'Первая часть заявки' для 1 участника в таблице 'Сведения протокола рассмотрения единственной заявки'
    And 'Заказчик' проверяет значение поля "Номер закупки" на странице печатной формы 'Заявка на участие в ...'
    And 'Заказчик' проверяет значение поля "Номер закупки в ЕИС" на странице печатной формы 'Заявка на участие в ...'
    And 'Заказчик' проверяет значение поля "Наименование закупки" на странице печатной формы 'Заявка на участие в ...'
    And 'Заказчик' проверяет, что поле "Способ закупки" содержит значение "Аукцион (заявка в двух частях)" на странице печатной формы 'Заявка на участие в ...'
    And 'Заказчик' проверяет, что поле "Организация, осуществляющая закупку" содержит значение "ООО Автотестеры 223" на странице печатной формы 'Заявка на участие в ...'
    And 'Заказчик' проверяет, что поле "Номер лота" содержит значение "1" на странице печатной формы 'Заявка на участие в ...'
    And 'Заказчик' проверяет, что поле "Наименование предмета договора" содержит значение "Лот 1" на странице печатной формы 'Заявка на участие в ...'
    And 'Заказчик' проверяет, что поле "Начальная - максимальная - цена" содержит значение "5,00 (Российский рубль)" на странице печатной формы 'Заявка на участие в ...'
    And 'Заказчик' проверяет, что поле "Размер обеспечения заявки" содержит значение "0,00 (Российский рубль)" на странице печатной формы 'Заявка на участие в ...'
    And 'Заказчик' проверяет, что поле "Страна происхождения товара" содержит значение "Российская Федерация" на странице печатной формы 'Заявка на участие в ...'
    And 'Заказчик' проверяет, что поле "Наименование документа" содержит значение "1" на странице печатной формы 'Заявка на участие в ...'
    And 'Заказчик' проверяет, что поле "Номер в реестре аккредитованных участников закупки" отсутствует на странице печатной формы 'Заявка на участие в ...'
    And 'Заказчик' проверяет, что поле "Фирменное наименование" отсутствует на странице печатной формы 'Заявка на участие в ...'
    And 'Заказчик' проверяет, что поле "Юридический адрес" отсутствует на странице печатной формы 'Заявка на участие в ...'
    And 'Заказчик' проверяет, что поле "Почтовый адрес" отсутствует на странице печатной формы 'Заявка на участие в ...'
    And 'Заказчик' проверяет, что поле "Номер контактного телефона" отсутствует на странице печатной формы 'Заявка на участие в ...'
    And 'Заказчик' проверяет, что поле "ИНН" отсутствует на странице печатной формы 'Заявка на участие в ...'
    And 'Пользователь' закрывает все дополнительные окна в приложении
    #*******************************************************************************************************************
    #
    #                 Заказчик проверяет вторую часть заявки
    #
    #*******************************************************************************************************************
    And 'Заказчик' открывает 'Вторая часть заявки' для 1 участника в таблице 'Сведения протокола рассмотрения единственной заявки'
    And 'Заказчик' проверяет значение поля "Номер закупки" на странице печатной формы 'Вторая часть заявки'
    And 'Заказчик' проверяет значение поля "Номер закупки в ЕИС" на странице печатной формы 'Вторая часть заявки'
    And 'Заказчик' проверяет значение поля "Наименование закупки" на странице печатной формы 'Вторая часть заявки'
    And 'Заказчик' проверяет, что поле "Способ закупки" содержит значение "Аукцион (заявка в двух частях)" на странице печатной формы 'Вторая часть заявки'
    And 'Заказчик' проверяет, что поле "Организация, осуществляющая закупку" содержит значение "ООО Автотестеры 223" на странице печатной формы 'Вторая часть заявки'
    And 'Заказчик' проверяет, что поле "Номер лота" содержит значение "1" на странице печатной формы 'Вторая часть заявки'
    And 'Заказчик' проверяет, что поле "Наименование предмета договора" содержит значение "Лот 1" на странице печатной формы 'Вторая часть заявки'
    And 'Заказчик' проверяет, что поле "Начальная - максимальная - цена" содержит значение "5,00 (Российский рубль)" на странице печатной формы 'Вторая часть заявки'
    And 'Заказчик' проверяет, что поле "Размер обеспечения заявки" содержит значение "0,00 (Российский рубль)" на странице печатной формы 'Вторая часть заявки'
    And 'Заказчик' проверяет, что поле 'Номер в реестре аккредитованных участников закупки' содержит значение, которое соответствует поставщику "ПОСТАВЩИК1ALL223 ЮРИДИЧЕСКОЕ ЛИЦО" на странице печатной формы 'Вторая часть заявки'
    And 'Заказчик' проверяет, что поле "Фирменное наименование" содержит значение "ООО Автотестеры 223" на странице печатной формы 'Заявка на участие в ...'
    And 'Заказчик' проверяет, что поле "Номер контактного телефона" содержит значение "+7(978)1111111(111)" на странице печатной формы 'Заявка на участие в ...'
    And 'Заказчик' проверяет, что поле "ИНН" содержит значение "3872478760" на странице печатной формы 'Заявка на участие в ...'
    And 'Пользователь' закрывает все дополнительные окна в приложении
    #*******************************************************************************************************************
    #
    #                 Заказчик проверяет печатную форму сведения об участнике закупки для первого участника
    #
    #*******************************************************************************************************************
    And 'Заказчик' открывает 'Сведения об участнике закупки' для 1 участника в таблице 'Сведения протокола рассмотрения единственной заявки'
    And 'Заказчик' проверяет, что поле 'Номер в реестре аккредитованных участников закупки' содержит значение, которое соответствует поставщику "ПОСТАВЩИК1ALL223 ЮРИДИЧЕСКОЕ ЛИЦО" на странице печатной формы 'Сведения об Участнике закупки'
    And 'Заказчик' проверяет, что поле "Дата прекращения действия аккредитации участника" содержит значение "" на странице печатной формы 'Сведения об Участнике закупки'
    And 'Заказчик' проверяет, что поле "Статус" содержит значение "Аккредитован" на странице печатной формы 'Сведения об Участнике закупки'
    And 'Заказчик' проверяет, что поле "Наименование" содержит значение "ООО Автотестеры 223" на странице печатной формы 'Сведения об Участнике закупки'
    And 'Заказчик' проверяет, что поле "Страна" содержит значение "Российская Федерация" на странице печатной формы 'Сведения об Участнике закупки'
    And 'Заказчик' проверяет, что поле "ИНН" содержит значение "3872478760" на странице печатной формы 'Сведения об Участнике закупки'
    And 'Заказчик' проверяет, что поле "ОГРН" содержит значение "1787525340051" на странице печатной формы 'Сведения об Участнике закупки'
    And 'Заказчик' проверяет, что поле "КПП" содержит значение "313039793" на странице печатной формы 'Сведения об Участнике закупки'
    And 'Заказчик' проверяет, что поле "Дополнительные адреса электронной почты" содержит значение "-" на странице печатной формы 'Сведения об Участнике закупки'
    And 'Заказчик' проверяет, что поле "Телефон организации" содержит значение "+7(978)1111111(111)" на странице печатной формы 'Сведения об Участнике закупки'
    And 'Заказчик' проверяет, что поле "Факс" содержит значение "" на странице печатной формы 'Сведения об Участнике закупки'
    And 'Заказчик' проверяет, что поле "Расчетный счет" содержит значение "22222222222222222222" на странице печатной формы 'Сведения об Участнике закупки'
    And 'Заказчик' проверяет, что поле "Лицевой счет" содержит значение "" на странице печатной формы 'Сведения об Участнике закупки'
    And 'Заказчик' проверяет, что поле "Корреспондентский счет" содержит значение "" на странице печатной формы 'Сведения об Участнике закупки'
    And 'Заказчик' проверяет, что поле "Бик" содержит значение "044525607" на странице печатной формы 'Сведения об Участнике закупки'
    And 'Заказчик' проверяет, что поле "Название банка" содержит значение "РНКБ (ОАО)" на странице печатной формы 'Сведения об Участнике закупки'
    And 'Заказчик' проверяет, что поле "Адрес банка" содержит значение "127994, МОСКВА, УЛ.КРАСНОПРОЛЕТАРСКАЯ,9 СТР.5, (495)2329002,2329000" на странице печатной формы 'Сведения об Участнике закупки'
    And 'Заказчик' проверяет, что поле "ФИО лица, подписавшего заявку на аккредитацию" содержит значение "ПОСТАВЩИК1ALL223 ЮРИДИЧЕСКОЕ ЛИЦО" на странице печатной формы 'Сведения об Участнике закупки'
    And 'Заказчик' проверяет, что поле "Адрес электронной почты лица, подписавшего заявку на аккредитацию" содержит значение "" на странице печатной формы 'Сведения об Участнике закупки'
    And 'Заказчик' проверяет, что поле "Телефон лица, подписавшего заявку на аккредитацию" содержит значение "+7(978)1111111(111)" на странице печатной формы 'Сведения об Участнике закупки'
    And 'Заказчик' проверяет, что поле "ФИО лица, подавшем заявку на участие" содержит значение "ПОСТАВЩИК1ALL223 ЮРИДИЧЕСКОЕ ЛИЦО" на странице печатной формы 'Сведения об Участнике закупки'
    And 'Заказчик' проверяет, что поле "Адрес электронной почты лица, подавшем заявку на участие" содержит значение "konturtester1@gmail.com" на странице печатной формы 'Сведения об Участнике закупки'
    And 'Заказчик' проверяет, что поле "Телефон лица, подавшем заявку на участие" содержит значение "+7(978)1111111(111)" на странице печатной формы 'Сведения об Участнике закупки'
    And 'Пользователь' закрывает все дополнительные окна в приложении
    #-------------------------------------------------------------------------------------------------------------------
    #                 Заказчик переходин на страницу закупки
    #-------------------------------------------------------------------------------------------------------------------
    And 'Заказчик' переходит на страницу 'Мои закупки' и ищет опубликованное извещение
    And 'Заказчик' заполняет поле 'Номер закупки' и нажимает на кнопку 'Поиск'
    And 'Заказчик' открывает извещение по ссылке с номером извещения в результатах поиска
    And 'Заказчик' проверяет статус закупки "Опубликована" и статус лота "Заключение договоров" для "1" лотов
    #*******************************************************************************************************************
    #
    #                 Заказчик направляет договор Поставщику 1
    #
    #*******************************************************************************************************************
    And 'Заказчик' переходит на страницу 'Мои закупки' и ищет опубликованное извещение
    And 'Заказчик' переходит по ссылке "Направьте договор" для лота "1"
    And 'Заказчик' нажимает на кнопку 'Продолжить' в диалоговом окне 'Предупреждение'
    And 'Пользователь' ожидает "90" секунд
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
    And 'Администратор' проверяет статус торга "Не состоялся"
    And 'Администратор' нажимает кнопку "Начало регламентного срока договора - Текущая" в строке 1 таблицы 'Управление договорами'
    And 'Администратор' нажимает кнопку "Крайний срок заключения договора - Текущая" в строке 1 таблицы 'Управление договорами'
    And 'Администратор' нажимает кнопку "Действия - Обновить договор в Tender" в строке 1 таблицы 'Управление договорами'
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
    And 'Заказчик' проверяет входящее уведомление "Извещение о подписании участником договора по закупке № %s (лот № 1)"
    And 'Пользователь' покидает систему
