package net.contact_hub.contact_hub.controller;

import java.util.ArrayList;
import java.util.List;

// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.contact_hub.contact_hub.bean.Contact;

// @Controller // to make Java class as a spring MVC controller
// @ResponseBody // tells the controller that the object returned is automatically serialized intor JSON and passed back into the HttpResponse object.

@RestController // combination of of both @Controllere and @ResponseBody
public class Contacts_ {
    // http://localhost:8080/contact
    @GetMapping("contact") // Endpoint
    public Contact getContact() {
        Contact contact = new Contact(1, "Michelle Uche", "0544636910");
        return contact;
    }

    @GetMapping("contacts")
    public List<Contact> getContacts() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact(1, "Michelle Uche", "0544636910"));
        contacts.add(new Contact(2, "Mable Oluchi", "0276338139"));
        contacts.add(new Contact(3, "Kofi Frimpong", "0544636910"));
        contacts.add(new Contact(4, "Precious", "0544636910"));
        return contacts;
    }

    // path variable
    @GetMapping("contacts/{id}")
    public Contact getSpecificContact(@PathVariable("id") int contactId) {
        return new Contact(contactId, "Michelle", "0544636910");
    }

    // query parameters
    // http://localhost:8080/contacts/query?id=1
    @GetMapping("contacts/query")
    public Contact getSpecContact(@RequestParam int id) {
        return new Contact(id, "Michelle", "0544636910");
    }
}
