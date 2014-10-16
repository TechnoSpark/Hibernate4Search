package com.hjava;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SolrSearch {
	public static void main(String[] args) throws IOException, SolrServerException {
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		Transaction transaction = null;
			String url = "http://localhost:8080/solr/";
			SolrServer server = null;
			Customer alex = new Customer();
			alex.setCustomerName("pandey");
			alex.setCustomerAddress("noida");
			alex.setId("4");
	        try {
	       session.saveOrUpdate(alex);
	        	//transaction.commit();
	            server = new HttpSolrServer(url);
	           System.out.println( server.ping());
	            server.addBean(alex);
	           //server.deleteByQuery( "*:*" );
	            server.commit();
	            System.out.println("success");
	        } catch (MalformedURLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
			finally{
				session.close();
			}
	}
}
