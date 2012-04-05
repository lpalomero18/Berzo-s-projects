package com.osfera;
import com.amazonaws.services.ec2.model.Address;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.StartInstancesRequest;

public class InstanciaMaquina {
	ClienteAWS cliente;
	Instance instancia;
	String direccionIP;
	Address addrDireccionIP;
	String ID;
	String tipo;
	int estadoReal;			// 1 Encendido	0 apagado	-1	Resto
	int estadoSupuesto;
	
	public InstanciaMaquina(String direccionIP, String iD, ClienteAWS ec2) {
		super();
		this.direccionIP = direccionIP;
		ID = iD;
		cliente = ec2;
	}
	public Instance getInstancia() {
		return instancia;
	}
	public void setInstancia(Instance instancia) {
		this.instancia = instancia;
	}
	public String getDireccionIP() {
		return direccionIP;
	}
	public void setDireccionIP(String direccionIP) {
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
	public int getEstado() {
		return estadoReal;
	}
	public void enciende(){
		StartInstancesRequest req = new StartInstancesRequest();
		req.withInstanceIds(ID);
		cliente.getEc2().startInstances(req);
		estadoSupuesto=1;
	}
	
}
