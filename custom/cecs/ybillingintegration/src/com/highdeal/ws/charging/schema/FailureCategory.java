
package com.highdeal.ws.charging.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FailureCategory.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="FailureCategory">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="illegalState"/>
 *     &lt;enumeration value="invalid"/>
 *     &lt;enumeration value="invalidConfiguration"/>
 *     &lt;enumeration value="prerequisiteMissing"/>
 *     &lt;enumeration value="temporaryIllegalSystemState"/>
 *     &lt;enumeration value="illegalSystemState"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FailureCategory")
@XmlEnum
public enum FailureCategory {

    @XmlEnumValue("illegalState")
    ILLEGAL_STATE("illegalState"),
    @XmlEnumValue("invalid")
    INVALID("invalid"),
    @XmlEnumValue("invalidConfiguration")
    INVALID_CONFIGURATION("invalidConfiguration"),
    @XmlEnumValue("prerequisiteMissing")
    PREREQUISITE_MISSING("prerequisiteMissing"),
    @XmlEnumValue("temporaryIllegalSystemState")
    TEMPORARY_ILLEGAL_SYSTEM_STATE("temporaryIllegalSystemState"),
    @XmlEnumValue("illegalSystemState")
    ILLEGAL_SYSTEM_STATE("illegalSystemState");
    private final String value;

    FailureCategory(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FailureCategory fromValue(String v) {
        for (FailureCategory c: FailureCategory.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
