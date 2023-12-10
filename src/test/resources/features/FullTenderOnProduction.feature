@runTest @production_tender
Feature: Full Tender On Production
  #=====================================================================================================================
  # Проведение полного электронного конкурса на боевом сервере
  # В системе созданы следующие пользователи:
  # Заказчик    - "Заказчик для боевого сервера"
  # Поставщик 1 - "Поставщик Юридическое лицо 2 для боевого сервера"
  # Поставщик 2 - "Поставщик Юридическое лицо 3 для боевого сервера"
  #=====================================================================================================================
  Scenario: Полный электронный конкурс на боевом сервере без использования ускорения процедур
    Given Параметру 'Тип теста' установлено значение "Full Tender On Production"
    And Все требуемые для теста сертификаты присутствуют на компьютере
    #-------------------------------------------------------------------------------------------------------------------
    #                 Заказчик публикует извещение о проведении коммерческого конкурса
    #-------------------------------------------------------------------------------------------------------------------
    When "Заказчик для боевого сервера" заходит в личный кабинет
    And 'Заказчик А' открывает страницу создания извещения для коммерческой закупки "Конкурс"
    And 'Заказчик А' заполняет сведения о полном электронном конкурсе:
    And 'Заказчик А' проверяет, что поле "Способ закупки" содержит значение "Конкурс"
    And 'Заказчик А' проверяет, что поле "Тип закупки" содержит значение "Коммерческая закупка"
    And 'Заказчик А' заполняет общие сведения о закупке для полного конкурса
    And 'Заказчик А' заполняет сведения о порядке подачи заявок
    And 'Заказчик А' заполняет сведения о порядке работы комиссии для конкурса без ускорения процедур
    And 'Заказчик А' заполняет сведения о контактном лице
    And 'Заказчик А' заполняет сведения об условиях участия для 'Поставщиков'
    And 'Заказчик А' заполняет сведения о лоте № "1" для полного конкурса
    And 'Заказчик А' прикрепляет необходимые документы
    And 'Заказчик А' публикует извещение
    And 'Заказчик' переходит на страницу 'Мои закупки' и ищет опубликованное извещение
    And 'Заказчик' проверяет статус "Прием заявок" лота № "1" в опубликованном извещении
    #
    #                 Проверка входящего уведомления [Уведомление об опубликовании закупки № ]
    #
    And 'Заказчик' проверяет входящее уведомление "Уведомление о публикации закупки № "
    And 'Пользователь' покидает систему
    #-------------------------------------------------------------------------------------------------------------------
    #                 Поставщик 1 подает заявку на участие в полном электронном конкурсе
    #-------------------------------------------------------------------------------------------------------------------
    When "Поставщик Юридическое лицо 2 для боевого сервера" заходит в личный кабинет
    And 'Поставщик' переходит к списку закупок
    And 'Поставщик' находит созданную закупку
    And 'Поставщик' переходит по ссылке с номером закупки в результатах поиска
    And 'Поставщик' ожидает окончания загрузки страницы с деталями закупки 'Закупка № XXXX'
    And 'Поставщик' нажимает на кнопку 'Подать заявку' для лота № "1"
    And 'Поставщик' заполняет поле заявки 'Предложение о цене договора' значением "3,00" руб.
    And 'Поставщик' заполняет поле заявки 'Номер контактного телефона'
    And 'Поставщик' прикрепляет документ в разделе заявки 'Сведения о товаре'
    And 'Поставщик' № "1" подтверждает заявку на участие для лота № "1"
    And 'Поставщик' проверяет статус заявки "Утверждена оператором"
    #
    #                 Проверка уведомления ["Подтверждение получения заявки на участие в закупке № X по лоту № X]
    #
    And 'Поставщик' проверяет уведомление "Подтверждение получения заявки на участие в закупке № %s по лоту № 1"
    And 'Пользователь' покидает систему
    #-------------------------------------------------------------------------------------------------------------------
    #                 Поставщик 2 подает заявку на участие в полном электронном конкурсе
    #-------------------------------------------------------------------------------------------------------------------
    When "Поставщик Юридическое лицо 3 для боевого сервера" заходит в личный кабинет
    And 'Поставщик' переходит к списку закупок
    And 'Поставщик' находит созданную закупку
    And 'Поставщик' переходит по ссылке с номером закупки в результатах поиска
    And 'Поставщик' ожидает окончания загрузки страницы с деталями закупки 'Закупка № XXXX'
    And 'Поставщик' нажимает на кнопку 'Подать заявку' для лота № "1"
    And 'Поставщик' заполняет поле заявки 'Предложение о цене договора' значением "3,00" руб.
    And 'Поставщик' заполняет поле заявки 'Номер контактного телефона'
    And 'Поставщик' прикрепляет документ в разделе заявки 'Сведения о товаре'
    And 'Поставщик' № "2" подтверждает заявку на участие для лота № "1"
    And 'Поставщик' проверяет статус заявки "Утверждена оператором"
    #
    #                 Проверка уведомления ["Подтверждение получения заявки на участие в закупке № X по лоту № X]
    #
    And 'Поставщик' проверяет уведомление "Подтверждение получения заявки на участие в закупке № %s по лоту № 1"
    And 'Пользователь' покидает систему
    #-------------------------------------------------------------------------------------------------------------------
    #                 Публикация протокола открытия доступа
    #-------------------------------------------------------------------------------------------------------------------
    When "Заказчик для боевого сервера" заходит в личный кабинет
    And 'Заказчик' заполняет поле 'Номер закупки' и нажимает на кнопку 'Поиск'
    And 'Заказчик' открывает извещение по ссылке с номером извещения в результатах поиска
    And 'Заказчик' проверяет статус закупки "Опубликована" и статус лота "Открытие доступа"
    And 'Заказчик' переходит по ссылке "Откройте доступ к заявкам" для лота "1"
    And 'Заказчик' публикует протокол открытия доступа
    Then Протокол перешел в статус "Утвержден"
    #-------------------------------------------------------------------------------------------------------------------
    #                 Публикация протокола рассмотрения заявок
    #-------------------------------------------------------------------------------------------------------------------
    And 'Заказчик' переходит на страницу 'Мои закупки' и ищет опубликованное извещение
    And 'Заказчик' открывает извещение по ссылке с номером извещения в результатах поиска
    And 'Заказчик' проверяет статус закупки "Опубликована" и статус лота "Рассмотрение заявок"
    And 'Заказчик' переходит по ссылке "Рассмотрите заявки" для лота "1"
    And 'Заказчик' допускает обе заявки и публикует протокол рассмотрения заявок конкурса на бою
    Then Протокол перешел в статус "Утвержден"
    And 'Заказчик' переходит на страницу 'Мои закупки' и ищет опубликованное извещение
    And 'Заказчик' открывает извещение по ссылке с номером извещения в результатах поиска
    And 'Заказчик' проверяет статус закупки "Опубликована" и статус лота "Оценка заявок"
    #-------------------------------------------------------------------------------------------------------------------
    #                 Публикация протокола оценки заявок
    #-------------------------------------------------------------------------------------------------------------------
    And 'Заказчик' заполняет поле 'Номер закупки' и нажимает на кнопку 'Поиск'
    And 'Заказчик' переходит по ссылке "Рассмотрите заявки" для лота "1"
    And 'Заказчик' публикует протокол оценки заявок конкурса
    Then Протокол перешел в статус "Утвержден"
    And 'Заказчик' переходит на страницу 'Мои закупки' и ищет опубликованное извещение
    And 'Заказчик' открывает извещение по ссылке с номером извещения в результатах поиска
    And 'Заказчик' проверяет статус закупки "Опубликована" и статус лота "Ожидает переторжку"
    And 'Пользователь' покидает систему
    #-------------------------------------------------------------------------------------------------------------------
    #                 Переторжка
    #-------------------------------------------------------------------------------------------------------------------
    When "Поставщик Юридическое лицо 3 для боевого сервера" заходит в личный кабинет
    And 'Поставщик' находит и открывает ранее созданную закупку
    And 'Поставщик' нажимает на кнопку 'Информация о торгах' для лота с номером "1" в закупке
    And 'Поставщик' ожидает начала торгов
    And 'Поставщик' нажимает на кнопку 'Подача ценовых предложений'
    And 'Поставщик' вводит цену "2" в поле Предложение цены
    And 'Поставщик' фиксирует время до окончания торгов
    And 'Поставщик' прикрепляет документ в разделе 'Новое предложение'
    And 'Поставщик' подает ценовое предложение
    And 'Поставщик' проверяет автоматическое продление переторжки после подачи ценового предложения до "10" мин.
    Then Ценовое предложение отобразилось в списке лучших ценовых предложений "2,00"
    And 'Поставщик' завершает ход торгов
    And 'Пользователь' покидает систему
    When "Поставщик Юридическое лицо 2 для боевого сервера" заходит в личный кабинет
    And 'Поставщик' находит и открывает ранее созданную закупку
    And 'Поставщик' нажимает на кнопку 'Информация о торгах' для лота с номером "1" в закупке
    And 'Поставщик' нажимает на кнопку 'Подача ценовых предложений'
    And 'Поставщик' вводит цену "1" в поле Предложение цены
    And 'Поставщик' прикрепляет документ в разделе 'Новое предложение'
    And 'Поставщик' подает ценовое предложение
    Then Ценовое предложение отобразилось в списке лучших ценовых предложений "1,00"
    And 'Поставщик' завершает ход торгов
    And 'Пользователь' покидает систему
    #-------------------------------------------------------------------------------------------------------------------
    #                 Переход на этап "Подведение итогов"
    #-------------------------------------------------------------------------------------------------------------------
    When "Заказчик для боевого сервера" заходит в личный кабинет
    And 'Заказчик' заполняет поле 'Номер закупки' и нажимает на кнопку 'Поиск'
    And 'Заказчик' открывает извещение по ссылке с номером извещения в результатах поиска
    And 'Заказчик' проверяет статус закупки "Опубликована" и статус лота "Подведение итогов"
    #-------------------------------------------------------------------------------------------------------------------
    #                 Публикация протокола проведения переторжки
    #-------------------------------------------------------------------------------------------------------------------
    And 'Заказчик' переходит по ссылке "Подведите итоги переторжки" для лота "1"
    And 'Заказчик' проверяет статус протокола - 'Формирование'
    And 'Заказчик' нажимает на кнопку 'Сформировать и прикрепить' и проверяет наличие прикрепленного файла протокола
    And 'Заказчик' проверяет наличие дубликатов в списке шаблонов протоколов
    And 'Заказчик' нажимает на кнопку 'Подписать и опубликовать'
    And 'Заказчик' нажимает на кнопку 'Продолжить' в диалоговом окне 'Предупреждение'
    And 'Заказчик' нажимает на кнопку 'OK' в диалоговом окне 'Информация'
    Then Протокол перешел в статус "Утвержден"
    And 'Заказчик' переходит на страницу 'Мои закупки' и ищет опубликованное извещение
    And 'Заказчик' открывает извещение по ссылке с номером извещения в результатах поиска
    And 'Заказчик' проверяет статус закупки "Опубликована" и статус лота "Подведение итогов"
    #-------------------------------------------------------------------------------------------------------------------
    #                 Публикация протокола подведения итогов
    #-------------------------------------------------------------------------------------------------------------------
    And 'Заказчик' переходит по ссылке "Подведите итоги торгов" для лота "1"
    And 'Заказчик' проверяет, что открыта страница "Один из протоколов"
    And 'Заказчик' проверяет сообщение 'Публикация протокола. После создания данного протокола будет невозможно внести изменения...'
    And 'Заказчик' нажимает на кнопку 'Продолжить' страницы с сообщением 'Публикация протокола.'
    And 'Заказчик' проверяет, что открыта страница "Один из протоколов"
    And 'Заказчик' переходит к просмотру раздела протокола "Общие сведения о протоколе"
    And 'Заказчик' проверяет статус протокола - 'Формирование'
    And 'Заказчик' проверяет наличие суммы "1,00 руб." в колонке 'Лучшее ценовое предложение'
    And 'Заказчик' проверяет наличие суммы "2,00 руб." в колонке 'Лучшее ценовое предложение'
    And 'Заказчик' нажимает на кнопку 'Сформировать и прикрепить' и проверяет наличие прикрепленного файла протокола
    And 'Заказчик' проверяет наличие дубликатов в списке шаблонов протоколов
    And 'Заказчик' нажимает на кнопку 'Подписать и опубликовать'
    And 'Заказчик' нажимает на кнопку 'Продолжить' в диалоговом окне 'Предупреждение'
    And 'Заказчик' нажимает на кнопку 'OK' в диалоговом окне 'Информация'
    Then Протокол перешел в статус "Утвержден"
    #                 Проверка статусов
    And 'Заказчик' переходит на страницу 'Мои закупки' и ищет опубликованное извещение
    And 'Заказчик' открывает извещение по ссылке с номером извещения в результатах поиска
    And 'Заказчик' проверяет статус закупки "Опубликована" и статус лота "Заключение договоров"
    And 'Пользователь' покидает систему
    #-------------------------------------------------------------------------------------------------------------------
    #                 Заключение договоров
    #-------------------------------------------------------------------------------------------------------------------
    When "Заказчик для боевого сервера" заходит в личный кабинет
    And 'Заказчик' заполняет поле 'Номер закупки' и нажимает на кнопку 'Поиск'
    And 'Заказчик' переходит по ссылке "Направьте договор" для лота "1"
    And 'Заказчик' направляет договор выигравшему конкурс Поставщику
    And 'Пользователь' покидает систему
    When "Поставщик Юридическое лицо 2 для боевого сервера" заходит в личный кабинет
    And 'Поставщик' переходит на страницу 'Мои договоры'
    And 'Поставщик' производит поиск договора
    And 'Поставщик' принимает и подписывает договор
    And 'Пользователь' покидает систему
    When "Заказчик для боевого сервера" заходит в личный кабинет
    And 'Заказчик' заполняет поле 'Номер закупки' и нажимает на кнопку 'Поиск'
    And 'Заказчик' переходит по ссылке "Подпишите договор" для лота "1"
    And 'Заказчик' подписывает договор
    And 'Пользователь' покидает систему
    #-------------------------------------------------------------------------------------------------------------------
    #                 Проверка сумм, заблокированных у Поставщика 1 (должна выполняться после заключения договоров)
    #-------------------------------------------------------------------------------------------------------------------
    When "Поставщик Юридическое лицо 2 для боевого сервера" заходит в личный кабинет
    And 'Поставщик' переходит на страницу 'Реестр транзакций счёта'
    And 'Поставщик' № "1" проверяет "Блокирование средств на виртуальном счете" для лота № "1"
    And 'Пользователь' покидает систему
    #-------------------------------------------------------------------------------------------------------------------
    #                 Проверка сумм, заблокированных у Поставщика 2 (должна выполняться после заключения договоров)
    #-------------------------------------------------------------------------------------------------------------------
    When "Поставщик Юридическое лицо 3 для боевого сервера" заходит в личный кабинет
    And 'Поставщик' переходит на страницу 'Реестр транзакций счёта'
    And 'Поставщик' № "2" проверяет "Блокирование средств на виртуальном счете" для лота № "1"
    And 'Пользователь' покидает систему
    #-------------------------------------------------------------------------------------------------------------------
    #                 Проверка статусов, прикрепленных к извещению протоколов, договоров и истории договоров
    #-------------------------------------------------------------------------------------------------------------------
    When "Заказчик для боевого сервера" заходит в личный кабинет
    And 'Заказчик' заполняет поле 'Номер закупки' и нажимает на кнопку 'Поиск'
    And 'Заказчик' открывает извещение по ссылке с номером извещения в результатах поиска
    And 'Заказчик' проверяет возможность скачать прикрепленные к извещению документы "5" по ссылкам
    And 'Заказчик' проверяет возможность скачать заявки из формы 'Протокол открытия доступа конкурса'
    And 'Заказчик' проверяет статус закупки "Завершена" и статус лота "Завершена"
    And 'Заказчик' переходит на страницу "ДОГОВОРЫ/ДОГОВОРЫ" с именем URL для проверки "Мои контракты"
    And 'Заказчик' выполняет поиск договора по номеру закупки
    And 'Заказчик' открывает страницу 'Сведения о договоре' по ссылке на номер карточки договора
    And 'Заказчик' проверяет возможность выгрузить информацию о договоре
    And 'Заказчик' проверяет возможность экспорта истории в Excel-файл
    And 'Пользователь' покидает систему
