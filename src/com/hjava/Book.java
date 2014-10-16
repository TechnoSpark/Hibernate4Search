package com.hjava;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.SnowballPorterFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.CharFilterDef;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

@Entity
@Indexed
@AnalyzerDef(name = "customanalyzer", 
charFilters=@CharFilterDef(factory = org.apache.solr.analysis.MappingCharFilterFactory.class, params={@Parameter(name="mapping" ,value="/opt/fd/xcz")  } ),
tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
filters={@TokenFilterDef(factory = LowerCaseFilterFactory.class),
	       @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")
	    })
    })
public class Book {

  @Id
  @GeneratedValue
  private Integer id;
  
  @Field(index=Index.YES,  analyze=Analyze.YES, analyzer=@org.hibernate.search.annotations.Analyzer(impl=StandardAnalyzer.class),store=Store.NO)
  private String title;
  
  @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
  private String subtitle; 
  @Field(index = Index.YES, analyze=Analyze.NO, store = Store.YES)
  @DateBridge(resolution = Resolution.DAY)
  private Date publicationDate;

  public Book() {
  }

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getSubtitle() {
	return subtitle;
}

public void setSubtitle(String subtitle) {
	this.subtitle = subtitle;
}

public Date getPublicationDate() {
	return publicationDate;
}

public void setPublicationDate(Date publicationDate) {
	this.publicationDate = publicationDate;
}

@Override
public String toString() {
	return "Book [id=" + id + ", title=" + title + ", subtitle=" + subtitle
			+ ", publicationDate=" + publicationDate + "]";
}

  
  
}
