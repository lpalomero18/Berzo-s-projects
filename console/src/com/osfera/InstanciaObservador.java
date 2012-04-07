package com.osfera;

import javax.swing.JTextPane;

import com.amazonaws.services.ec2.model.DescribeInstanceAttributeRequest;
import com.amazonaws.services.ec2.model.DescribeInstanceStatusRequest;
import com.amazonaws.services.ec2.model.DescribeInstanceStatusResult;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceState;

public class InstanciaObservador implements Runnable {
	Thread t;
	ClienteAWS cliente;
	InstanciaMaquina instancia;
	int estado;
	String strEstado;
	int estadoViejo;
	String type;
	JTextPane texto;
	String IP;

	InstanciaObservador(InstanciaMaquina inst, ClienteAWS cli) {
		// Create a new, second thread
		instancia = inst;
		cliente = cli;
		estado = 0;
		estadoViejo = 0;
		IP="";
		t = new Thread(this, "Demo Thread");
		System.out.println("Llamado al creador: " + t);
		t.start(); // Start the thread
	}

	// This is the entry point for the second thread.
	public void run() {
		try {
			while (true) {
				// Let the thread sleep for a while.
				Thread.sleep(5000);
				updateAttributes();
			}
		} catch (InterruptedException e) {
			System.out.println("Child interrupted.");
		}
		System.out.println("Exiting child thread.");
	}

	private void publishEstado() {
		// TODO Auto-generated method stub

	}

	private void updateAttributes() {
		updateType();
		updateStateIP();
		publishEstado();
		if (estadoViejo != estado) {
			System.out.println("Cambio de estado detectado: de " + estadoViejo
					+ " a " + estado);
			estadoViejo = estado;
		}
		String string = "Estado actual: " + estado + " " + strEstado+"\r\nTipo actual: " + type+"\r\nIP: "+IP;
//		System.out.println(string);
		texto.setText(string);
	}

	private void updateStateIP() {
		DescribeInstancesRequest describeInstanceReq = new DescribeInstancesRequest();
		describeInstanceReq.withInstanceIds(instancia.getID());
		DescribeInstancesResult result = cliente.getEc2().describeInstances(
				describeInstanceReq);
		Instance instance = result.getReservations().get(0).getInstances()
				.get(0);
		InstanceState state = instance.getState();
		estado = state.getCode();
		IP=instance.getPublicIpAddress();
		strEstado = state.getName();
	}

	private void updateType() {
		type = cliente
				.getEc2()
				.describeInstanceAttribute(
						new DescribeInstanceAttributeRequest(instancia
								.getID(), "instanceType"))
				.getInstanceAttribute().getInstanceType();
	}

	public void registraComponente(JTextPane tx) {
		texto = tx;
	}
}
