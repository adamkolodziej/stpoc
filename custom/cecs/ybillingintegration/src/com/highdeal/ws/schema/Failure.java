
package com.highdeal.ws.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Failure complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Failure">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="category" type="{http://schema.ws.highdeal.com/}FailureCategory" minOccurs="0"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cause" type="{http://schema.ws.highdeal.com/}FailureCause" minOccurs="0"/>
 *         &lt;element name="causedBy" type="{http://schema.ws.highdeal.com/}FailureCause" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Failure", propOrder = {
    "category",
    "message",
    "cause",
    "causedBy"
})
public class Failure {

    @XmlSchemaType(name = "string")
    protected FailureCategory category;
    protected String message;
    protected FailureCause cause;
    protected List<FailureCause> causedBy;

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link FailureCategory }
     *     
     */
    public FailureCategory getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link FailureCategory }
     *     
     */
    public void setCategory(FailureCategory value) {
        this.category = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the cause property.
     * 
     * @return
     *     possible object is
     *     {@link FailureCause }
     *     
     */
    public FailureCause getCause() {
        return cause;
    }

    /**
     * Sets the value of the cause property.
     * 
     * @param value
     *     allowed object is
     *     {@link FailureCause }
     *     
     */
    public void setCause(FailureCause value) {
        this.cause = value;
    }

    /**
     * Gets the value of the causedBy property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the causedBy property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCausedBy().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FailureCause }
     * 
     * 
     */
    public List<FailureCause> getCausedBy() {
        if (causedBy == null) {
            causedBy = new ArrayList<FailureCause>();
        }
        return this.causedBy;
    }

}
