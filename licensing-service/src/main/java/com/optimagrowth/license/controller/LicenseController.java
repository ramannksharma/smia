package com.optimagrowth.license.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.util.Locale;

import com.optimagrowth.license.model.License;
import com.optimagrowth.license.service.licenseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "v1/organisation/{organisationId}/license")
public class LicenseController {

    @Autowired
    private licenseService licenseService;

    @GetMapping("/{licenseId}")
	public ResponseEntity<License> getLicense(
        @PathVariable("organisationId") String organisationId,
        @PathVariable("licenseId") String licenseId
    ) {
        License license = licenseService.getLicense(licenseId, organisationId);
        license.add(linkTo(methodOn(LicenseController.class)
        .getLicense(organisationId, license.getLicenseId()))
        .withSelfRel(),
        linkTo(methodOn(LicenseController.class)
        .createLicense(organisationId, license, null))
        .withRel("createLicense"),
        linkTo(methodOn(LicenseController.class)
        .updateLicense(organisationId, license))
        .withRel("updateLicense"),
        linkTo(methodOn(LicenseController.class)
        .deleteLicense(organisationId, license.getLicenseId()))
        .withRel("deleteLicense"));
        return ResponseEntity.ok(license);
    }

    @PutMapping
    public ResponseEntity<String> updateLicense(
        @PathVariable( "organisationId") String organisationId,
        @RequestBody License request){
        return ResponseEntity.ok(licenseService.updateLicense(request, organisationId));
    }

    @PostMapping
    public ResponseEntity<String> createLicense(
        @PathVariable("organisationId") String organisationId,
        @RequestBody License request,
        @RequestHeader(value = "Accept-Language",required = false)
                       Locale locale){
            return ResponseEntity.ok(licenseService.createLicense(request, organisationId, locale));
        }

    @DeleteMapping(value = "/{licenseId}")
    public ResponseEntity<String> deleteLicense(
        @PathVariable("organisationId") String organisationId,
        @PathVariable("licenseId") String licenseId){
            return ResponseEntity.ok(licenseService.deleteLicense(licenseId, organisationId));
        }

}
