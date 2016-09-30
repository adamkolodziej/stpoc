
package com.highdeal.ws.charging.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AccountOperationType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AccountOperationType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="debit"/>
 *     &lt;enumeration value="credit"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AccountOperationType")
@XmlEnum
public enum AccountOperationType {

    @XmlEnumValue("debit")
    DEBIT("debit"),
    @XmlEnumValue("credit")
    CREDIT("credit");
    private final String value;

    AccountOperationType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AccountOperationType fromValue(String v) {
        for (AccountOperationType c: AccountOperationType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
