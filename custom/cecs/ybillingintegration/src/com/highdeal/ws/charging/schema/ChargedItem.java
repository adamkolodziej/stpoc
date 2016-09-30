
package com.highdeal.ws.charging.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChargedItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChargedItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="chargedItemClassId" type="{http://schema.ws.highdeal.com/}String256"/>
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="field" type="{http://schema.charging.ws.highdeal.com/}Field" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChargedItem", propOrder = {
    "chargedItemClassId",
    "key",
    "field"
})
public class ChargedItem {

    @XmlElement(required = true)
    protected String chargedItemClassId;
    protected int key;
    protected List<Field> field;

    /**
     * Gets the value of the chargedItemClassId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargedItemClassId() {
        return chargedItemClassId;
    }

    /**
     * Sets the value of the chargedItemClassId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargedItemClassId(String value) {
        this.chargedItemClassId = value;
    }

    /**
     * Gets the value of the key property.
     * 
     */
    public int getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     * 
     */
    public void setKey(int value) {
        this.key = value;
    }

    /**
     * Gets the value of the field property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the field property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getField().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Field }
     * 
     * 
     */
    public List<Field> getField() {
        if (field == null) {
            field = new ArrayList<Field>();
        }
        return this.field;
    }

}
