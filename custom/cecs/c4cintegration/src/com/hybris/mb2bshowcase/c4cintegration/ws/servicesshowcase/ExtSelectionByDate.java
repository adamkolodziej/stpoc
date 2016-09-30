
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ExtSelectionByDate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExtSelectionByDate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SelectionByDate" type="{http://sap.com/xi/AP/Common/Global}SelectionByDate" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtSelectionByDate", namespace = "http://sap.com/xi/Extensibility/Global", propOrder = {
    "selectionByDate"
})
public class ExtSelectionByDate {

    @XmlElement(name = "SelectionByDate")
    protected List<SelectionByDate> selectionByDate;

    /**
     * Gets the value of the selectionByDate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByDate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByDate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByDate }
     * 
     * 
     */
    public List<SelectionByDate> getSelectionByDate() {
        if (selectionByDate == null) {
            selectionByDate = new ArrayList<SelectionByDate>();
        }
        return this.selectionByDate;
    }

}
