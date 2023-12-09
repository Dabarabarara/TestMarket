package Helpers.UserProfiles;

import LogicLayer.CertificateSelectors.ICertificateSelector;
import LogicLayer.CertificateSelectors.SupplierCertificateSelector;

import java.util.HashMap;
import java.util.Map;

/**
 * Поставщик Физическое Лицо
 * Updated by Vladimir V. Klochkov on 22.05.2020.
 */
public class SupplierIndividualProfile implements IUserProfile {
    private final static Map<Class<?>, Object> LogicClasses = new HashMap<Class<?>, Object>();

    static {
        fillClassesMap();
    }

    private static void fillClassesMap() {
        LogicClasses.put(ICertificateSelector.class, new SupplierCertificateSelector());
    }

    public <T> T getLogic(Class<T> clazz) {
        return (T) LogicClasses.get(clazz);
    }
}
