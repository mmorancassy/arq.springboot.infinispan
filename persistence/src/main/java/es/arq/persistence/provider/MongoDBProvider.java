package es.arq.persistence.provider;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;

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
	public String insert(String document, String collection) throws PersistenceException {
		String insertedDocument = null;
		
		try {
			DB db = this.mongo.getDb();
			
			LOG.info("Accessing collection: " + collection + " on MongoDB database: " + db.getName());

			DBObject dbo = (DBObject)JSON.parse(document);
			List<DBObject> documents = new ArrayList<DBObject>();
			documents.add(dbo);
			WriteResult result = db.getCollection(collection).insert(documents);
						
			LOG.info("Document sucessfully inserted in database: " + result.toString());
			
			insertedDocument = dbo.get("_id").toString();
			
		} catch (Exception e) {
			LOG.error("Se ha producido un error al tratar de insertar un documento en la base de datos", e);
			throw new PersistenceException("Se ha producido un error al tratar de insertar un documento en la base de datos", e);
		}
		
		return insertedDocument;
	}

	@Override
	public boolean delete(String documentId, String collection) throws PersistenceException {
		boolean statusOperation = true;
		try {
			DB db = this.mongo.getDb();
			
			LOG.info("Accessing collection: " + collection + " on MongoDB database: " + db.getName());

			DBObject dbo = new BasicDBObject();
			dbo.put("_id", new ObjectId(documentId));
			WriteResult result = db.getCollection(collection).remove(dbo);
						
			LOG.info("Document sucessfully inserted in database: " + result.toString());
			
			statusOperation = (result.getN() > 0);
			
		} catch (Exception e) {
			LOG.error("Se ha producido un error al tratar de insertar un documento en la base de datos", e);
			throw new PersistenceException("Se ha producido un error al tratar de insertar un documento en la base de datos", e);
		}
		
		return statusOperation;
	}

	@Override
	public String update(String document, String collection) throws PersistenceException {
		String updatedDocument = null;
		
		try {
			DB db = this.mongo.getDb();
			
			LOG.info("Accessing collection: " + collection + " on MongoDB database: " + db.getName());
			
			DBObject dbo = (DBObject)JSON.parse(document);
			String documentId = dbo.get("_id").toString();
			DBObject criteria = new BasicDBObject();
			criteria.put("_id", new ObjectId(documentId));
			WriteResult result = db.getCollection(collection).update(criteria , dbo);

			if (result.isUpdateOfExisting()) {
				LOG.info("Document sucessfully updated in database: " + result.toString());
				
				DBObject jsonDoc = db.getCollection(collection).findOne(criteria);
				
				updatedDocument = jsonDoc.toString();
				
			} else {
				LOG.info("document can't be updated in database: " + result.toString());
				
				updatedDocument = "";
			}
			
		} catch (Exception e) {
			LOG.error("Se ha producido un error al tratar de recuperar un documento de la base de datos", e);
			throw new PersistenceException("Se ha producido un error al tratar de recuperar un documento de la base de datos", e);
		}
		
		return updatedDocument;
	}

	@Override
	public String getById(String collection, String documentId) throws PersistenceException {
		String document = null;
		
		try {
			DB db = this.mongo.getDb();
			
			LOG.info("Accessing collection: " + collection + " on MongoDB database: " + db.getName());

			BasicDBObject dbo = new BasicDBObject();
			dbo.put("_id", new ObjectId(documentId));
			DBObject result = db.getCollection(collection).findOne(dbo);
						
			if (result != null) {
				LOG.info("Retrieved document from database: " + result.toString());
				
				document = result.toString();
			}
			
		} catch (Exception e) {
			LOG.error("Se ha producido un error al tratar de recuperar un documento de la base de datos", e);
			throw new PersistenceException("Se ha producido un error al tratar de recuperar un documento de la base de datos", e);
		}
		
		return document;
	}

	@Override
	public List<String> query(String query, String collection, int limit) throws PersistenceException {
				
		List<String> documents = null;
		
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
			
			documents = new ArrayList<String>();
			while (results.hasNext()) {
				DBObject item = results.next();
				String document = item.toString();
				String _id = item.get("_id").toString();
				
				documents.add(document);
				
				LOG.debug("Document retrieved with id: " + _id + " json: " + document);
			}
		} catch (Exception e) {
			LOG.error("Se ha producido un error al tratar de recuperar documentos de la base de datos", e);
			throw new PersistenceException("Se ha producido un error al tratar de recuperar documentos de la base de datos", e);
		}
		
		return documents;
	}

}
