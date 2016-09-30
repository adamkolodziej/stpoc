
package com.hybris.mb2bshowcase.c4cintegration.ws.servicesshowcase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for ServiceRequestByElementsServicePointLocation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceRequestByElementsServicePointLocation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CountryCode" type="{http://sap.com/xi/AP/Common/GDT}CountryCode"/>
 *         &lt;element name="RegionCode" type="{http://sap.com/xi/AP/Common/GDT}RegionCode" minOccurs="0"/>
 *         &lt;element name="CityName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_MEDIUM_Name" minOccurs="0"/>
 *         &lt;element name="StreetPostalCode" type="{http://sap.com/xi/AP/Common/GDT}PostalCode" minOccurs="0"/>
 *         &lt;element name="StreetName" type="{http://sap.com/xi/AP/Common/GDT}StreetName" minOccurs="0"/>
 *         &lt;element name="HouseID" type="{http://sap.com/xi/AP/Common/GDT}HouseID" minOccurs="0"/>
 *         &lt;element name="BuildingID" type="{http://sap.com/xi/AP/Common/GDT}BuildingID" minOccurs="0"/>
 *         &lt;element name="RoomID" type="{http://sap.com/xi/AP/Common/GDT}RoomID" minOccurs="0"/>
 *         &lt;element name="FloorID" type="{http://sap.com/xi/AP/Common/GDT}FloorID" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceRequestByElementsServicePointLocation", propOrder = {
    "countryCode",
    "regionCode",
    "cityName",
    "streetPostalCode",
    "streetName",
    "houseID",
    "buildingID",
    "roomID",
    "floorID"
})
public class ServiceRequestByElementsServicePointLocation {

    @XmlElement(name = "CountryCode", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String countryCode;
    @XmlElement(name = "RegionCode")
    protected RegionCode regionCode;
    @XmlElement(name = "CityName")
    protected String cityName;
    @XmlElement(name = "StreetPostalCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String streetPostalCode;
    @XmlElement(name = "StreetName")
    protected String streetName;
    @XmlElement(name = "HouseID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String houseID;
    @XmlElement(name = "BuildingID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String buildingID;
    @XmlElement(name = "RoomID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String roomID;
    @XmlElement(name = "FloorID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String floorID;

    /**
     * Gets the value of the countryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets the value of the countryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryCode(String value) {
        this.countryCode = value;
    }

    /**
     * Gets the value of the regionCode property.
     * 
     * @return
     *     possible object is
     *     {@link RegionCode }
     *     
     */
    public RegionCode getRegionCode() {
        return regionCode;
    }

    /**
     * Sets the value of the regionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegionCode }
     *     
     */
    public void setRegionCode(RegionCode value) {
        this.regionCode = value;
    }

    /**
     * Gets the value of the cityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Sets the value of the cityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityName(String value) {
        this.cityName = value;
    }

    /**
     * Gets the value of the streetPostalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetPostalCode() {
        return streetPostalCode;
    }

    /**
     * Sets the value of the streetPostalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetPostalCode(String value) {
        this.streetPostalCode = value;
    }

    /**
     * Gets the value of the streetName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * Sets the value of the streetName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetName(String value) {
        this.streetName = value;
    }

    /**
     * Gets the value of the houseID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseID() {
        return houseID;
    }

    /**
     * Sets the value of the houseID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseID(String value) {
        this.houseID = value;
    }

    /**
     * Gets the value of the buildingID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuildingID() {
        return buildingID;
    }

    /**
     * Sets the value of the buildingID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuildingID(String value) {
        this.buildingID = value;
    }

    /**
     * Gets the value of the roomID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomID() {
        return roomID;
    }

    /**
     * Sets the value of the roomID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomID(String value) {
        this.roomID = value;
    }

    /**
     * Gets the value of the floorID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFloorID() {
        return floorID;
    }

    /**
     * Sets the value of the floorID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFloorID(String value) {
        this.floorID = value;
    }

}
