
package com.highdeal.ws.charging.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChargeableItemChargeBundleResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChargeableItemChargeBundleResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schema.charging.ws.highdeal.com/}Response">
 *       &lt;sequence>
 *         &lt;element name="chargeableItemChargeResponse" type="{http://schema.charging.ws.highdeal.com/}ChargeableItemChargeResponse" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChargeableItemChargeBundleResponse", propOrder = {
    "chargeableItemChargeResponse"
})
public class ChargeableItemChargeBundleResponse
    extends Response
{

    protected List<ChargeableItemChargeResponse> chargeableItemChargeResponse;

    /**
     * Gets the value of the chargeableItemChargeResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the chargeableItemChargeResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChargeableItemChargeResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChargeableItemChargeResponse }
     * 
     * 
     */
    public List<ChargeableItemChargeResponse> getChargeableItemChargeResponse() {
        if (chargeableItemChargeResponse == null) {
            chargeableItemChargeResponse = new ArrayList<ChargeableItemChargeResponse>();
        }
        return this.chargeableItemChargeResponse;
    }

}
