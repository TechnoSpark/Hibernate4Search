package com.hjava;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SolrDataReads {
	public static void main(String[] args) throws IOException, SolrServerException {
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		Transaction transaction = null;
			String url = "http://localhost:8080/solr/";
			SolrServer server = null;
			
	        try {
	        	
	            server = new HttpSolrServer(url);
	            SolrQuery query = new SolrQuery();
	            ModifiableSolrParams solrParams = new ModifiableSolrParams();
	            solrParams.set("q", "name:arvind");
	            query.setQuery( "arvind" );
	            query.addSortField( "name", SolrQuery.ORDER.asc );
	            QueryResponse rsp = server.query( solrParams );
	            SolrDocumentList docs = rsp.getResults();
	            List<Customer> beans = rsp.getBeans(Customer.class);
	            for (Customer customer : beans) {
					System.out.println(customer);
				}
	          
	            
	           /* query.setQuery("'ram'");

	            query.setHighlight(true); //set other params as needed
	            query.setParam("hl.fl", "name");
	            QueryResponse rsp = server.query(query);
	            SolrDocumentList docs = rsp.getResults();
	            Iterator iter=docs.iterator();
	            while (iter.hasNext()) {
	                SolrDocument resultDoc = (SolrDocument) iter.next();

	                String name = (String) resultDoc.getFieldValue("name");
	                String id = (String) resultDoc.getFieldValue("id"); //id is the uniqueKey field

	                if (rsp.getHighlighting().get(id) != null) {
	                  List<String> highlightSnippets = rsp.getHighlighting().get(id).get("name");
	                  for (Iterator iterator = highlightSnippets.iterator(); iterator
							.hasNext();) {
						String string = (String) iterator.next();
						System.out.println(string);
						
					}
	                }
	              }*/
	           /* System.out.println("-------------");
	            List<Customer> beans1 = rsp.getBeans(Customer.class);
	            for (Customer customer : beans1) {
					System.out.println(customer);
				}*/
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
