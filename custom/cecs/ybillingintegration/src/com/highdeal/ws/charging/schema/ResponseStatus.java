
package com.highdeal.ws.charging.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResponseStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ResponseStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="return"/>
 *     &lt;enumeration value="error"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ResponseStatus")
@XmlEnum
public enum ResponseStatus {

    @XmlEnumValue("return")
    RETURN("return"),
    @XmlEnumValue("error")
    ERROR("error");
    private final String value;

    ResponseStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ResponseStatus fromValue(String v) {
        for (ResponseStatus c: ResponseStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
