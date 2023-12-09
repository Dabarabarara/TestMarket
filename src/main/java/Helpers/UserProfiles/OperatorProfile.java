package Helpers.UserProfiles;

import LogicLayer.CertificateSelectors.ICertificateSelector;
import LogicLayer.CertificateSelectors.OperatorCertificateSelector;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dima Makieiev on 23.01.2016.
 * Updated by Vladimir V. Klochkov on 22.05.2020.
 */
public class OperatorProfile implements IUserProfile {
    private final static Map<Class<?>, Object> LogicClasses = new HashMap<Class<?>, Object>();

    static {
        fillClassesMap();
    }

    private static void fillClassesMap() {
        LogicClasses.put(ICertificateSelector.class, new OperatorCertificateSelector());
    }

    public <T> T getLogic(Class<T> clazz) {
        return (T) LogicClasses.get(clazz);
    }
}