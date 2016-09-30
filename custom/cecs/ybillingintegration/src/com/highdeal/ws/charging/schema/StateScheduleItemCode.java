
package com.highdeal.ws.charging.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StateScheduleItemCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="StateScheduleItemCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="blocked"/>
 *     &lt;enumeration value="locked"/>
 *     &lt;enumeration value="closed"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "StateScheduleItemCode")
@XmlEnum
public enum StateScheduleItemCode {

    @XmlEnumValue("blocked")
    BLOCKED("blocked"),
    @XmlEnumValue("locked")
    LOCKED("locked"),
    @XmlEnumValue("closed")
    CLOSED("closed");
    private final String value;

    StateScheduleItemCode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StateScheduleItemCode fromValue(String v) {
        for (StateScheduleItemCode c: StateScheduleItemCode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
