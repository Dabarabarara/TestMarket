@smoke @accreditation @boosted_accreditation_supplier_legal_entity
Feature: Supplier Legal Entity Boosted Accreditation
  #=====================================================================================================================
  # Ускоренная аккредитация [ Поставщик Юридическое лицо ]
  # ( .kontur.ru/supplier/lk/Accreditation/Request.aspx )
  # https://ve-lab.visualstudio.com/kontur.market/_workitems/edit/362352
  # В системе созданы следующие пользователи:
  # Администратор ( Оператор ) - "Администратор"
  #=====================================================================================================================
  Scenario: Ускоренная аккредитация Поставщика Юридическое лицо
    Given Параметру 'Тип теста' установлено значение "Accreditation"
    And Все требуемые для теста сертификаты присутствуют на компьютере
    #*******************************************************************************************************************
    #
    #                 Юридическое лицо заполняет заявку на аккредитацию
    #
    #*******************************************************************************************************************
    When 'Пользователь' открывает страницу Аккредитации
    And 'Пользователь' выбирает тип Аккредитации "Поставщик Юридическое лицо"
    And 'Пользователь' сохраняет указатель на главное окно приложения
    And 'Пользователь' открывает окно выбора сертификата
    And 'Пользователь' выбирает сертификат
    And 'Пользователь' проверят наличие информационного текста "Ускоренная аккредитация" на странице 'Заявка на аккредитацию'
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
    #                 Почтовый адрес
    #
    And 'Пользователь' проверяет, что флажок "Совпадает с юридическим адресом" установлен на странице аккредитации
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
    #                 Ускоренная аккредитация
    #
    And 'Пользователь' проверяет наличие заголовка раздела 'Ускоренная аккредитация' на странице 'Заявка на аккредитацию'
    And 'Пользователь' проверяет текст "осуществляется приоритетное проведение анализа информации" раздела 'Ускоренная аккредитация' на странице 'Заявка на аккредитацию'
    And 'Пользователь' устанавливает флажок "Заказать услугу ускоренной аккредитации" на странице 'Заявка на аккредитацию'
    #
    #                 Заявка на аккредитацию
    #
    And 'Пользователь' устанавливает флажок "Заявление на аккредитацию" на странице 'Заявка на аккредитацию'
    And 'Пользователь' заполняет поле 'Ключ защиты' на странице 'Заявка на аккредитацию'
    And 'Пользователь' нажимает на кнопку 'Подать заявку на аккредитацию' на странице 'Заявка на аккредитацию'
    And 'Пользователь' сохраняет в параметрах теста 'Наименование' на странице 'Карточка заявки на аккредитацию'
    And 'Пользователь' пытается скачать документ платежного поручения со страницы 'Просмотр заявки на аккредитацию'
    And 'Пользователь' проверят статус заявки с ускоренной аккредитацией на странице 'Просмотр заявки на аккредитацию'
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
    And 'Администратор' проверяет прикрепленные документы у 'Поставщик' с ускоренной аккредитацией
	And 'Администратор' аккредитует поданную заявку
	And 'Администратор' принудительно утверждает поданную заявку
	And 'Администратор' проверяет статус аккредитации "Утверждена"
    And 'Пользователь' покидает систему
	#*******************************************************************************************************************
    #
    #                 Поставщик проверяет данные аккредитации
    #
    #*******************************************************************************************************************
    When 'Поставщик' заходит в личный кабинет
    And 'Поставщик' "Поставщик Юридическое лицо" выбирает сертификат
    And 'Поставщик' переходит на страницу 'Реестр транзакций счёта'
    And 'Поставщик' проверяет списание суммы за услугу ускоренной аккредитации "9 900,00 (Российский рубль)"
    And 'Поставщик' переходит на страницу 'Информация об организации'
    Then 'Поставщик' проверяет данные аккредитации
    And 'Поставщик' переключается на вкладку "Документы" страницы 'Информация об организации'
    And 'Поставщик' проверяет, что список ссылок для скачивания документов организации на странице 'Информация об организации' не пустой
    And 'Поставщик' пытается скачать документ 'Оферта.docx' со страницы 'Информация об организации'
    And 'Пользователь' покидает систему