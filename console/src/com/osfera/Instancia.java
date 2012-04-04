package com.osfera;
import com.amazonaws.services.ec2.model.Address;
import com.amazonaws.services.ec2.model.Instance;

public class Instancia {
	Instance instancia;
	Address direccionIP;
	String ID;
	String tipo;
	String estado;
	public Instance getInstancia() {
		return instancia;
	}
	public void setInstancia(Instance instancia) {
		this.instancia = instancia;
	}
	public Address getDireccionIP() {
		return direccionIP;
	}
	public void setDireccionIP(Address direccionIP) {
		this.direccionIP = direccionIP;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
