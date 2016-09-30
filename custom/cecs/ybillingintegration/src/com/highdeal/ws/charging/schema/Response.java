
package com.highdeal.ws.charging.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Response complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Response">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schema.charging.ws.highdeal.com/}BaseResponse">
 *       &lt;sequence>
 *         &lt;element name="error" type="{http://schema.charging.ws.highdeal.com/}Failure" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Response", propOrder = {
    "error"
})
@XmlSeeAlso({
    ChargeableItemChargeBundleResponse.class,
    ChargeableItemChargeMassResponse.class
})
public class Response
    extends BaseResponse
{

    protected Failure error;

    /**
     * Gets the value of the error property.
     * 
     * @return
     *     possible object is
     *     {@link Failure }
     *     
     */
    public Failure getError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     * 
     * @param value
     *     allowed object is
     *     {@link Failure }
     *     
     */
    public void setError(Failure value) {
        this.error = value;
    }

}
