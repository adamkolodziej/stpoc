
package com.highdeal.ws.charging.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AccountReferenceType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AccountReferenceType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="external"/>
 *     &lt;enumeration value="prepaid"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AccountReferenceType")
@XmlEnum
public enum AccountReferenceType {

    @XmlEnumValue("external")
    EXTERNAL("external"),
    @XmlEnumValue("prepaid")
    PREPAID("prepaid");
    private final String value;

    AccountReferenceType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AccountReferenceType fromValue(String v) {
        for (AccountReferenceType c: AccountReferenceType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
