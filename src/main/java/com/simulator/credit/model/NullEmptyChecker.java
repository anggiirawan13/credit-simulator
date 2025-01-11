package com.simulator.credit.model;

import java.util.Collection;
import java.util.Map;

public class NullEmptyChecker {

    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null) {
            return true;
        } else {
            if (obj instanceof String) {
                return ((String) obj).trim().isEmpty();
            } else if (obj instanceof Collection) {
                return ((Collection<?>) obj).isEmpty();
            } else if (obj instanceof Map) {
                return ((Map<?, ?>) obj).isEmpty();
            }
        }

        return false;
    }

    public static boolean isNotNullOrEmpty(Object obj) {
        return !isNullOrEmpty(obj);
    }

}
