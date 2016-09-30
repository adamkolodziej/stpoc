
package com.highdeal.ws.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchFilterType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SearchFilterType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="boolean"/>
 *     &lt;enumeration value="number"/>
 *     &lt;enumeration value="string"/>
 *     &lt;enumeration value="date"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SearchFilterType")
@XmlEnum
public enum SearchFilterType {

    @XmlEnumValue("boolean")
    BOOLEAN("boolean"),
    @XmlEnumValue("number")
    NUMBER("number"),
    @XmlEnumValue("string")
    STRING("string"),
    @XmlEnumValue("date")
    DATE("date");
    private final String value;

    SearchFilterType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SearchFilterType fromValue(String v) {
        for (SearchFilterType c: SearchFilterType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
