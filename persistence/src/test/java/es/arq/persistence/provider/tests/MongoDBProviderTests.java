package es.arq.persistence.provider.tests;

import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import es.arq.persistence.application.Application;
import es.arq.persistence.provider.DatabaseProvider;
import es.arq.persistence.provider.exceptions.PersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class}, initializers = ConfigFileApplicationContextInitializer.class)
public class MongoDBProviderTests {
	
	// Test collection to insert data
	private final static String TEST_COLLECTION = "garbage";
	
	@Autowired
	DatabaseProvider mongoProvider;
	
	@Test
	public void testFindById() {
		try {
			String documentId = mongoProvider.insert("{\"test\" : \"data\"}", TEST_COLLECTION);
			String document = mongoProvider.getById(TEST_COLLECTION, documentId);
			
			DBObject jsonDoc = (DBObject)JSON.parse(document);
			String _id = ((ObjectId)jsonDoc.get("_id")).toString();
			
			Assert.assertTrue(_id.equals(documentId));
			
		} catch (PersistenceException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testQuery() {
		try {
			List<String> documents = mongoProvider.query("", TEST_COLLECTION, 100);
			Assert.assertTrue(documents != null && documents.size() > 0);
			
		} catch (PersistenceException e) {
			Assert.fail(e.getMessage());
		}
	}	
	
	@Test
	public void testInsert() {
		try  {
			String result = mongoProvider.insert("{\"test\" : \"data\"}", TEST_COLLECTION);
			
			Assert.assertTrue(result != null);
		} catch (PersistenceException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testDelete() {
		try  {
			String documentId = mongoProvider.insert("{\"test\" : \"data\"}", TEST_COLLECTION);			
			boolean result = mongoProvider.delete(documentId, TEST_COLLECTION);
			
			Assert.assertTrue(result);
			
		} catch (PersistenceException e) {
			Assert.fail(e.getMessage());
		}
	}	

}
