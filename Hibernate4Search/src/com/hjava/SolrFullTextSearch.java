package com.hjava;

import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.highlight.GradientFormatter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.TokenGroup;
import org.apache.lucene.util.Version;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.util.HighlightingUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SolrFullTextSearch {

		public static void main(String[] args) throws IOException, SolrServerException, ParseException {
			Session session = HibernateSessionFactory.getSessionFactory().openSession();
			Transaction transaction = null;
				String url = "http://localhost:8080/solr/";
				SolrServer server = null;
				
		        try{
		        	server = new HttpSolrServer(url);
		            SolrQuery query = new SolrQuery();
		            query.setQuery( "address_s:mangowan" );
		            query.addFacetField("name");
		            query.addFacetField("address_s");
		            query.addHighlightField("address_s");
		            System.out.println(query.toString());
		            QueryResponse rsp = server.query( query );
		            SolrDocumentList docs = rsp.getResults();
		            Iterator iter=docs.iterator();
		            while (iter.hasNext()) {
		                SolrDocument resultDoc = (SolrDocument) iter.next();
		                
		                System.out.println("resultDoc="+resultDoc.get("name"));
		            }
		            System.out.println("count="+rsp.getResults().getNumFound());
		            System.out.println(rsp.getFacetField("name").getValueCount());
		            
		           
		            System.out.println(rsp.getHighlighting().toString());
		            System.out.println(rsp.getFacetQuery());
		           
		            System.out.println(query.getHighlightFields()[0]);
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
