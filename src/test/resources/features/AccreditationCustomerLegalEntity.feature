@smoke @accreditation @accreditation_customer_legal_entity
Feature: Customer Legal Entity Accreditation
  #=====================================================================================================================
  # Первичная аккредитация [ Заказчик Юридическое лицо ]
  # ( https://ve-lab.visualstudio.com/kontur.market/_workitems/edit/278597 )
  # ( .kontur.ru/supplier/lk/Accreditation/Request.aspx )
  # В системе созданы следующие пользователи:
  # Администратор ( Оператор ) - "Администратор"
  #=====================================================================================================================
  Scenario: Первичная аккредитация Заказчик Юридическое лицо
    Given Параметру 'Тип теста' установлено значение "Accreditation"
    And Все требуемые для теста сертификаты присутствуют на компьютере
    #*******************************************************************************************************************
    #
    #                 Юридическое лицо заполняет заявку на аккредитацию
    #
    #*******************************************************************************************************************
    When 'Пользователь' открывает страницу Аккредитации
    And 'Пользователь' выбирает тип Аккредитации "Заказчик Юридическое лицо"
    And 'Пользователь' сохраняет указатель на главное окно приложения
    And 'Пользователь' сбрасывает флажок "Аккредитация в качестве поставщика" на странице аккредитации
    And 'Пользователь' открывает окно выбора сертификата
    And 'Пользователь' выбирает сертификат
    #
    #                 Информация о лице, подписавшем заявку
    #
    And 'Пользователь' заполняет поле 'Номер телефона лица, подписавшего заявку' на странице 'Заявка на аккредитацию'
    And 'Пользователь' заполняет поле 'Адрес электронной почты' на странице 'Заявка на аккредитацию'
    And 'Пользователь' заполняет поле 'Имя пользователя - логин' на странице 'Заявка на аккредитацию'
    And 'Пользователь' заполняет поле 'Пароль' на странице 'Заявка на аккредитацию'
    And 'Пользователь' заполняет поле 'Подтверждение пароля' на странице 'Заявка на аккредитацию'
    And 'Пользователь' заполняет поле 'Кодовое слово' на странице 'Заявка на аккредитацию'
    #
    #                 Заявитель
    #
    And 'Пользователь' сохраняет в параметрах теста ИНН на странице 'Заявка на аккредитацию'
    And 'Пользователь' заполняет поля 'Номер телефона' на странице 'Заявка на аккредитацию'
    And 'Пользователь' заполняет поле 'Основной е-mail для уведомлений' на странице 'Заявка на аккредитацию'
    And 'Пользователь' заполняет поле 'ФИО руководителя организации' на странице 'Заявка на аккредитацию'
    And 'Пользователь' заполняет поле 'Контактное лицо' на странице 'Заявка на аккредитацию'
    #
    #                 Юридический адрес
    #
    And 'Пользователь' заполняет поле 'Регион' на странице 'Заявка на аккредитацию'
    And 'Пользователь' заполняет поле 'Район' на странице 'Заявка на аккредитацию'
    And 'Пользователь' заполняет поле 'Город' на странице 'Заявка на аккредитацию'
    And 'Пользователь' заполняет поле 'Населённый пункт' на странице 'Заявка на аккредитацию'
    And 'Пользователь' заполняет поле 'Улица' на странице 'Заявка на аккредитацию'
    And 'Пользователь' заполняет поле 'Почтовый индекс' на странице 'Заявка на аккредитацию'
    And 'Пользователь' заполняет поле 'Дом - строение - корпус' на странице 'Заявка на аккредитацию'
    #
    #                 Сведения о видах экономической деятельности
    #
    And 'Пользователь' устанавливает значение в поле 'Номенклатура ОКВЭД2' на странице 'Заявка на аккредитацию'
    #
    #                 Документы заявителя
    #
    And 'Пользователь' загружает данные в таб '0-0 Учредительные документы' на странице 'Заявка на аккредитацию'
    And 'Пользователь' загружает данные в таб '0-0 Полномочия Руководителя' на странице 'Заявка на аккредитацию'
    And 'Пользователь' загружает данные в таб '0-0 Выписка ЕГРЮЛ' на странице 'Заявка на аккредитацию'
    And 'Пользователь' загружает данные в таб '0-0 Полномочия на получение аккредитации' на странице 'Заявка на аккредитацию'
    And 'Пользователь' загружает данные в таб '0-0 Доверенность' на странице 'Заявка на аккредитацию'
    #
    #                 Банковские реквизиты
    #
    And 'Пользователь' заполняет поле 'БИК' на странице 'Заявка на аккредитацию'
    And 'Пользователь' заполняет поле 'Расчетный счет' на странице 'Заявка на аккредитацию'
    And 'Пользователь' заполняет поле 'Название банка' на странице 'Заявка на аккредитацию'
    And 'Пользователь' заполняет поле 'Адрес банка' на странице 'Заявка на аккредитацию'
    #
    #                 Заявка на аккредитацию
    #
    And 'Пользователь' устанавливает флажок "Заявление на аккредитацию" на странице 'Заявка на аккредитацию'
    And 'Пользователь' заполняет поле 'Ключ защиты' на странице 'Заявка на аккредитацию'
    And 'Пользователь' нажимает на кнопку 'Подать заявку на аккредитацию' на странице 'Заявка на аккредитацию'
    And 'Пользователь' сохраняет в параметрах теста 'Наименование' на странице 'Карточка заявки на аккредитацию'
    And 'Пользователь' закрывает все дополнительные окна в приложении
    #*******************************************************************************************************************
    #
    #                 Администратор выполняет аккредитацию поданной заявки
    #
    #*******************************************************************************************************************
    When "Администратор" заходит в личный кабинет
    And 'Администратор' переходит на страницу "Заявки на аккредитацию"
    And 'Администратор' устанавливает значение поля 'ИНН' на странице 'Список заявок'
    And 'Администратор' нажимает на кнопку 'Поиск' на странице 'Список заявок'
    And 'Администратор' нажимает на ссылку в колонке 'Номер' на странице 'Список заявок'
    And 'Администратор' проверяет наименование организации
    And 'Администратор' проверяет ИНН организации
    And 'Администратор' выполняет рассмотрение заявки
    And 'Администратор' проверяет прикрепленные документы у 'Заказчик'
    And 'Администратор' аккредитует поданную заявку
    And 'Администратор' проверяет статус аккредитации "Утверждена"
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Заказчик проверяет данные аккредитации
    #
    #*******************************************************************************************************************
    When 'Заказчик' открывает страницу 'Вход в личный кабинет'
    And 'Заказчик' производит авторизацию по логину и паролю после аккредитации
    And 'Заказчик' переходит на страницу "МОЯ ОРГАНИЗАЦИЯ/ИНФОРМАЦИЯ ОБ ОРГАНИЗАЦИИ" с именем URL для проверки "Информация об организации"
    And 'Заказчик' проверяет данные аккредитации
    And 'Пользователь' покидает систему
