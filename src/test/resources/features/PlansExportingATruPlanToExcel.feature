@smoke @runTest @plans_exporting_a_tru_plan_to_excel
Feature: Plans Exporting A Tru Plan To Excel
  #=====================================================================================================================
  # Планы - экспорт плана в Excel (План ТРУ)
  # ( https://ve-lab.visualstudio.com/kontur.market/_workitems/edit/326764 )
  # В системе создан следующий пользователь:
  # Заказчик - "Заказчик 1 для работы с ЕИС" ( особый сертификат, он такой у нас один на всех )
  #=====================================================================================================================
  Scenario: Планы - экспорт плана в Excel (План ТРУ)
    Given Параметру 'Тип теста' установлено значение "Plans Exporting A Tru Plan To Excel"
    And Все требуемые для теста сертификаты присутствуют на компьютере
    #*******************************************************************************************************************
    #
    #                 Заказчик создает новый [План закупки товаров (работ, услуг)]
    #
    #*******************************************************************************************************************
    When "Заказчик 1 для работы с ЕИС" заходит в личный кабинет
    #
    #                 Мои закупки
    #
    And 'Заказчик' переходит на страницу "ПЛАНЫ ЗАКУПОК" с именем URL для проверки "Планы закупок"
    #
    #                 Планы закупок
    #
    And 'Заказчик' нажимает на кнопку "Добавить план закупки" на странице 'Планы закупок'
    #
    #                 Создание плана закупки
    #
    And 'Заказчик' проверяет, что открыта страница "Создание плана закупки"
    #                 Создание плана закупки - Общие сведения о плане закупки
    And 'Заказчик' переходит к просмотру раздела "Общие сведения о плане закупки" на странице 'Создание плана закупки'
    And 'Заказчик' заполняет поле 'Наименование' сгенерированным именем на странице 'Создание плана закупки'
    And 'Заказчик' проверяет, что поле "Заказчик" содержит не пустое значение на странице 'Создание плана закупки'
    And 'Заказчик' проверяет, что поле "Вид" содержит значение "План закупки товаров (работ, услуг)" на странице 'Создание плана закупки'
    And 'Заказчик' заполняет дату "Период C" в "MONTHS" со смещением "+0" от текущей на странице 'Создание плана закупки'
    And 'Заказчик' заполняет дату "Период По" в "MONTHS" со смещением "+1" от текущей на странице 'Создание плана закупки'
    And 'Заказчик' заполняет дату "Дата утверждения" в "MONTHS" со смещением "+0" от текущей на странице 'Создание плана закупки'
    And 'Заказчик' заполняет год "Отчетный год" в "YEARS" со смещением "+0" от текущего на странице 'Создание плана закупки'
    #                 Создание плана закупки - Подвал страницы
    And 'Заказчик' переходит к просмотру раздела "Подвал страницы" на странице 'Создание плана закупки'
    And 'Заказчик' нажимает на кнопку "Продолжить" на странице 'Создание плана закупки'
    And 'Заказчик' нажимает на кнопку "Продолжить (формирование черновика)" в окне диалога 'Сохранено'
    #
    #                 Редактирование плана закупки
    #
    And 'Заказчик' проверяет, что открыта страница "Редактирование плана закупки"
    #                 Редактирование плана закупки - Общие сведения о плане закупки
    And 'Заказчик' переходит к просмотру раздела "Общие сведения о плане закупки" на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что "Наименование" содержит параметр теста "TradePlanName" на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что поле "Заказчик" содержит не пустое значение на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что поле "Вид" содержит значение "План закупки товаров (работ, услуг)" на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет дату в поле "Период C" в "MONTHS" со смещением "+0" от текущей на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет дату в поле "Период По" в "MONTHS" со смещением "+1" от текущей на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет дату в поле "Дата утверждения" в "MONTHS" со смещением "+0" от текущей на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет год в поле "Отчетный год" в "YEARS" со смещением "+0" от текущего на странице 'Редактирование плана закупки'
    #                 Редактирование плана закупки - Импорт позиций
    And 'Заказчик' переходит к просмотру раздела "Импорт позиций" на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что переключатель "Загрузка из Excel-файла" отмечен на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что переключатель "Загрузка с ЕИС" не отмечен на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что кнопка "Загрузить файл" отображается и доступна на странице 'Редактирование плана закупки'
    And 'Заказчик' импортирует позиции плана ТРУ из Excel-файла в созданный черновик на странице 'Редактирование плана закупки'
    #                 Редактирование плана закупки - Позиции плана
    And 'Заказчик' переходит к просмотру раздела "Позиции плана" на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет количество импортированных позиций плана закупки - 5 на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что в столбце "Номер" таблицы 'Позиции плана' нет пустых ячеек на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что в столбце "Предмет договора" таблицы 'Позиции плана' нет пустых ячеек на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что в столбце "Начальная цена договора" таблицы 'Позиции плана' нет пустых ячеек на странице 'Редактирование плана закупки'
    #                 Редактирование плана закупки - Позиции плана - Добавление новой позиции в план закупки
    And 'Заказчик' переходит к просмотру раздела "Информеры" на странице 'Редактирование плана закупки'
    And 'Заказчик' нажимает на кнопку "Добавить позицию" на странице 'Редактирование плана закупки'
    And 'Заказчик' заполняет поле "Предмет договора" таблицы 'Позиции плана' значением "Предмет договора" на странице 'Редактирование плана закупки'
    And 'Заказчик' заполняет поле "Мин. требования к товарам/услугам" таблицы 'Позиции плана' значением "Мин. требования к товарам/услугам" на странице 'Редактирование плана закупки'
    And 'Заказчик' заполняет поле со счётчиком "Начальная цена договора" таблицы 'Позиции плана' значением "5" на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что поле "Начальная цена договора" таблицы 'Позиции плана' содержит значение "5" на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что поле "Начальная цена договора список доступных валют" таблицы 'Позиции плана' содержит значение "Российский рубль" на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что флажок "Начальная цена договора невозможно определить цену" таблицы 'Позиции плана' "не установлен" на странице 'Редактирование плана закупки'
    And 'Заказчик' заполняет поле "Порядок формирования цены договора" таблицы 'Позиции плана' значением "Порядок формирования цены договора" на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что переключатель "Планируемый период размещения месяц" таблицы 'Позиции плана' отмечен на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что переключатель "Планируемый период размещения дата" таблицы 'Позиции плана' не отмечен на странице 'Редактирование плана закупки'
    And 'Заказчик' устанавливает переключатель "Планируемый период размещения" таблицы 'Позиции плана' в положение "Планируемый период размещения дата" на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что переключатель "Планируемый период размещения месяц" таблицы 'Позиции плана' не отмечен на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что переключатель "Планируемый период размещения дата" таблицы 'Позиции плана' отмечен на странице 'Редактирование плана закупки'
    And 'Заказчик' заполняет дату "Планируемый период размещения" таблицы 'Позиции плана' в "MONTHS" со смещением "+0" от текущей на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что флажок "Планируемый период размещения закупка запланирована на третий" таблицы 'Позиции плана' "не установлен" на странице 'Редактирование плана закупки'
    And 'Заказчик' заполняет месяц и год "Срок исполнения договора" таблицы 'Позиции плана' в "MONTHS" со смещением "+1" от текущего на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что поле "Способ закупки" таблицы 'Позиции плана' содержит значение "[" на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что поле "Закупка в эл. форме" таблицы 'Позиции плана' содержит значение "Да" на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что поле "МСП Да/Нет" таблицы 'Позиции плана' содержит не пустое значение на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что поле "МСП категория" таблицы 'Позиции плана' содержит не пустое значение на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что поле "Причина аннулирования" таблицы 'Позиции плана' содержит значение "-" на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что поле "Закупка товаров (работ, услуг), относящихся к инновационной продукции" таблицы 'Позиции плана' содержит значение "Нет" на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что поле "Заказчик" таблицы 'Позиции плана' содержит значение "АО «Финтендер-Академия»" на странице 'Редактирование плана закупки'
    #                 Редактирование плана закупки - Позиции плана - Добавление новой позиции в план закупки -
    #                 Товары (работы, услуги)
    And 'Заказчик' нажимает на кнопку "Показать блок товары (работы, услуги)" таблицы 'Позиции плана' на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что поле "Наименование" таблицы 'Товары работы услуги' содержит значение "Предмет договора" на странице 'Редактирование плана закупки'
    And 'Заказчик' заполняет поле "Кол-во" таблицы 'Товары работы услуги' значением "1" на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что флажок "Невозможно определить кол-во" таблицы 'Товары работы услуги' "не установлен" на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что поле "Ед. измерения" таблицы 'Товары работы услуги' содержит не пустое значение на странице 'Редактирование плана закупки'
    And 'Заказчик' выбирает значение 'Товар' в списке для поля 'Тип объекта закупки' таблицы 'Товары работы услуги' на странице 'Редактирование плана закупки'
    And 'Заказчик' заполняет поле "ОКВЭД2" таблицы 'Товары работы услуги' значением "01.11.13" на странице 'Редактирование плана закупки'
    And 'Заказчик' заполняет поле "ОКПД2" таблицы 'Товары работы услуги' значением "36.00.11.000" на странице 'Редактирование плана закупки'
    And 'Заказчик' выбирает значение '67000000000: г. Севастополь' в списке для поля 'Регион поставки' таблицы 'Товары работы услуги' на странице 'Редактирование плана закупки'
    And 'Заказчик' заполняет поле "Дополнительные сведения" таблицы 'Товары работы услуги' значением "Дополнительные сведения" на странице 'Редактирование плана закупки'
    #                 Редактирование плана закупки - Позиции плана - Сохранение новой позиции в плане закупки
    And 'Заказчик' нажимает на кнопку "Сохранить новую позицию" на странице 'Редактирование плана закупки'
    #                 Редактирование плана закупки - Позиции плана
    And 'Заказчик' переходит к просмотру раздела "Позиции плана" на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет количество импортированных позиций плана закупки - 6 на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что в столбце "Номер" таблицы 'Позиции плана' нет пустых ячеек на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что в столбце "Предмет договора" таблицы 'Позиции плана' нет пустых ячеек на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что в столбце "Начальная цена договора" таблицы 'Позиции плана' нет пустых ячеек на странице 'Редактирование плана закупки'
    #                 Редактирование плана закупки - Документы
    And 'Заказчик' переходит к просмотру раздела "Документы" на странице 'Редактирование плана закупки'
    And 'Заказчик' прикрепляет документ в разделе 'Документы' на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет ссылку для скачивания прикрепленного файла "1.txt" в разделе 'Документы' на странице 'Редактирование плана закупки'
    #                 Редактирование плана закупки - Подвал страницы
    And 'Заказчик' переходит к просмотру раздела "Подвал страницы" на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что кнопка "Сохранить черновик" отображается и доступна на странице 'Редактирование плана закупки'
    And 'Заказчик' нажимает на кнопку "Сохранить черновик" на странице 'Редактирование плана закупки'
    And 'Заказчик' нажимает на кнопку "Продолжить (формирование черновика)" в окне диалога 'Сохранено'
    #
    #                 Редактирование плана закупки
    #
    And 'Заказчик' проверяет, что открыта страница "Редактирование плана закупки"
    #                 Редактирование плана закупки - Общие сведения о плане закупки
    And 'Заказчик' переходит к просмотру раздела "Общие сведения о плане закупки" на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что "Наименование" содержит параметр теста "TradePlanName" на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что поле "Заказчик" содержит не пустое значение на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что поле "Вид" содержит значение "План закупки товаров (работ, услуг)" на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет дату в поле "Период C" в "MONTHS" со смещением "+0" от текущей на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет дату в поле "Период По" в "MONTHS" со смещением "+1" от текущей на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет дату в поле "Дата утверждения" в "MONTHS" со смещением "+0" от текущей на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет год в поле "Отчетный год" в "YEARS" со смещением "+0" от текущего на странице 'Редактирование плана закупки'
    #                 Редактирование плана закупки - Импорт позиций
    And 'Заказчик' переходит к просмотру раздела "Импорт позиций" на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что переключатель "Загрузка из Excel-файла" отмечен на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что переключатель "Загрузка с ЕИС" не отмечен на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что кнопка "Загрузить файл" отображается и доступна на странице 'Редактирование плана закупки'
    #                 Редактирование плана закупки - Позиции плана
    And 'Заказчик' переходит к просмотру раздела "Позиции плана" на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет количество импортированных позиций плана закупки - 6 на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что в столбце "Номер" таблицы 'Позиции плана' нет пустых ячеек на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что в столбце "Предмет договора" таблицы 'Позиции плана' нет пустых ячеек на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что в столбце "Начальная цена договора" таблицы 'Позиции плана' нет пустых ячеек на странице 'Редактирование плана закупки'
    #                 Редактирование плана закупки - Документы
    And 'Заказчик' переходит к просмотру раздела "Документы" на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет ссылку для скачивания прикрепленного файла "1.txt" в разделе 'Документы' на странице 'Редактирование плана закупки'
    #                 Редактирование плана закупки - Подвал страницы
    And 'Заказчик' переходит к просмотру раздела "Подвал страницы" на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что кнопка "Сохранить черновик" отображается и доступна на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что кнопка "Отправить в ЕИС" отображается и доступна на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что кнопка "Экспортировать в Excel 2007+" отображается и доступна на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет, что кнопка "Сформировать печатную форму" отображается и доступна на странице 'Редактирование плана закупки'
    And 'Заказчик' проверяет возможность экспортирования в Excel 2007+ плана закупки на странице 'Редактирование плана закупки'
    And 'Пользователь' покидает систему
