package org.llin.demo.browserDOM.form;

import jakarta.validation.constraints.NotBlank;

public class RequestNewPasswordForm {
	
    @NotBlank(message = "User must not be blank")
    private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
    
}