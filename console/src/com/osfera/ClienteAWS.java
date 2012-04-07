package com.osfera;

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

public class ClienteAWS {

	private static final String DIRECCION_IP = "";
	private static final String MAQUINA = "i-be5e68f7";
	AmazonEC2 ec2;
	InstanciaMaquina maquina;
	InstanciaObservador obsMaquina;
	
	public AmazonEC2 getEc2() {
		return ec2;
	}

	public ClienteAWS() {
		super();
			try {
				init();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 maquina = new InstanciaMaquina(DIRECCION_IP,MAQUINA,this);
			 obsMaquina = new InstanciaObservador(getInstanceFromString(MAQUINA), this);
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
		// ec2.eu-west-1.amazonaws.com
	}
	public InstanciaMaquina getMainInstance(){
		return maquina;
	}

	public Instance getInstanceFromString (String instanceID) {
		Instance instance;
			DescribeInstancesRequest describeInstancesRequest = new DescribeInstancesRequest()
					.withInstanceIds(instanceID);
			DescribeInstancesResult describeInstancesResult = getEc2()
					.describeInstances(describeInstancesRequest);
			List<Reservation> reservations = describeInstancesResult
					.getReservations();
			instance = reservations.get(0).getInstances().get(0);
		
		return instance;
		
	}

	public InstanciaObservador getObsMaquina() {
		return obsMaquina;
	}

}
