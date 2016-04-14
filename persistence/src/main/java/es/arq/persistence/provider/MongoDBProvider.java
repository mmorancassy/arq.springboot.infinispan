package es.arq.persistence.provider;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Component;

import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import es.arq.persistence.provider.exceptions.PersistenceException;

@Component("mongoProvider")
public class MongoDBProvider implements DatabaseProvider {
	
	// The Logger
	private static final Logger LOG = LoggerFactory.getLogger(MongoDBProvider.class);
	
	private final MongoDbFactory mongo;
	
	@Autowired
	public MongoDBProvider(MongoDbFactory mongo) {
		this.mongo = mongo;
	}

	@Override
	public String insert(String document) throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(String documentId) throws PersistenceException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String update(String documentId, String document) throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getById(String documentId) throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> query(String query, String collection, int limit) throws PersistenceException {
				
		Map<String, String> documents = null;
		
		try {
			DB db = this.mongo.getDb();
			
			LOG.info("Accessing collection: " + collection + " on MongoDB database: " + db.getName());
			
			DBCursor results = null;
			if (limit > 0) {
				results = db.getCollection(collection).find().limit(limit);
			} else {
				results = db.getCollection(collection).find();
			}
			
			LOG.info("Number of documents retrieved from database: " + results.count());
			
			documents = new HashMap<String, String>();
			while (results.hasNext()) {
				DBObject item = results.next();
				String document = item.toString();
				String _id = item.get("_id").toString();
				
				documents.put(_id, document);
				
				LOG.debug("Document retrieved with id: " + _id + " json: " + document);
			}
		} catch (Exception e) {
			LOG.error("", e);
			throw new PersistenceException("", e);
		}
		
		return documents;
	}

}
