package es.arq.persistence.provider;

import java.util.List;

import es.arq.persistence.provider.exceptions.PersistenceException;

public interface DatabaseProvider {
	
	/**
	 * Inserts JSON document
	 * 
	 * @param document
	 * @param collection
	 * @return
	 */
	public String insert(String document, String collection) throws PersistenceException;
	
	/**
	 * Deletes JSON document
	 * 
	 * @param documentId
	 * @return
	 */
	public boolean delete(String documentId, String collection) throws PersistenceException;
	
	/**
	 * Updates JSON document, return updated object
	 * 
	 * @param document
	 * @param collection
	 * @return
	 */
	public String update(String document, String collection) throws PersistenceException;
	
	/**
	 * Retrieves JSON document
	 * 
	 * @param collection
	 * @param documentId
	 * @return
	 */
	public String getById(String collection, String documentId) throws PersistenceException;
	
	/**
	 * Retrieves JSON document Map<id,json>
	 * 
	 * @param query
	 * @param collection
	 * @param limit
	 * @return
	 */
	public List<String> query(String query, String collection, int limit) throws PersistenceException;
}