package org.mu.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private final Logger log = Logger.getLogger(LoginBean.class);
	
	private String username;
	private String password;
	
	public LoginBean() {
		log.info("Cargando Session de LoginBean");
	}
	
	public void verifyLogin() {
		log.info("Username: "+username+", Password: " + password);
	}
	
	public void displayMessage() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("HOLA PRUEBA"));
	}
	
	//	GETTER AND SETTER
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
