package es.arq.platform.services.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.arq.persistence.provider.exceptions.PersistenceException;
import es.arq.platform.application.WebApplication;
import es.arq.platform.controller.dto.DiscogsRelease;
import es.arq.platform.services.DiscogsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebApplication.class}, initializers = ConfigFileApplicationContextInitializer.class)
public class DiscogsServiceTests {
	
	@Autowired
	private DiscogsService discogsService;
	
	@Test
	public void testFindCollectionByUser() throws PersistenceException {
		
		DiscogsRelease collection = discogsService.findCollectionByUser("danzig6661");
		
		Assert.assertTrue(collection != null);
		
	}

}
