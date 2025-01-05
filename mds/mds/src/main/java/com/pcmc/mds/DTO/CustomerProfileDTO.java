package com.pcmc.mds.DTO;

import java.time.LocalDate;

import com.pcmc.mds.Models.Customer;
import com.pcmc.mds.Models.CustomerProfile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerProfileDTO {

    private String uniqueID;

    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @Email(message = "Invalid email format")
    private String alternateEmail;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    @Pattern(
        regexp = "\\d{5}(-\\d{4})?",
        message = "Invalid postal code format"
    )
    private String postalCode;

    private String country;

    private LocalDate dateOfBirth;

    @Pattern(
        regexp = "Male|Female|Other",
        message = "Gender must be Male, Female, or Other"
    )
    private String gender;

    private String profilePictureUrl;

    private String preferredLanguage;

    private String communicationPreference;

    private String facebookLink;

    private String twitterHandle;

    private String linkedinProfile;
    
    
    public CustomerProfile mapDtoToEntity(CustomerProfileDTO dto) {
         CustomerProfile customerProfile = new CustomerProfile();
//         customerProfile.setCustomer(dto.getCustomer());
         customerProfile.setFirstName(dto.getFirstName());
         customerProfile.setLastName(dto.getLastName());
         customerProfile.setAlternateEmail(dto.getAlternateEmail());
         customerProfile.setAddressLine1(dto.getAddressLine1());
         customerProfile.setAddressLine2(dto.getAddressLine2());
         customerProfile.setCity(dto.getCity());
         customerProfile.setState(dto.getState());
         customerProfile.setPostalCode(dto.getPostalCode());
         customerProfile.setCommunicationPreference(dto.getCommunicationPreference());
         customerProfile.setCountry(dto.getCountry());
         customerProfile.setDateOfBirth(dto.getDateOfBirth());
         customerProfile.setGender(dto.getGender());
         customerProfile.setProfilePictureUrl(dto.getProfilePictureUrl());
         customerProfile.setFacebookLink(dto.getFacebookLink());
         customerProfile.setPreferredLanguage(dto.getPreferredLanguage());
         customerProfile.setTwitterHandle(dto.getTwitterHandle());
         customerProfile.setLinkedinProfile(dto.getLinkedinProfile());
         
         return customerProfile;
    }


	public String getUniqueId() {
		return uniqueID;
	}


	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getAlternateEmail() {
		return alternateEmail;
	}


	public void setAlternateEmail(String alternateEmail) {
		this.alternateEmail = alternateEmail;
	}


	public String getAddressLine1() {
		return addressLine1;
	}


	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}


	public String getAddressLine2() {
		return addressLine2;
	}


	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getPostalCode() {
		return postalCode;
	}


	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getProfilePictureUrl() {
		return profilePictureUrl;
	}


	public void setProfilePictureUrl(String profilePictureUrl) {
		this.profilePictureUrl = profilePictureUrl;
	}


	public String getPreferredLanguage() {
		return preferredLanguage;
	}


	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}


	public String getCommunicationPreference() {
		return communicationPreference;
	}


	public void setCommunicationPreference(String communicationPreference) {
		this.communicationPreference = communicationPreference;
	}


	public String getFacebookLink() {
		return facebookLink;
	}


	public void setFacebookLink(String facebookLink) {
		this.facebookLink = facebookLink;
	}


	public String getTwitterHandle() {
		return twitterHandle;
	}


	public void setTwitterHandle(String twitterHandle) {
		this.twitterHandle = twitterHandle;
	}


	public String getLinkedinProfile() {
		return linkedinProfile;
	}


	public void setLinkedinProfile(String linkedinProfile) {
		this.linkedinProfile = linkedinProfile;
	}
    
    
    
    
}
