@runTest @checking_revisions_history_in_published_notice
Feature: User Checks Revisions History In Published Notice
  #=====================================================================================================================
  # Проверка истории ревизий в опубликованном извещении о закупке на боевом сервере
  # В системе созданы следующие пользователи:
  # Поставщик 1 - "Поставщик Юридическое лицо 1"
  #=====================================================================================================================
  Scenario: Проверка истории ревизий в опубликованном извещении о закупке
    Given Параметру 'Тип теста' установлено значение "Checking Revisions History In Published Notice"
    And Параметру 'Снимать скриншоты' установлено значение "true"
    And Все требуемые для теста сертификаты присутствуют на компьютере
    When "Поставщик Юридическое лицо 6 для боевого сервера" заходит в личный кабинет
    And 'Поставщик' открывает извещение о закупке используя прямую ссылку из файла конфигурации
    And 'Поставщик' проверяет наличие ревизий в извещении о закупке
    And 'Пользователь' сохраняет копию экрана приложения
    And 'Поставщик' проверяет каждую ревизию в извещении о закупке
    And 'Пользователь' покидает систему
