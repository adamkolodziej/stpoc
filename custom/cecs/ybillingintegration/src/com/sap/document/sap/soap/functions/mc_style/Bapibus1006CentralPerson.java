
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Bapibus1006CentralPerson complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Bapibus1006CentralPerson">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Firstname" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="Lastname" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="Birthname" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="Middlename" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="Secondname" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="TitleAca1" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="TitleAca2" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="TitleSppl" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Prefix1" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Prefix2" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Nickname" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="Initials" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Nameformat" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Namcountry" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Namcountryiso" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Sex" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Birthplace" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="Birthdate" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="Deathdate" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="Maritalstatus" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Correspondlanguage" type="{urn:sap-com:document:sap:rfc:functions}lang"/>
 *         &lt;element name="Correspondlanguageiso" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Fullname" type="{urn:sap-com:document:sap:rfc:functions}char80"/>
 *         &lt;element name="Employer" type="{urn:sap-com:document:sap:rfc:functions}char35"/>
 *         &lt;element name="Occupation" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Nationality" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Nationalityiso" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Countryorigin" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Bapibus1006CentralPerson", propOrder = {
    "firstname",
    "lastname",
    "birthname",
    "middlename",
    "secondname",
    "titleAca1",
    "titleAca2",
    "titleSppl",
    "prefix1",
    "prefix2",
    "nickname",
    "initials",
    "nameformat",
    "namcountry",
    "namcountryiso",
    "sex",
    "birthplace",
    "birthdate",
    "deathdate",
    "maritalstatus",
    "correspondlanguage",
    "correspondlanguageiso",
    "fullname",
    "employer",
    "occupation",
    "nationality",
    "nationalityiso",
    "countryorigin"
})
public class Bapibus1006CentralPerson {

    @XmlElement(name = "Firstname", required = true)
    protected String firstname;
    @XmlElement(name = "Lastname", required = true)
    protected String lastname;
    @XmlElement(name = "Birthname", required = true)
    protected String birthname;
    @XmlElement(name = "Middlename", required = true)
    protected String middlename;
    @XmlElement(name = "Secondname", required = true)
    protected String secondname;
    @XmlElement(name = "TitleAca1", required = true)
    protected String titleAca1;
    @XmlElement(name = "TitleAca2", required = true)
    protected String titleAca2;
    @XmlElement(name = "TitleSppl", required = true)
    protected String titleSppl;
    @XmlElement(name = "Prefix1", required = true)
    protected String prefix1;
    @XmlElement(name = "Prefix2", required = true)
    protected String prefix2;
    @XmlElement(name = "Nickname", required = true)
    protected String nickname;
    @XmlElement(name = "Initials", required = true)
    protected String initials;
    @XmlElement(name = "Nameformat", required = true)
    protected String nameformat;
    @XmlElement(name = "Namcountry", required = true)
    protected String namcountry;
    @XmlElement(name = "Namcountryiso", required = true)
    protected String namcountryiso;
    @XmlElement(name = "Sex", required = true)
    protected String sex;
    @XmlElement(name = "Birthplace", required = true)
    protected String birthplace;
    @XmlElement(name = "Birthdate", required = true)
    protected String birthdate;
    @XmlElement(name = "Deathdate", required = true)
    protected String deathdate;
    @XmlElement(name = "Maritalstatus", required = true)
    protected String maritalstatus;
    @XmlElement(name = "Correspondlanguage", required = true)
    protected String correspondlanguage;
    @XmlElement(name = "Correspondlanguageiso", required = true)
    protected String correspondlanguageiso;
    @XmlElement(name = "Fullname", required = true)
    protected String fullname;
    @XmlElement(name = "Employer", required = true)
    protected String employer;
    @XmlElement(name = "Occupation", required = true)
    protected String occupation;
    @XmlElement(name = "Nationality", required = true)
    protected String nationality;
    @XmlElement(name = "Nationalityiso", required = true)
    protected String nationalityiso;
    @XmlElement(name = "Countryorigin", required = true)
    protected String countryorigin;

    /**
     * Gets the value of the firstname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets the value of the firstname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstname(String value) {
        this.firstname = value;
    }

    /**
     * Gets the value of the lastname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets the value of the lastname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastname(String value) {
        this.lastname = value;
    }

    /**
     * Gets the value of the birthname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthname() {
        return birthname;
    }

    /**
     * Sets the value of the birthname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthname(String value) {
        this.birthname = value;
    }

    /**
     * Gets the value of the middlename property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiddlename() {
        return middlename;
    }

    /**
     * Sets the value of the middlename property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiddlename(String value) {
        this.middlename = value;
    }

    /**
     * Gets the value of the secondname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondname() {
        return secondname;
    }

    /**
     * Sets the value of the secondname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondname(String value) {
        this.secondname = value;
    }

    /**
     * Gets the value of the titleAca1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitleAca1() {
        return titleAca1;
    }

    /**
     * Sets the value of the titleAca1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitleAca1(String value) {
        this.titleAca1 = value;
    }

    /**
     * Gets the value of the titleAca2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitleAca2() {
        return titleAca2;
    }

    /**
     * Sets the value of the titleAca2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitleAca2(String value) {
        this.titleAca2 = value;
    }

    /**
     * Gets the value of the titleSppl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitleSppl() {
        return titleSppl;
    }

    /**
     * Sets the value of the titleSppl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitleSppl(String value) {
        this.titleSppl = value;
    }

    /**
     * Gets the value of the prefix1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrefix1() {
        return prefix1;
    }

    /**
     * Sets the value of the prefix1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrefix1(String value) {
        this.prefix1 = value;
    }

    /**
     * Gets the value of the prefix2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrefix2() {
        return prefix2;
    }

    /**
     * Sets the value of the prefix2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrefix2(String value) {
        this.prefix2 = value;
    }

    /**
     * Gets the value of the nickname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Sets the value of the nickname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNickname(String value) {
        this.nickname = value;
    }

    /**
     * Gets the value of the initials property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInitials() {
        return initials;
    }

    /**
     * Sets the value of the initials property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInitials(String value) {
        this.initials = value;
    }

    /**
     * Gets the value of the nameformat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameformat() {
        return nameformat;
    }

    /**
     * Sets the value of the nameformat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameformat(String value) {
        this.nameformat = value;
    }

    /**
     * Gets the value of the namcountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNamcountry() {
        return namcountry;
    }

    /**
     * Sets the value of the namcountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNamcountry(String value) {
        this.namcountry = value;
    }

    /**
     * Gets the value of the namcountryiso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNamcountryiso() {
        return namcountryiso;
    }

    /**
     * Sets the value of the namcountryiso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNamcountryiso(String value) {
        this.namcountryiso = value;
    }

    /**
     * Gets the value of the sex property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSex() {
        return sex;
    }

    /**
     * Sets the value of the sex property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSex(String value) {
        this.sex = value;
    }

    /**
     * Gets the value of the birthplace property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthplace() {
        return birthplace;
    }

    /**
     * Sets the value of the birthplace property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthplace(String value) {
        this.birthplace = value;
    }

    /**
     * Gets the value of the birthdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthdate() {
        return birthdate;
    }

    /**
     * Sets the value of the birthdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthdate(String value) {
        this.birthdate = value;
    }

    /**
     * Gets the value of the deathdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeathdate() {
        return deathdate;
    }

    /**
     * Sets the value of the deathdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeathdate(String value) {
        this.deathdate = value;
    }

    /**
     * Gets the value of the maritalstatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaritalstatus() {
        return maritalstatus;
    }

    /**
     * Sets the value of the maritalstatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaritalstatus(String value) {
        this.maritalstatus = value;
    }

    /**
     * Gets the value of the correspondlanguage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorrespondlanguage() {
        return correspondlanguage;
    }

    /**
     * Sets the value of the correspondlanguage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorrespondlanguage(String value) {
        this.correspondlanguage = value;
    }

    /**
     * Gets the value of the correspondlanguageiso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorrespondlanguageiso() {
        return correspondlanguageiso;
    }

    /**
     * Sets the value of the correspondlanguageiso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorrespondlanguageiso(String value) {
        this.correspondlanguageiso = value;
    }

    /**
     * Gets the value of the fullname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * Sets the value of the fullname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFullname(String value) {
        this.fullname = value;
    }

    /**
     * Gets the value of the employer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployer() {
        return employer;
    }

    /**
     * Sets the value of the employer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployer(String value) {
        this.employer = value;
    }

    /**
     * Gets the value of the occupation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * Sets the value of the occupation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOccupation(String value) {
        this.occupation = value;
    }

    /**
     * Gets the value of the nationality property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Sets the value of the nationality property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNationality(String value) {
        this.nationality = value;
    }

    /**
     * Gets the value of the nationalityiso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNationalityiso() {
        return nationalityiso;
    }

    /**
     * Sets the value of the nationalityiso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNationalityiso(String value) {
        this.nationalityiso = value;
    }

    /**
     * Gets the value of the countryorigin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryorigin() {
        return countryorigin;
    }

    /**
     * Sets the value of the countryorigin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryorigin(String value) {
        this.countryorigin = value;
    }

}
