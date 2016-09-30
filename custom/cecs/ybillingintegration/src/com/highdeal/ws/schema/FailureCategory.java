
package com.highdeal.ws.schema;

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
 *   &lt;restriction base="{http://schema.ws.highdeal.com/}String256">
 *     &lt;enumeration value="alreadyExists"/>
 *     &lt;enumeration value="doesNotExist"/>
 *     &lt;enumeration value="invalid"/>
 *     &lt;enumeration value="prerequisiteMissing"/>
 *     &lt;enumeration value="incompatibleConfiguration"/>
 *     &lt;enumeration value="illegalState"/>
 *     &lt;enumeration value="temporaryIllegalState"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FailureCategory")
@XmlEnum
public enum FailureCategory {

    @XmlEnumValue("alreadyExists")
    ALREADY_EXISTS("alreadyExists"),
    @XmlEnumValue("doesNotExist")
    DOES_NOT_EXIST("doesNotExist"),
    @XmlEnumValue("invalid")
    INVALID("invalid"),
    @XmlEnumValue("prerequisiteMissing")
    PREREQUISITE_MISSING("prerequisiteMissing"),
    @XmlEnumValue("incompatibleConfiguration")
    INCOMPATIBLE_CONFIGURATION("incompatibleConfiguration"),
    @XmlEnumValue("illegalState")
    ILLEGAL_STATE("illegalState"),
    @XmlEnumValue("temporaryIllegalState")
    TEMPORARY_ILLEGAL_STATE("temporaryIllegalState");
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
