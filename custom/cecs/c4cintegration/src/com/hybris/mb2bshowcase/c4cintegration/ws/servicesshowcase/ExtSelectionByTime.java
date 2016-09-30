
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ExtSelectionByTime complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExtSelectionByTime">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SelectionByTime" type="{http://sap.com/xi/AP/Common/Global}SelectionByTime" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtSelectionByTime", namespace = "http://sap.com/xi/Extensibility/Global", propOrder = {
    "selectionByTime"
})
public class ExtSelectionByTime {

    @XmlElement(name = "SelectionByTime")
    protected List<SelectionByTime> selectionByTime;

    /**
     * Gets the value of the selectionByTime property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByTime property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByTime().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByTime }
     * 
     * 
     */
    public List<SelectionByTime> getSelectionByTime() {
        if (selectionByTime == null) {
            selectionByTime = new ArrayList<SelectionByTime>();
        }
        return this.selectionByTime;
    }

}
