package pa3;

/*
 * Copyright 2010-2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.amazonaws.services.dynamodbv2.util.Tables;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;

/**
 * This sample demonstrates how to make basic requests to Amazon SQS using the
 * AWS SDK for Java.
 * <p>
 * <b>Prerequisites:</b> You must have a valid Amazon Web Services developer
 * account, and be signed up to use Amazon SQS. For more information on Amazon
 * SQS, see http://aws.amazon.com/sqs.
 * <p>
 * Fill in your AWS access credentials in the provided credentials file
 * template, and be sure to move the file to the default location
 * (C:\\Users\\HP\\.aws\\credentials) where the sample code will load the
 * credentials from.
 * <p>
 * <b>WARNING:</b> To avoid accidental leakage of your credentials, DO NOT keep
 * the credentials file in your source directory.
 */


/**
 * Class name: PublisherThreadPool
 * 
 * @author Pavankumar Bhujanga Shetty
 * @version 1.0
 * 
 *          This is the class used to implement the publisher/Client
 *          implementation It will read the contents from the file And puts into
 *          Queue
 * 
 * @param ConcurrentLinkedQueue<TaskToQueueThreadPool>
 *            requestQueue - Queue used by publisher to out all tasks/ Messages.
 * @param String
 *            fileName - Name of the file from where tasks are read from.
 * 
 */

public class RemoteWorkerSQS {

	static AmazonSQS sqs;
	static AmazonDynamoDBClient dynamoDB;
	
	/**
     * The only information needed to create a client are security credentials
     * consisting of the AWS Access Key ID and Secret Access Key. All other
     * configuration, such as the service endpoints, are performed
     * automatically. Client parameters, such as proxies, can be specified in an
     * optional ClientConfiguration object when constructing a client.
     *
     * @see com.amazonaws.auth.BasicAWSCredentials
     * @see com.amazonaws.auth.ProfilesConfigFile
     * @see com.amazonaws.ClientConfiguration
     */
	
	private static void initSQSandDynamoDB() throws Exception {
    	/*
    	 * The ProfileCredentialsProvider will return your [default] credential
    	 * profile by reading from the credentials file located at
    	 * (C:\\Users\\HP\\.aws\\credentials).
    	 */
    	AWSCredentials credentials = null;
    	try {
    		credentials = new ProfileCredentialsProvider("default").getCredentials();
    	} catch (Exception e) {
    		throw new AmazonClientException("Cannot load the credentials from the credential profiles file. "
    				+ "Please make sure that your credentials file is at the correct "
    				+ "location (C:\\Users\\HP\\.aws\\credentials), and is in valid format.", e);
    	}
    	dynamoDB = new AmazonDynamoDBClient(credentials);
    	sqs = new AmazonSQSClient(credentials);
    	Region usWest2 = Region.getRegion(Regions.US_WEST_2);
    	dynamoDB.setRegion(usWest2);
    	sqs.setRegion(usWest2);
    }	
	// Create a new item for database entry
    private static Map<String, AttributeValue> newItem(String taskID) {
    	Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
    	item.put("taskID", new AttributeValue(taskID));
    	return item;
    }
	
    public static void main(String[] args) throws Exception {

        initSQSandDynamoDB();
		try {

			String tableName = "PA3"+args[2];
			// Create table if it does not exist yet
			if (Tables.doesTableExist(dynamoDB, tableName)) {
				System.out.println("Table " + tableName + " is already ACTIVE");
			} else {
				// Create a table with a primary hash key named 'name', which
				// holds a string
				CreateTableRequest createTableRequest = new CreateTableRequest().withTableName(tableName)
						.withKeySchema(new KeySchemaElement().withAttributeName("taskID").withKeyType(KeyType.HASH))
						.withAttributeDefinitions(new AttributeDefinition().withAttributeName("taskID")
								.withAttributeType(ScalarAttributeType.S))
						.withProvisionedThroughput(
								new ProvisionedThroughput().withReadCapacityUnits(1L).withWriteCapacityUnits(1L));
				TableDescription createdTableDescription = dynamoDB.createTable(createTableRequest)
						.getTableDescription();
				System.out.println("Created Table: " + createdTableDescription);

				// Wait for it to become active
				System.out.println("Waiting for " + tableName + " to become ACTIVE...");
				Tables.awaitTableToBecomeActive(dynamoDB, tableName);
			}

			String noOfWorkers = args[4];
			String requestQueueName = "TaskQueue"+args[2];
			String responseQueueName = "TaskResponseQueue"+args[2];
			String clientOrWorker = args[0];
			
			// Create a queue
			//System.out.println("Accessing SQS queue: "+requestQueueName);
			String myRequestQueueUrl = sqs.createQueue(requestQueueName).getQueueUrl();

			//System.out.println("Creating a new SQS queue called MyResponseQueue.\n");
			String myResponseQueueUrl = sqs.createQueue(responseQueueName).getQueueUrl();

			// Receive the messages
			try {
				ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(myRequestQueueUrl);
				receiveMessageRequest.setVisibilityTimeout(900);
				receiveMessageRequest.setWaitTimeSeconds(20);
				while (true) {	
					List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
					// Throw exception when queue gets empty
					if(messages.isEmpty()){
						break;
					}
					for (Message message : messages) {
						try {
							String[] splitTask = message.getBody().split(" ");
							//DynamoDB
							Map<String, AttributeValue> item = newItem(splitTask[0]);
							PutItemRequest putItemRequest = new PutItemRequest(tableName, item)
									.withConditionExpression("attribute_not_exists(taskID)");
							dynamoDB.putItem(putItemRequest);
							
							//System.out.println(splitTask[0]+" : "+splitTask[2]);
							// Execute Task
							Thread.sleep(Long.parseLong(splitTask[2]));
							sqs.sendMessage(
									new SendMessageRequest(myResponseQueueUrl,splitTask[0]));
							// Delete the message
							String messageReceiptHandle = message.getReceiptHandle();
							sqs.deleteMessage(new DeleteMessageRequest(myRequestQueueUrl, messageReceiptHandle));
							
						} catch (ConditionalCheckFailedException e) {
							//e.printStackTrace();
						}
					}
				}
			} catch (Exception ex) {
				//ex.printStackTrace();
			}

		} catch (AmazonServiceException ase) {
			System.out.println("Caught an AmazonServiceException, which means your request made it "
					+ "to AWS, but was rejected with an error response for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException, which means the client encountered "
					+ "a serious internal problem while trying to communicate with AWS, "
					+ "such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}

	}
}

