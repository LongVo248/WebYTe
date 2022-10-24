package mtt.webyte.util;

import java.math.BigDecimal;
import java.util.Date;

public class ObjectUtils {

    public static String stringValue(Object obj) {
        return obj != null ? (String) obj : null;
    }

    public static Long longValue(Object obj) {
        if (obj instanceof BigDecimal) {
            return ((BigDecimal) obj).longValue();
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).longValue();
        }
        if (obj instanceof String) {
            return Long.valueOf((String) obj);
        }
        return obj != null ? (Long) obj : null;
    }

    public static Integer intValue(Object obj) {
        if (obj instanceof BigDecimal) {
            return ((BigDecimal) obj).intValue();
        }
        if (obj instanceof Long) {
            return ((Integer) obj).intValue();
        }
        if (obj instanceof String) {
            return Integer.valueOf((String) obj);
        }
        return obj != null ? (Integer) obj : null;
    }

    public static BigDecimal decimalValue(Object obj) {
        return obj != null ? new BigDecimal(obj.toString()) : null;
    }

    public static Boolean booleanValue(Object obj) {
        return obj != null ? (Boolean) obj : null;
    }

    public static Date dateValue(Object obj) {
        return obj != null ? (Date) obj : null;
    }
}
