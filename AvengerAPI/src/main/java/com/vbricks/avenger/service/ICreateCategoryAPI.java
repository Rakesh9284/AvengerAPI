package com.vbricks.avenger.service;

import org.apache.commons.lang.RandomStringUtils;

public interface ICreateCategoryAPI {

	String categoryname="Category_"+RandomStringUtils.randomAlphanumeric(10);
	String childcategoryname="childCategory_"+RandomStringUtils.randomAlphanumeric(10);
	String categorynamenull=" ";
	
	String name="name";
	String parentCategoryId="ParentCategoryId";
	String cID="categoryId";
}
