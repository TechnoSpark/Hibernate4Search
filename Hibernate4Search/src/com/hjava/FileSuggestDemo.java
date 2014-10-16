package com.hjava;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Iterator;

import org.apache.lucene.search.spell.TermFreqIterator;
import org.apache.lucene.search.suggest.FileDictionary;
import org.apache.lucene.util.BytesRef;

public class FileSuggestDemo {

	public FileSuggestDemo() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		InputStreamReader dictFile =new InputStreamReader(new FileInputStream("F:/solrwork/demo.txt"));
		FileDictionary dictionary =new FileDictionary(dictFile); 
		 TermFreqIterator freqIterator=dictionary.getWordsIterator();
			 BytesRef ref= freqIterator.next();
			
			 while(ref!=null){
				 System.out.println(ref.utf8ToString());
				 ref= freqIterator.next();
			 }
		     dictFile.close();
	}

}
