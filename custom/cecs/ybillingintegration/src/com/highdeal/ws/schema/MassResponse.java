
package com.highdeal.ws.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MassResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MassResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schema.ws.highdeal.com/}Response">
 *       &lt;sequence>
 *         &lt;element name="failures" type="{http://schema.ws.highdeal.com/}Integer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MassResponse", propOrder = {
    "failures"
})
public class MassResponse
    extends Response
{

    protected Integer failures;

    /**
     * Gets the value of the failures property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFailures() {
        return failures;
    }

    /**
     * Sets the value of the failures property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFailures(Integer value) {
        this.failures = value;
    }

}
