@smoke @runTest @establishing_a_two_way_partnership
Feature: A Two-Way Partnership Between Two Customers
  #=====================================================================================================================
  # Пользователь создает двухсторонние партнерские отношения между двумя Заказчиками
  # ( https://ve-lab.visualstudio.com/kontur.market/_workitems/edit/283294 )
  # В системе созданы следующие пользователи:
  # Заказчик 1 - "Заказчик 1 Юридическое лицо 1"
  # Заказчик 2 - "Заказчик 2 Юридическое лицо 1"
  #=====================================================================================================================
  Scenario: Пользователь создает двухсторонние партнерские отношения между двумя Заказчиками
    Given Параметру 'Тип теста' установлено значение "Establishing a two-way partnership"
    And Все требуемые для теста сертификаты присутствуют на компьютере
    #*******************************************************************************************************************
    #
    #                 Заказчик 1 устанавлиевает партнерскую связь с Заказчиком 2 ( делегирует доступ )
    #
    #*******************************************************************************************************************
    When "Заказчик" заходит в личный кабинет
    #-------------------------------------------------------------------------------------------------------------------
    #                 Заказчик проверяет наличие ранее установленных данным тестом партнерских связей и удаляет их
    #-------------------------------------------------------------------------------------------------------------------
    And 'Заказчик' проверяет наличие ранее установленных данным автотестом партнерских связей и удаляет их из базы данных
    #-------------------------------------------------------------------------------------------------------------------
    And 'Заказчик' переходит на страницу "МОЯ ОРГАНИЗАЦИЯ/ПОЛНОМОЧИЯ ПАРТНЕРОВ В МОИХ ЗАКУПКАХ"
    And 'Заказчик' нажимает на кнопку "Делегировать полномочия" на странице 'Полномочия партнеров в моих закупках'
    And 'Заказчик' проверяет, что открыта страница "Делегирование доступа организации"
    And 'Заказчик' нажимает на кнопку "Выбрать" на странице 'Делегирование доступа организации'
    And 'Заказчик' указывает требуемую организацию в диалоговом окне 'Выбрать организацию'
    And 'Заказчик' проверяет значение поля 'Организация партнёр' на странице 'Делегирование доступа организации'
    And 'Заказчик' проверяет невозможность редактирования поля 'Организация партнёр' на странице 'Делегирование доступа организации'
    And 'Заказчик' заполняет поле 'Наименование связи' на странице 'Делегирование доступа организации' сгенерированным именем
    And 'Заказчик' устанавливает флажок "Право представителя на электронной площадке" на странице 'Делегирование доступа организации'
    And 'Заказчик' устанавливает флажок "Право просмотра отчетности по закупкам" на странице 'Делегирование доступа организации'
    And 'Заказчик' устанавливает флажок "Право просмотра всех закупок" на странице 'Делегирование доступа организации'
    And 'Заказчик' нажимает на кнопку "Делегировать доступ" на странице 'Делегирование доступа организации'
    And 'Заказчик' нажимает на кнопку 'OK' в диалоговом окне 'Операция успешно выполнена'
    And 'Заказчик' проверяет, что открыта страница "Карточка партнёрской связи"
    And 'Заказчик' проверяет значение поля 'ИНН' организации донора прав на странице 'Карточка партнёрской связи'
    And 'Заказчик' проверяет значение поля 'ОГРН' организации донора прав на странице 'Карточка партнёрской связи'
    And 'Заказчик' проверяет значение поля 'ИНН' организации реципиента прав на странице 'Карточка партнёрской связи'
    And 'Заказчик' проверяет значение поля 'ОГРН' организации реципиента прав на странице 'Карточка партнёрской связи'
    And 'Заказчик' проверяет значение поля 'Наименование связи' на странице 'Карточка партнёрской связи'
    And 'Заказчик' проверяет возможность редактирования поля 'Наименование связи' на странице 'Карточка партнёрской связи'
    And 'Заказчик' проверяет, что флажок "Право предоставитекля на электррнной площадке" "установлен" на странице 'Карточка партнёрской связи'
    And 'Заказчик' проверяет, что флажок "Право просмотра отчетности по закупкам" "установлен" на странице 'Карточка партнёрской связи'
    And 'Заказчик' проверяет, что флажок "Право просмотра всех закупок" "установлен" на странице 'Карточка партнёрской связи'
    And 'Заказчик' нажимает на кнопку "Сохранить" на странице 'Карточка партнёрской связи'
    And 'Заказчик' нажимает на кнопку 'OK' в диалоговом окне 'Сообщение'
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Заказчик 2 проверяет наличие установленной партнерской связи с Заказчиком 1 ( доступ делегирован )
    #
    #*******************************************************************************************************************
    When "Заказчик 1 Сотрудник 1" заходит в личный кабинет
    And 'Заказчик' переходит на страницу "МОЯ ОРГАНИЗАЦИЯ/МОИ ПОЛНОМОЧИЯ В ЗАКУПКАХ ПАРТНЕРОВ"
    And 'Заказчик' ищет партнерскую связь по её полному наименованию на странице 'Мои полномочия в закупках партнеров'
    And 'Заказчик' проверяет результаты поиска партнерской связи на странице 'Мои полномочия в закупках партнеров'
    And 'Заказчик' переходит по ссылке с наименованием партнерской связи на странице 'Мои полномочия в закупках партнеров'
    And 'Пользователь' переключается на новую вкладку в браузере
    And 'Заказчик' проверяет, что открыта страница "Карточка партнёрской связи"
    And 'Заказчик' проверяет значение поля 'ИНН' организации донора прав на странице 'Карточка партнёрской связи'
    And 'Заказчик' проверяет значение поля 'ОГРН' организации донора прав на странице 'Карточка партнёрской связи'
    And 'Заказчик' проверяет значение поля 'ИНН' организации реципиента прав на странице 'Карточка партнёрской связи'
    And 'Заказчик' проверяет значение поля 'ОГРН' организации реципиента прав на странице 'Карточка партнёрской связи'
    And 'Заказчик' проверяет значение поля 'Наименование связи' на странице 'Карточка партнёрской связи'
    And 'Заказчик' проверяет невозможность редактирования поля 'Наименование связи' на странице 'Карточка партнёрской связи'
    And 'Заказчик' проверяет, что флажок "Право предоставитекля на электррнной площадке" "установлен" на странице 'Карточка партнёрской связи'
    And 'Заказчик' проверяет, что флажок "Право просмотра отчетности по закупкам" "установлен" на странице 'Карточка партнёрской связи'
    And 'Заказчик' проверяет, что флажок "Право просмотра всех закупок" "установлен" на странице 'Карточка партнёрской связи'
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Заказчик 1 устанавлиевает партнерскую связь с Заказчиком 2 ( запрашивает доступ )
    #
    #*******************************************************************************************************************
    When "Заказчик" заходит в личный кабинет
    And 'Заказчик' переходит на страницу "МОЯ ОРГАНИЗАЦИЯ/МОИ ПОЛНОМОЧИЯ В ЗАКУПКАХ ПАРТНЕРОВ"
    And 'Заказчик' нажимает на кнопку "Запросить полномочия" на странице 'Мои полномочия в закупках партнеров'
    And 'Заказчик' проверяет, что открыта страница "Запрос на установление связи"
    And 'Заказчик' нажимает на кнопку "Выбрать" на странице 'Запрос на установление связи'
    And 'Заказчик' указывает требуемую организацию в диалоговом окне 'Выбрать организацию'
    And 'Заказчик' проверяет значение поля 'Организация партнёр' на странице 'Запрос на установление связи'
    And 'Заказчик' проверяет невозможность редактирования поля 'Организация партнёр' на странице 'Запрос на установление связи'
    And 'Заказчик' заполняет поле 'Наименование связи' на странице 'Запрос на установление связи' сгенерированным именем
    And 'Заказчик' устанавливает флажок "Право представителя на электронной площадке" на странице 'Запрос на установление связи'
    And 'Заказчик' устанавливает флажок "Право просмотра отчетности по закупкам" на странице 'Запрос на установление связи'
    And 'Заказчик' устанавливает флажок "Право просмотра всех закупок" на странице 'Запрос на установление связи'
    And 'Заказчик' нажимает на кнопку "Запросить доступ" на странице 'Запрос на установление связи'
    And 'Заказчик' нажимает на кнопку 'OK' в диалоговом окне 'Операция успешно выполнена'
    And 'Заказчик' проверяет, что открыта страница "Исходящие запросы на добавление связи"
    And 'Заказчик' проверяет, что созданный запрос отображается в таблице 'Список запросов на установление связи' на странице 'Исходящие запросы на добавление связи'
    And 'Заказчик' сохраняет номер созданного запроса из таблицы 'Список запросов на установление связи' на странице 'Исходящие запросы на добавление связи'
    And 'Пользователь' покидает систему
    #*******************************************************************************************************************
    #
    #                 Заказчик 2 принимает запрос на установленние партнерской связи с Заказчиком 1 ( доступ разрешен )
    #
    #*******************************************************************************************************************
    When "Заказчик 1 Сотрудник 1" заходит в личный кабинет
    And 'Заказчик' проверяет последнее из входящих уведомлений "Поступил запрос на создание партнерской связи"
    And 'Заказчик' переходит по первой верхней ссылке в колонке 'Входящий номер' результатов поиска уведомлений
    And 'Заказчик' проверяет, что открыта страница "Просмотр сообщения"
    And 'Заказчик' переходит к рассмотрению запроса по ссылке на странице 'Просмотр сообщения'
    And 'Пользователь' переключается на новую вкладку в браузере
    And 'Заказчик' проверяет, что открыта страница "Запрос на установление связи-принять запрос или отказать в принятии"
    And 'Заказчик' проверяет значение поля 'Номер запроса' на странице 'Запрос на установление связи-принять запрос или отказать в принятии'
    And 'Заказчик' проверяет значение поля 'Статус'-подтверждение на странице 'Запрос на установление связи-принять запрос или отказать в принятии'
    And 'Заказчик' нажимает на кнопку "Принять запрос" на странице 'Запрос на установление связи-принять запрос или отказать в принятии'
    And 'Заказчик' нажимает на кнопку 'OK' в диалоговом окне 'Операция успешно выполнена'
    And 'Заказчик' проверяет, что открыта страница "Карточка партнёрской связи"
    And 'Заказчик' проверяет значение поля 'ИНН' организации донора на странице 'Карточка партнёрской связи'
    And 'Заказчик' проверяет значение поля 'ОГРН' организации донора на странице 'Карточка партнёрской связи'
    And 'Заказчик' проверяет значение поля 'ИНН' организации реципиента на странице 'Карточка партнёрской связи'
    And 'Заказчик' проверяет значение поля 'ОГРН' организации реципиента на странице 'Карточка партнёрской связи'
    And 'Заказчик' проверяет значение поля 'Наименование связи' страницы 'Карточка партнёрской связи'
    And 'Заказчик' проверяет возможность редактирования поля 'Наименование связи' на странице 'Карточка партнёрской связи'
    And 'Заказчик' проверяет, что флажок "Право предоставитекля на электррнной площадке" "установлен" на странице 'Карточка партнёрской связи'
    And 'Заказчик' проверяет, что флажок "Право просмотра отчетности по закупкам" "установлен" на странице 'Карточка партнёрской связи'
    And 'Заказчик' проверяет, что флажок "Право просмотра всех закупок" "установлен" на странице 'Карточка партнёрской связи'
    And 'Заказчик' нажимает на кнопку "Сохранить" на странице 'Карточка партнёрской связи'
    And 'Заказчик' нажимает на кнопку 'OK' в диалоговом окне 'Сообщение'
    #-------------------------------------------------------------------------------------------------------------------
    #                 Заказчик проверяет наличие ранее установленных данным тестом партнерских связей и удаляет их
    #-------------------------------------------------------------------------------------------------------------------
    And 'Заказчик' проверяет наличие ранее установленных данным автотестом партнерских связей и удаляет их из базы данных
    #-------------------------------------------------------------------------------------------------------------------
    And 'Пользователь' покидает систему
