package Helpers.UserProfiles;

import LogicLayer.CertificateSelectors.CustomerCertificateSelector;
import LogicLayer.CertificateSelectors.ICertificateSelector;

import java.util.HashMap;
import java.util.Map;

/**
 * Заказчик
 * Updated by Vladimir V. Klochkov on 22.05.2020.
 */
public class CustomerLegalEntityProfile implements IUserProfile {
    private final static Map<Class<?>, Object> LogicClasses = new HashMap<Class<?>, Object>();
    private final static Map<String, String> userData = new HashMap<String, String>();

    static {
        fillClassesMap();
        userData.put("login", "customer_auction");
    }

    private static void fillClassesMap() {
        LogicClasses.put(ICertificateSelector.class, new CustomerCertificateSelector());
    }

    public <T> T getLogic(Class<T> clazz) {
        return (T) LogicClasses.get(clazz);
    }
}
