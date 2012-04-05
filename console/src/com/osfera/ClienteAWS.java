package com.osfera;

/*
 * Copyright 2010-2012 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeAvailabilityZonesResult;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceState;
import com.amazonaws.services.ec2.model.ModifyInstanceAttributeRequest;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.simpledb.AmazonSimpleDB;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;
import com.amazonaws.services.simpledb.model.DomainMetadataRequest;
import com.amazonaws.services.simpledb.model.DomainMetadataResult;
import com.amazonaws.services.simpledb.model.ListDomainsRequest;
import com.amazonaws.services.simpledb.model.ListDomainsResult;

/**
 * Welcome to your new AWS Java SDK based project!
 * 
 * This class is meant as a starting point for your console-based application
 * that makes one or more calls to the AWS services supported by the Java SDK,
 * such as EC2, SimpleDB, and S3.
 * 
 * In order to use the services in this sample, you need:
 * 
 * - A valid Amazon Web Services account. You can register for AWS at:
 * https://aws-portal.amazon.com/gp/aws/developer/registration/index.html
 * 
 * - Your account's Access Key ID and Secret Access Key:
 * http://aws.amazon.com/security-credentials
 * 
 * - A subscription to Amazon EC2. You can sign up for EC2 at:
 * http://aws.amazon.com/ec2/
 * 
 * - A subscription to Amazon SimpleDB. You can sign up for Simple DB at:
 * http://aws.amazon.com/simpledb/
 * 
 * - A subscription to Amazon S3. You can sign up for S3 at:
 * http://aws.amazon.com/s3/
 */
public class ClienteAWS {

	/*
	 * Important: Be sure to fill in your AWS access credentials in the
	 * AwsCredentials.properties file before you try to run this sample.
	 * http://aws.amazon.com/security-credentials
	 */

	AmazonEC2 ec2;
	String maquina;

	public AmazonEC2 getEc2() {
		return ec2;
	}

	public ClienteAWS() {
		super();
		try {
			init();

		} catch (AmazonServiceException ase) {
			System.out.println("Caught Exception: " + ase.getMessage());
			System.out.println("Reponse Status Code: " + ase.getStatusCode());
			System.out.println("Error Code: " + ase.getErrorCode());
			System.out.println("Request ID: " + ase.getRequestId());
		} catch (Exception ase) {
			System.out.println("Caught Exception: " + ase.getMessage());

		}
	}

	/**
	 * The only information needed to create a client are security credentials
	 * consisting of the AWS Access Key ID and Secret Access Key. All other
	 * configuration, such as the service endpoints, are performed
	 * automatically. Client parameters, such as proxies, can be specified in an
	 * optional ClientConfiguration object when constructing a client.
	 * 
	 * @see com.amazonaws.auth.BasicAWSCredentials
	 * @see com.amazonaws.auth.PropertiesCredentials
	 * @see com.amazonaws.ClientConfiguration
	 */
	private void init() throws Exception {
		AWSCredentials credentials = new PropertiesCredentials(
				ClienteAWS.class
						.getResourceAsStream("AwsCredentials.properties"));

		ec2 = new AmazonEC2Client(credentials);
		ec2.setEndpoint("https://eu-west-1.ec2.amazonaws.com");
		maquina = new String("i-be5e68f7");
		// ec2.eu-west-1.amazonaws.com
	}

	public static void main(String[] args) throws Exception {

		System.out.println("===========================================");
		System.out.println("Welcome to the AWS Java SDK!");
		System.out.println("===========================================");

		ClienteAWS cliente = new ClienteAWS();
		cliente.init();
		/*
		 * Amazon EC2
		 * 
		 * The AWS EC2 client allows you to create, delete, and administer
		 * instances programmatically.
		 * 
		 * In this sample, we use an EC2 client to get a list of all the
		 * availability zones, and all instances sorted by reservation id.
		 */
		try {
			/*
			 * DescribeAvailabilityZonesResult availabilityZonesResult =
			 * ec2.describeAvailabilityZones();
			 * System.out.println("You have access to " +
			 * availabilityZonesResult.getAvailabilityZones().size() +
			 * " Availability Zones.");
			 * 
			 * DescribeInstancesResult describeInstancesRequest =
			 * ec2.describeInstances();
			 * 
			 * List<Reservation> reservations =
			 * describeInstancesRequest.getReservations(); Set<Instance>
			 * instances = new HashSet<Instance>();
			 * 
			 * for (Reservation reservation : reservations) {
			 * instances.addAll(reservation.getInstances()); }
			 */
			// DescribeInstancesResult descInst = cliente.ec2
			// .describeInstances(new DescribeInstancesRequest()
			// .withInstanceIds(maquina));
			// List<Reservation> reservations = descInst.getReservations();
			// Set<Instance> instances = new HashSet<Instance>();
			// for (Reservation reservation : reservations) {
			// instances.addAll(reservation.getInstances());
			// }
			// Instance inst = instances.iterator().next();
			// cambiaTamaño(inst, "m1.small");
			// describeInstancia(inst);
			// cambiaTamaño(inst, "m1.large");
			// describeInstancia(inst);

			System.out.println("Fin");
		} catch (AmazonServiceException ase) {
			System.out.println("Caught Exception: " + ase.getMessage());
			System.out.println("Reponse Status Code: " + ase.getStatusCode());
			System.out.println("Error Code: " + ase.getErrorCode());
			System.out.println("Request ID: " + ase.getRequestId());
		}

	}

	/*
	 * private static void cambiaTamaño(Instance inst, String tamaño) {
	 * ModifyInstanceAttributeRequest modif = new
	 * ModifyInstanceAttributeRequest();
	 * modif.setInstanceId(inst.getInstanceId()); modif.setInstanceType(tamaño);
	 * cliente.ec2.modifyInstanceAttribute(modif); }
	 */

	private static void describeInstancia(Instance inst) {
		System.out.println(inst.getInstanceId() + " "
				+ inst.getState().toString() + " " + inst.getState().getCode()
				+ " " + inst.getInstanceType());

	}
}
