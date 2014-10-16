package com.hjava;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.SnowballPorterFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.apache.solr.client.solrj.beans.Field;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

@Entity
@Table(name="Customer")
@Indexed
@AnalyzerDef(name = "customanalyzer", 
tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),filters={@TokenFilterDef(factory = LowerCaseFilterFactory.class),
	 @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
	      @Parameter(name = "language", value = "English")
	    })
    })
public class Customer {
	@Id
    @Field
    public String id;
    
	@Field(value="name")
    public String customerName;
	
	@Field("address_s")
    public String address;

  
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return address;
    }
    public void setCustomerAddress(String customerAddress) {
        this.address = customerAddress;
    }
	@Override
	public String toString() {
		return "Customer [id=" + id + ", customerName=" + customerName
				+ ", address=" + address + "]";
	}



}