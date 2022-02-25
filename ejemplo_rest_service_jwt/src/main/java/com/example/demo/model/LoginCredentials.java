package com.example.demo.model;

import java.util.Objects;

import lombok.*;

@NoArgsConstructor
public class LoginCredentials {

    private String email;
    private String password;
    
	public LoginCredentials(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginCredentials other = (LoginCredentials) obj;
		return Objects.equals(email, other.email);
	}

	@Override
	public String toString() {
		return "LoginCredentials [email=" + email + ", password=" + password + "]";
	}


    
    
}