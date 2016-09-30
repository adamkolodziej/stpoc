
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ZcrmBuagCreate.RfcExceptions.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ZcrmBuagCreate.RfcExceptions">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Failed"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ZcrmBuagCreate.RfcExceptions")
@XmlEnum
public enum ZcrmBuagCreateRfcExceptions {

    @XmlEnumValue("Failed")
    FAILED("Failed");
    private final String value;

    ZcrmBuagCreateRfcExceptions(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ZcrmBuagCreateRfcExceptions fromValue(String v) {
        for (ZcrmBuagCreateRfcExceptions c: ZcrmBuagCreateRfcExceptions.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
