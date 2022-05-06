package com.optimagrowth.license.service;

import java.util.Locale;
import java.util.Random;

import com.optimagrowth.license.model.License;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class licenseService {
    @Autowired
    MessageSource messages;

    public License getLicense(String licenseId, String organisationId){
        License license = new License();
        license.setId(new Random().nextInt(1000));
        license.setLicenseId(licenseId);
        license.setOrganisationId(organisationId);
        license.setDescription("Software product");
        license.setProductName("Ostock");
        license.setLicenseType("full");
        return license;
    }

    public String createLicense(License license, String organisationId, Locale locale) {
        String responseMessage = null;
        if (license != null) {
            license.setOrganisationId(organisationId);
            responseMessage = String.format(messages.getMessage("license.create.message", null, locale), license.toString());
        }
        return responseMessage;
    }

    public String updateLicense(License license, String organisationId){
        String responseMessage = null;
        if (license != null) {
           license.setOrganisationId(organisationId);
           responseMessage = String.format(messages.getMessage("license.update.message", null, null), license.toString());
        }
  
        return responseMessage;
     }
  
     public String deleteLicense(String licenseId, String organisationId){
        String responseMessage = null;
        responseMessage = String.format(messages.getMessage("license.delete.message",null,null),licenseId, organisationId);
        return responseMessage;
  
     }
    
}
