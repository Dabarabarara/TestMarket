package Helpers.UserProfiles;

import java.util.HashMap;
import java.util.Map;

/**
 * Фабрика генерации профилей Пользователей ( синглтон ).
 * Created by Dima Makieiev on 20.01.2016.
 * Updated by Vladimir V. Klochkov on 11.02.2019.
 */
public class UserProfileFactory {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private static volatile UserProfileFactory instance;

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса.
     * Делаем конструктор класса приватным, чтобы не было возможности создать экземпляр класса извне.
     ******************************************************************************************************************/
    private UserProfileFactory() {
    }

    /*******************************************************************************************************************
     * Метод доступа к экземпляру этого класса.
     ******************************************************************************************************************/
    /**
     * Возвращает экземпляр этого класса ( синглтон ).
     *
     * @return экземпляр этого класса
     */
    public static UserProfileFactory getInstance() {
        if (instance == null) {
            synchronized (UserProfileFactory.class) {
                if (instance == null) {
                    instance = new UserProfileFactory();
                }
            }
        }
        return instance;
    }

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    public IUserProfile getProfile(String str) {
        Map<String, IUserProfile> userPool = new HashMap<>();
        IUserProfile pr = createProfile(str);

        // В общем, если по строке возвращается профиль юзера и его нет в пуле мы его туда пихаем
        if (!userPool.containsKey(str) && !(pr == null)) {
            userPool.put(str, pr);
        }
        // А вот если он есть в пуле мы его просто возвращаем
        else if (userPool.containsKey(str)) {
            pr = userPool.get(str);
        }
        return pr;
    }

    private IUserProfile createProfile(String profileType) {
        if (profileType == null) return null;

        if (profileType.contains("Заказчик")) {
            return new CustomerLegalEntityProfile();
        } else if (profileType.contains("Поставщик Индивидуальный предприниматель")) {
            return new SupplierEntrepreneurProfile();
        } else if (profileType.contains("Поставщик Юридическое лицо")) {
            return new SupplierLegalEntityProfile();
        } else if (profileType.contains("Поставщик Физическое лицо")) {
            return new SupplierIndividualProfile();
        } else if (profileType.contains("Администратор")) {
            return new OperatorProfile();
        }
        return null;
    }
}
