
package com.highdeal.ws.charging.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChargingFailureReason.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ChargingFailureReason">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="outOfValidityPeriod"/>
 *     &lt;enumeration value="notAuthorized"/>
 *     &lt;enumeration value="accountBlocked"/>
 *     &lt;enumeration value="accountLocked"/>
 *     &lt;enumeration value="accountClosed"/>
 *     &lt;enumeration value="cannotChargeAccount"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ChargingFailureReason")
@XmlEnum
public enum ChargingFailureReason {

    @XmlEnumValue("outOfValidityPeriod")
    OUT_OF_VALIDITY_PERIOD("outOfValidityPeriod"),
    @XmlEnumValue("notAuthorized")
    NOT_AUTHORIZED("notAuthorized"),
    @XmlEnumValue("accountBlocked")
    ACCOUNT_BLOCKED("accountBlocked"),
    @XmlEnumValue("accountLocked")
    ACCOUNT_LOCKED("accountLocked"),
    @XmlEnumValue("accountClosed")
    ACCOUNT_CLOSED("accountClosed"),
    @XmlEnumValue("cannotChargeAccount")
    CANNOT_CHARGE_ACCOUNT("cannotChargeAccount");
    private final String value;

    ChargingFailureReason(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ChargingFailureReason fromValue(String v) {
        for (ChargingFailureReason c: ChargingFailureReason.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
