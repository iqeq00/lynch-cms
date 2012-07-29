package com.lynch.cms.common.collection;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.google.common.base.Splitter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;

/**
 * guava的集合类型例子
 * 
 * @author Lynch
 */
public class AdvancedCollectionTypeDemo {

	/**
	 * Guava的Table等于有兩个key的Map，可用来替代Map<String,Map<String,Object>
	 */
	@Test
	public void table() {
		
		Table<Integer, String, String> table = HashBasedTable.create();
		table.put(1, "a", "1a");
		table.put(1, "b", "1b");
		table.put(2, "a", "2a");
		table.put(2, "b", "2b");
		assertEquals("2a", table.get(2, "a"));
	}

	/**
	 * Guava的同一个key可以有多个value的Map.
	 */
	@Test
	public void multiMap() {
		
		Multimap<Integer, String> multimap = HashMultimap.create();
		multimap.put(1, "a");
		multimap.put(2, "b");
		multimap.put(3, "c");
		multimap.put(1, "a2");
		Collection<String> values = multimap.get(1);
		assertEquals(2, values.size());
	}
	
	/**
	 * 不可修改的集合 
	 * 
	 *  Immutable 集合的实现：ImmutableList，ImmutableMap，ImmutableSortedSet，ImmutableSortedMap。
	 */
	public void immutableSet() {
		
		//保护原本的set
		Set<String> set = new HashSet<String>(Arrays.asList(new String[]{"RED", "GREEN"})); 
		ImmutableSet<String> immutableSet = ImmutableSet.copyOf(set); 
		//自己声明
		ImmutableSet<String> immutableSet1 = ImmutableSet.of("RED", "GREEN"); 
		//builder方式加入初始化，不但能加入单个元素还能加入既有的集合。
		Builder<String>  builder = ImmutableSet.builder(); 
		ImmutableSet<String> immutableSet2 = builder.add("RED").addAll(set).build(); 
	}
	
	/**
	 * 统计1个List<String>中某个字符串出现的次数 
	 */
	@Test
	public void getStrSum() {
		
		//构造字符串list
		List<String> strList = new ArrayList<String>();
		strList.add("the");
		strList.add("the");
		strList.add("a");
		strList.add("a");
		strList.add("b");
		strList.add("the");
		
		//传统做法
		Map<String, Integer> map = new HashMap<String, Integer>(); 
		 for(String word : strList){ 
		    Integer count = map.get(word); 
		    map.put(word, (count == null) ? 1 : count + 1); 
		 } 
		 Integer countOld = map.get("the"); 	
		 
		 //guava做法
		 HashMultiset<String> multiSet = HashMultiset.create(); 
		 multiSet.addAll(strList); 
		 Integer countNew = multiSet.count("the"); 
		 assertEquals(countOld, countNew);
	}
	
	/**
	 * Splitter 字符串做分割 
	 */
	@Test
	public void testSplitter() {
		
		//最简单的
		Splitter sp = Splitter.on(',');  
		Iterable<String> is =  sp.split("one,two,three");  
		for(String s:is) {  
	        System.out.println(s);
	    }  
		System.out.println("$$$$$$$$$$$$$");
		//复杂点的(去掉空值)
		Splitter sp1 = Splitter.on(',').omitEmptyStrings();  
		Iterable<String> is1 =  sp1.split("one,,two,three");
		for(String s:is1) {  
	        System.out.println(s);
	    }  
		System.out.println("$$$$$$$$$$$$$");
		//复杂点的(去掉空白)
		Splitter sp2 = Splitter.on(',').trimResults();  
		Iterable<String> is2 =  sp2.split(" one ,  two , three    ");
		for(String s:is2) {  
	        System.out.println(s);
	    }  
	}
	
	
	@Test
	public void MutliMapTest() {
		
		Multimap<String,String> myMultimap = ArrayListMultimap.create();
			  
		// Adding some key/value
		myMultimap.put("Fruits", "Bannana");
		myMultimap.put("Fruits", "Apple");
		myMultimap.put("Fruits", "Pear");
		myMultimap.put("Vegetables", "Carrot");
		  
		// Getting the size
		int size = myMultimap.size();
		System.out.println(size);  // 4
		  
		// Getting values
		Collection<String> fruits = myMultimap.get("Fruits");
		System.out.println(fruits); // [Bannana, Apple, Pear]
		  
		Collection<String> vegetables = myMultimap.get("Vegetables");
		System.out.println(vegetables); // [Carrot]
		  
		// Iterating over entire Mutlimap
		for(String value : myMultimap.values()) {
		System.out.println(value);
		}
		  
		// Removing a single value
		myMultimap.remove("Fruits","Pear");
		System.out.println(myMultimap.get("Fruits")); // [Bannana, Pear]
		  
		// Remove all values for a key
		myMultimap.removeAll("Fruits");
		System.out.println(myMultimap.get("Fruits")); // [] (Empty Collection!)
	}
	
	/**
	 * BiMaps演示 
	 */
	@Test
	public void testBiMaps() {
		
		//新的Map创建方法
		Map<String,String> britishToAmerican = Maps.newHashMap();
		britishToAmerican.put("aubergine","egglant");
		britishToAmerican.put("courgette","zucchini");
		britishToAmerican.put("jam","jelly");
		
		
		BiMap<String,String> britishToAmerican1 = HashBiMap.create();
		britishToAmerican1.put("aubergine","egglant");
		britishToAmerican1.put("courgette","zucchini");
		britishToAmerican1.put("jam","jelly");
		System.out.println(britishToAmerican1.get("aubergine")); 			// eggplant
		
		
		//反向映射，BiMap强制其value的唯一性，如果发现违规则会抛出 IllegalArgumentException
		//关于inverse方法 
		//另外一个关键点就是理解inverse方法，这个方法返回一个反转 
		//后的BiMap，即key/value互相切换的映射。 
		//这个反转的map并不是一个新的map，而是一个视图，这意味着， 
		//你在这个反转后的map中的任何增删改操作都会影响原来的map 
		BiMap<String,String> americanToBritish = britishToAmerican1.inverse();
		System.out.println(americanToBritish.get("eggplant")); 				// aubergine
		System.out.println(americanToBritish.get("zucchini")); 				// courgette
		
		//使用forcePut覆盖原来的值
		americanToBritish.put("pudding","dessert");
		americanToBritish.forcePut("sweet","dessert");  // Overwrites the previous entry
		System.out.println(americanToBritish.get("sweet")); // dessert
		System.out.println(americanToBritish.get("pudding")); // null
	}
	
	/**
	 * guava支持的创建类型 
	 */
	@Test
	public void testCreatCollection() {
	
		Multimap<String,String> multimap2 = ArrayListMultimap.create();
		//对jdk的新支持
		List<String> myList1 = new ArrayList<String>(); //old way  
		List<String> myList2 = Lists.newArrayList(); //guava way  
		Set<String> mySet1 = new HashSet<String>(); //old way  
		Set<String> mySet2 = Sets.newHashSet(); //guava way  
		Map<String,Object> map1 = new HashMap<String, Object>();
		Map<String,Object> map2 = Maps.newHashMap();
	}
	
}
