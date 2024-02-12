
package com.project;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.project package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Hello_QNAME = new QName("http://project.com/", "hello");
    private final static QName _GetAvailableSeatsResponse_QNAME = new QName("http://project.com/", "getAvailableSeatsResponse");
    private final static QName _GetAvailableSeats_QNAME = new QName("http://project.com/", "getAvailableSeats");
    private final static QName _HelloResponse_QNAME = new QName("http://project.com/", "helloResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.project
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAvailableSeats }
     * 
     */
    public GetAvailableSeats createGetAvailableSeats() {
        return new GetAvailableSeats();
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
    }

    /**
     * Create an instance of {@link GetAvailableSeatsResponse }
     * 
     */
    public GetAvailableSeatsResponse createGetAvailableSeatsResponse() {
        return new GetAvailableSeatsResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://project.com/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAvailableSeatsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://project.com/", name = "getAvailableSeatsResponse")
    public JAXBElement<GetAvailableSeatsResponse> createGetAvailableSeatsResponse(GetAvailableSeatsResponse value) {
        return new JAXBElement<GetAvailableSeatsResponse>(_GetAvailableSeatsResponse_QNAME, GetAvailableSeatsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAvailableSeats }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://project.com/", name = "getAvailableSeats")
    public JAXBElement<GetAvailableSeats> createGetAvailableSeats(GetAvailableSeats value) {
        return new JAXBElement<GetAvailableSeats>(_GetAvailableSeats_QNAME, GetAvailableSeats.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://project.com/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

}
