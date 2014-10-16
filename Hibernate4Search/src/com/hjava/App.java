package com.hjava;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;



@SuppressWarnings("deprecation")
public class App {
	public static void main(String[] args) throws IOException, SolrServerException {
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		Transaction transaction = null;
		try{
		Book book=new Book();
		Author author1=new Author();
		Author author2=new Author();
		author1.setName("demo testauthor");
		author2.setName("Arvind");
		Set<Author> set=new HashSet<>();
		set.add(author1);
		set.add(author2);
		book.setPublicationDate(new Date());
		book.setSubtitle("core java");
		book.setTitle("free valocity");
			session.saveOrUpdate(book);
			//transaction.commit();
		String url = "http://localhost:8983/solr/";
		SolrServer server = null;
        try {
        	
            server = new HttpSolrServer(url);
           System.out.println( server.ping());
            server.addBean(book);
            server.commit();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
			
			/*FullTextSession fullTextSession = Search.getFullTextSession(session);
			Transaction tx = fullTextSession.beginTransaction();

			// create native Lucene query unsing the query DSL
			// alternatively you can write the Lucene query using the Lucene query parser
			// or the Lucene programmatic API. The Hibernate Search DSL is recommended though
			QueryBuilder qb = fullTextSession.getSearchFactory()
			    .buildQueryBuilder().forEntity( Book.class ).get();
			org.apache.lucene.search.Query query = qb
			  .keyword()
			  .onFields("title","subtitle")
			  .ignoreAnalyzer().matching("spring")
			  .createQuery();

			// wrap Lucene query in a org.hibernate.Query
			org.hibernate.Query hibQuery = 
			    fullTextSession.createFullTextQuery(query, Book.class);

			// execute search
			List result = hibQuery.list();
			Iterator it=result.iterator();
			while(it.hasNext()){
				Book book2=(Book) it.next();
				System.out.println(book2);
				
			}*/
			  
		
			
			
		}catch (HibernateException e) {
			//transaction.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
}



