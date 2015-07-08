package hu.neuron.java.web.servlt;

import java.io.Serializable;
import java.util.Date;

public class WebVO implements Serializable {

	private static final long serialVersionUID = -4761114566342808402L;

	private String name;
	private String address;
	private Date date;

	public WebVO() {

	}

	public WebVO(String name, String address, Date date) {
		super();
		this.name = name;
		this.address = address;
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
