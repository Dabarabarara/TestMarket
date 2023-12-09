package Helpers.UserProfiles;

import LogicLayer.CertificateSelectors.ICertificateSelector;
import LogicLayer.CertificateSelectors.SupplierCertificateSelector;

import java.util.HashMap;
import java.util.Map;

/**
 * Поставщик Индивидуальный предприниматель
 * Updated by Vladimir V. Klochkov on 22.05.2020.
 */
public class SupplierEntrepreneurProfile implements IUserProfile {
    private final static Map<Class<?>, Object> LogicClasses = new HashMap<Class<?>, Object>();
    private final static Map<String, String> userData = new HashMap<String, String>();

    static {
        fillClassesMap();
        userData.put("login", "auction_supplier_1");
    }

    private static void fillClassesMap() {
        LogicClasses.put(ICertificateSelector.class, new SupplierCertificateSelector());
    }

    public <T> T getLogic(Class<T> clazz) {
        return (T) LogicClasses.get(clazz);
    }
}
