package net.contact_hub.contact_hub.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.contact_hub.contact_hub.dto.ContactDto;
import net.contact_hub.contact_hub.exception.ResourceNotFoundException;
import net.contact_hub.contact_hub.mapper.ContactMapper;
import net.contact_hub.contact_hub.model.Contact;
import net.contact_hub.contact_hub.repository.ContactRepository;
import net.contact_hub.contact_hub.service.ContactService;

/*
 * In a Spring Boot application, the service package is used to contain the service
 * layer of the application. The service layer acts as a bridge 
 * between the controller (which handles HTTP requests and user input) 
 * and the repository (which directly interacts with the database). 
 * It is responsible for implementing business logic, ensuring data integrity, 
 * and managing data processing tasks.
 * 
 * POSTMAN/CLIENT => DTO => CONTROLLER LAYER => SERVICE LAYER => REPOSITORY LAYER => DB
 */

// to create s spring bean for the class
@Service

@AllArgsConstructor
public class ContactServiceImpl implements ContactService {

    private ContactRepository contactRepository;

    @Override
    public ContactDto createContact(ContactDto contactDto) {

        // convert ContactDto to Contact model
        Contact contact = ContactMapper.mapToContact(contactDto);
        // save contact to the database
        Contact savedContact = contactRepository.save(contact);
        // return the bean data / JSON to the client
        return ContactMapper.mapToContactDto(savedContact);
    }

    @Override
    public ContactDto getContactById(Long contactId) {
        Contact contact = contactRepository.findById(contactId).orElseThrow(
                () -> new ResourceNotFoundException("Contact with the id " + contactId + " does not exists"));
        return ContactMapper.mapToContactDto(contact);
    }

    @Override
    public List<ContactDto> getAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
        return contacts.stream().map((contact) -> ContactMapper.mapToContactDto(contact)).collect(Collectors.toList());
    }

    @Override
    public ContactDto updateContact(Long contactId, ContactDto updatedContact) {
        Contact contact = contactRepository.findById(contactId).orElseThrow(
                () -> new ResourceNotFoundException("Contact with the id " + contactId + " does not exists"));

        contact.setFullName(updatedContact.getFullName());
        contact.setPhoneNumber(updatedContact.getPhoneNumber());

        Contact updatedContactObj = contactRepository.save(contact);

        return ContactMapper.mapToContactDto(updatedContactObj);
    }

    @Override
    public void deleteContact(Long contactId) {
        contactRepository.findById(contactId).orElseThrow(
                () -> new ResourceNotFoundException("Contact with the given ID does not exists" + contactId));

        contactRepository.deleteById(contactId);
    }
}
