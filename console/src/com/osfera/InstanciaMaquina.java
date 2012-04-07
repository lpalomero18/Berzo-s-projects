package com.osfera;

import com.amazonaws.services.ec2.model.Address;
import com.amazonaws.services.ec2.model.AssociateAddressRequest;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.ModifyInstanceAttributeRequest;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesRequest;

public class InstanciaMaquina {
	ClienteAWS cliente;
	Instance instancia;
	String direccionIP;
	Address addrDireccionIP;
	String ID;
	String tipo;
	int estadoReal; // 1 Encendido 0 apagado -1 Resto
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

	public void enciende() {
		if (cliente.getObsMaquina().estado == 80) {
			System.out.println("Arrancando " + ID);
			StartInstancesRequest req = new StartInstancesRequest();
			req.withInstanceIds(ID);
			cliente.getEc2().startInstances(req);
			estadoSupuesto = 1;
			System.out.println("Orden de arrancado dada");
			Runnable r = new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					while (cliente.getObsMaquina().estado != 16) {
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					asociaIP();
				}

			};
			Thread t = new Thread(r);
			t.start();
		} else {
			System.out.println("La maqina debe estar apagada para encenderse");
		}
	}

	public void apaga() {
		if (cliente.getObsMaquina().estado == 16) {
			StopInstancesRequest req = new StopInstancesRequest();
			req.withInstanceIds(ID);
			cliente.getEc2().stopInstances(req);
			estadoSupuesto = 1;
		} else {
			System.out.println("La máquina debe estar encendida para apagarse");
		}
	}

	public void cambiaSmall() {

		if (cliente.getObsMaquina().estado == 80) {
			ModifyInstanceAttributeRequest modif = new ModifyInstanceAttributeRequest();
			modif.withInstanceId(ID);
			modif.setInstanceType("m1.small");
			cliente.getEc2().modifyInstanceAttribute(modif);
		}
	}

	public void cambiaLarge() {

		if (cliente.getObsMaquina().estado == 80) {
			ModifyInstanceAttributeRequest modif = new ModifyInstanceAttributeRequest();
			modif.withInstanceId(ID);
			modif.setInstanceType("m1.large");
			cliente.getEc2().modifyInstanceAttribute(modif);
		}
	}

	public void asociaIP() {
		AssociateAddressRequest req = new AssociateAddressRequest(ID,
				direccionIP);
		cliente.getEc2().associateAddress(req);
	}
}
