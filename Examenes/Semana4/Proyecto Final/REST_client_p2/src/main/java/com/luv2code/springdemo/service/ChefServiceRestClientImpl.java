package com.luv2code.springdemo.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.luv2code.springdemo.model.Chef;

@Service
public class ChefServiceRestClientImpl implements ChefService {

	private RestTemplate restTemplate;

	private String crmRestUrl;
		
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	public ChefServiceRestClientImpl(RestTemplate theRestTemplate, 
										@Value("${crm.rest.url}") String theUrl) {
		
		restTemplate = theRestTemplate;
		crmRestUrl = theUrl;
				
		logger.info("Loaded property:  crm.rest.url=" + crmRestUrl);
	}
	
	@Override
	public List<Chef> getChef() {
		
		logger.info("***OBTENER LISTA DE CLIENTES DESDE EL SERVICE REST CLIENTE");
		logger.info("in getChefs(): Calling REST API " + crmRestUrl);

		// make REST call
		ResponseEntity<List<Chef>> responseEntity = 
											restTemplate.exchange(crmRestUrl, HttpMethod.GET, null, 
													 new ParameterizedTypeReference<List<Chef>>() {});

		// get the list of customers from response
		List<Chef> chefs = responseEntity.getBody();

		logger.info("in getCustomers(): customers" + chefs);
		
		return chefs;
	}

	@Override
	public Chef getChef(int theId) {
		logger.info("***OBTENER UN CLIENTE DESDE EL SERVICE REST CLIENTE");

		logger.info("in getChef(): Calling REST API " + crmRestUrl);

		// make REST call
		Chef theChef = 
				restTemplate.getForObject(crmRestUrl + "/" + theId, 
						Chef.class);

		logger.info("in saveChef(): theChef=" + theChef);
		
		return theChef;
	}

	@Override
	public void saveChef(Chef theChef) {

		logger.info("in saveChef(): Calling REST API " + crmRestUrl);
		
		int chefId = theChef.getId();

		// make REST call
		if (chefId == 0) {
			// add chef
			logger.info("***SALVAR UN CHEF DESDE EL SERVICE REST CLIENTE");

			restTemplate.postForEntity(crmRestUrl, theChef, String.class);			
		
		} else {
			// update chef
			logger.info("***ACTUALIZAR UN CHEF DESDE EL SERVICE REST CHEF");

			restTemplate.put(crmRestUrl, theChef);
		}

		logger.info("in saveChef(): success");	
	}

	@Override
	public void deleteChef(int theId) {
		logger.info("***BORRAR UN CLIENTE DESDE EL SERVICE REST CLIENTE");

		logger.info("in deleteChef(): Calling REST API " + crmRestUrl);

		// make REST call
		restTemplate.delete(crmRestUrl + "/" + theId);

		logger.info("in deleteChef(): deleted chef theId=" + theId);
	}

	@Override
	public List<Chef> getChefs() {
		// TODO Auto-generated method stub
		return null;
	}

}
