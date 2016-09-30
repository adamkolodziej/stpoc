
package com.highdeal.ws.charging.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChargingResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChargingResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schema.charging.ws.highdeal.com/}BaseResponse">
 *       &lt;sequence>
 *         &lt;element name="result" type="{http://schema.charging.ws.highdeal.com/}ChargingResult" minOccurs="0"/>
 *         &lt;element name="error" type="{http://schema.charging.ws.highdeal.com/}ChargingFailure" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChargingResponse", propOrder = {
    "result",
    "error"
})
@XmlSeeAlso({
    ChargeableItemChargeResponse.class,
    ChargeableItemCheckChargeResponse.class
})
public class ChargingResponse
    extends BaseResponse
{

    protected ChargingResult result;
    protected ChargingFailure error;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link ChargingResult }
     *     
     */
    public ChargingResult getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargingResult }
     *     
     */
    public void setResult(ChargingResult value) {
        this.result = value;
    }

    /**
     * Gets the value of the error property.
     * 
     * @return
     *     possible object is
     *     {@link ChargingFailure }
     *     
     */
    public ChargingFailure getError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargingFailure }
     *     
     */
    public void setError(ChargingFailure value) {
        this.error = value;
    }

}
