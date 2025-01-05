package com.pcmc.mds.DTO;

import com.pcmc.mds.Models.Customer;
import com.pcmc.mds.Util.CustomerUtil.PasswordUtil;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CustomerDTO {

    @Email(message = "Invalid email format")
    private String email;

//    @Pattern(
//        regexp = "\\+91 [1-9][0-9]{9}",
//        message = "Phone number must be in the format '+91 XXXXXXXXXX' where X is a digit and the first digit cannot be 0"
//    )
    private String phone;

    @NotBlank(message = "Password cannot be blank")
    @Size(
        min = 8,
        max = 20,
        message = "Password must be between 8 and 20 characters long"
    )
    @Pattern(
        regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]+$",
        message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character"
    )
    private String password;
    
    
    /**
     * Converts this DTO to a Customer entity.
     * @return Customer entity
     */  
    public Customer toEntity() {
        Customer customer = new Customer();
        customer.setEmail(this.email);
        customer.setPhone(this.phone);
        customer.setPassword(PasswordUtil.hashPassword(this.password)); // Ideally, hash the password here before setting it
        return customer;
    }
    
    public static CustomerDTO fromEntity(Customer customer) {
        if (customer == null) {
            return null;
        }
        CustomerDTO dto = new CustomerDTO();
        dto.setEmail(customer.getEmail());
        dto.setPhone(customer.getPhone());
        return dto;
    }
    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
