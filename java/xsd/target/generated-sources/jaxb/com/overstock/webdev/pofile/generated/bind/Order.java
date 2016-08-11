//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.05 at 04:26:52 PM IST 
//


package com.overstock.webdev.pofile.generated.bind;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{}OrderId"/&gt;
 *         &lt;element ref="{}OrderDate"/&gt;
 *         &lt;element ref="{}DomesticBasket"/&gt;
 *         &lt;element ref="{}COPShippingMethod" minOccurs="0"/&gt;
 *         &lt;element ref="{}DomesticProfile"/&gt;
 *         &lt;element ref="{}TPLShippingMethod" minOccurs="0"/&gt;
 *         &lt;element ref="{}InternationalProfile" minOccurs="0"/&gt;
 *         &lt;element ref="{}CreditCard"/&gt;
 *         &lt;element ref="{}Marketing" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "orderId",
    "orderDate",
    "domesticBasket",
    "copShippingMethod",
    "domesticProfile",
    "tplShippingMethod",
    "internationalProfile",
    "creditCard",
    "marketing"
})
@XmlRootElement(name = "Order")
public class Order {

    @XmlElement(name = "OrderId", required = true)
    protected OrderId orderId;
    @XmlElement(name = "OrderDate", required = true)
    protected OrderDate orderDate;
    @XmlElement(name = "DomesticBasket", required = true)
    protected DomesticBasket domesticBasket;
    @XmlElement(name = "COPShippingMethod")
    protected DeliveryServiceTypeRef copShippingMethod;
    @XmlElement(name = "DomesticProfile", required = true)
    protected DomesticProfile domesticProfile;
    @XmlElement(name = "TPLShippingMethod")
    protected DeliveryServiceTypeRef tplShippingMethod;
    @XmlElement(name = "InternationalProfile")
    protected InternationalProfile internationalProfile;
    @XmlElement(name = "CreditCard", required = true)
    protected CreditCard creditCard;
    @XmlElement(name = "Marketing")
    protected Marketing marketing;

    /**
     * Gets the value of the orderId property.
     * 
     * @return
     *     possible object is
     *     {@link OrderId }
     *     
     */
    public OrderId getOrderId() {
        return orderId;
    }

    /**
     * Sets the value of the orderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderId }
     *     
     */
    public void setOrderId(OrderId value) {
        this.orderId = value;
    }

    /**
     * Gets the value of the orderDate property.
     * 
     * @return
     *     possible object is
     *     {@link OrderDate }
     *     
     */
    public OrderDate getOrderDate() {
        return orderDate;
    }

    /**
     * Sets the value of the orderDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderDate }
     *     
     */
    public void setOrderDate(OrderDate value) {
        this.orderDate = value;
    }

    /**
     * Gets the value of the domesticBasket property.
     * 
     * @return
     *     possible object is
     *     {@link DomesticBasket }
     *     
     */
    public DomesticBasket getDomesticBasket() {
        return domesticBasket;
    }

    /**
     * Sets the value of the domesticBasket property.
     * 
     * @param value
     *     allowed object is
     *     {@link DomesticBasket }
     *     
     */
    public void setDomesticBasket(DomesticBasket value) {
        this.domesticBasket = value;
    }

    /**
     * Gets the value of the copShippingMethod property.
     * 
     * @return
     *     possible object is
     *     {@link DeliveryServiceTypeRef }
     *     
     */
    public DeliveryServiceTypeRef getCOPShippingMethod() {
        return copShippingMethod;
    }

    /**
     * Sets the value of the copShippingMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeliveryServiceTypeRef }
     *     
     */
    public void setCOPShippingMethod(DeliveryServiceTypeRef value) {
        this.copShippingMethod = value;
    }

    /**
     * Gets the value of the domesticProfile property.
     * 
     * @return
     *     possible object is
     *     {@link DomesticProfile }
     *     
     */
    public DomesticProfile getDomesticProfile() {
        return domesticProfile;
    }

    /**
     * Sets the value of the domesticProfile property.
     * 
     * @param value
     *     allowed object is
     *     {@link DomesticProfile }
     *     
     */
    public void setDomesticProfile(DomesticProfile value) {
        this.domesticProfile = value;
    }

    /**
     * Gets the value of the tplShippingMethod property.
     * 
     * @return
     *     possible object is
     *     {@link DeliveryServiceTypeRef }
     *     
     */
    public DeliveryServiceTypeRef getTPLShippingMethod() {
        return tplShippingMethod;
    }

    /**
     * Sets the value of the tplShippingMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeliveryServiceTypeRef }
     *     
     */
    public void setTPLShippingMethod(DeliveryServiceTypeRef value) {
        this.tplShippingMethod = value;
    }

    /**
     * Gets the value of the internationalProfile property.
     * 
     * @return
     *     possible object is
     *     {@link InternationalProfile }
     *     
     */
    public InternationalProfile getInternationalProfile() {
        return internationalProfile;
    }

    /**
     * Sets the value of the internationalProfile property.
     * 
     * @param value
     *     allowed object is
     *     {@link InternationalProfile }
     *     
     */
    public void setInternationalProfile(InternationalProfile value) {
        this.internationalProfile = value;
    }

    /**
     * Gets the value of the creditCard property.
     * 
     * @return
     *     possible object is
     *     {@link CreditCard }
     *     
     */
    public CreditCard getCreditCard() {
        return creditCard;
    }

    /**
     * Sets the value of the creditCard property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditCard }
     *     
     */
    public void setCreditCard(CreditCard value) {
        this.creditCard = value;
    }

    /**
     * Gets the value of the marketing property.
     * 
     * @return
     *     possible object is
     *     {@link Marketing }
     *     
     */
    public Marketing getMarketing() {
        return marketing;
    }

    /**
     * Sets the value of the marketing property.
     * 
     * @param value
     *     allowed object is
     *     {@link Marketing }
     *     
     */
    public void setMarketing(Marketing value) {
        this.marketing = value;
    }

}
