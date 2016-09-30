
package com.highdeal.ws.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchOrderType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SearchOrderType">
 *   &lt;restriction base="{http://schema.ws.highdeal.com/}String256">
 *     &lt;enumeration value="asc"/>
 *     &lt;enumeration value="desc"/>
 *     &lt;enumeration value="noOrder"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SearchOrderType")
@XmlEnum
public enum SearchOrderType {

    @XmlEnumValue("asc")
    ASC("asc"),
    @XmlEnumValue("desc")
    DESC("desc"),
    @XmlEnumValue("noOrder")
    NO_ORDER("noOrder");
    private final String value;

    SearchOrderType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SearchOrderType fromValue(String v) {
        for (SearchOrderType c: SearchOrderType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
