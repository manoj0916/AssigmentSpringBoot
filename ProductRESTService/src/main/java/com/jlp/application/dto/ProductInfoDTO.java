package com.jlp.application.dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductInfoDTO {
	 private ArrayList <ProductDTO> products = new ArrayList <ProductDTO> ();
	 private ArrayList <Object> facets = new ArrayList <Object> ();
	 private String categoryTitle;
	 private float childCategoriesCount;
	 private SeoInformation SeoInformationObject;
	 private float results;
	 private float pagesAvailable;
	 private ArrayList <Object> crumbs = new ArrayList <Object> ();
	 private String dynamicBannerId;
	 private String seoBannerId;
	 private String redirectUrl;
	 private ArrayList <Object> staticLinks = new ArrayList <Object> ();
	 private String selectedDept;
	 private String multiCatSelected;
	 private String endecaCanonical;
	 private String labelType;

	 public String getLabelType() {
		return labelType;
	}

	public void setLabelType(String labelType) {
		this.labelType = labelType;
	}

	public String getCategoryTitle() {
	  return categoryTitle;
	 }

	 public float getChildCategoriesCount() {
	  return childCategoriesCount;
	 }

	 public SeoInformation getSeoInformation() {
	  return SeoInformationObject;
	 }

	 public float getResults() {
	  return results;
	 }

	 public float getPagesAvailable() {
	  return pagesAvailable;
	 }

	 public String getDynamicBannerId() {
	  return dynamicBannerId;
	 }

	 public String getSeoBannerId() {
	  return seoBannerId;
	 }

	 public String getRedirectUrl() {
	  return redirectUrl;
	 }

	 public String getSelectedDept() {
	  return selectedDept;
	 }

	 public String getMultiCatSelected() {
	  return multiCatSelected;
	 }

	 public String getEndecaCanonical() {
	  return endecaCanonical;
	 }

	 public void setCategoryTitle(String categoryTitle) {
	  this.categoryTitle = categoryTitle;
	 }

	 public void setChildCategoriesCount(float childCategoriesCount) {
	  this.childCategoriesCount = childCategoriesCount;
	 }

	 public void setSeoInformation(SeoInformation seoInformationObject) {
	  this.SeoInformationObject = seoInformationObject;
	 }

	 public void setResults(float results) {
	  this.results = results;
	 }

	 public void setPagesAvailable(float pagesAvailable) {
	  this.pagesAvailable = pagesAvailable;
	 }

	 public void setDynamicBannerId(String dynamicBannerId) {
	  this.dynamicBannerId = dynamicBannerId;
	 }

	 public void setSeoBannerId(String seoBannerId) {
	  this.seoBannerId = seoBannerId;
	 }

	 public void setRedirectUrl(String redirectUrl) {
	  this.redirectUrl = redirectUrl;
	 }

	 public void setSelectedDept(String selectedDept) {
	  this.selectedDept = selectedDept;
	 }

	 public void setMultiCatSelected(String multiCatSelected) {
	  this.multiCatSelected = multiCatSelected;
	 }

	 public void setEndecaCanonical(String endecaCanonical) {
	  this.endecaCanonical = endecaCanonical;
	 }

	public ArrayList<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<ProductDTO> products) {
		this.products = products;
	}

	public ArrayList<Object> getFacets() {
		return facets;
	}

	public void setFacets(ArrayList<Object> facets) {
		this.facets = facets;
	}

	public SeoInformation getSeoInformationObject() {
		return SeoInformationObject;
	}

	public void setSeoInformationObject(SeoInformation seoInformationObject) {
		SeoInformationObject = seoInformationObject;
	}

	public ArrayList<Object> getCrumbs() {
		return crumbs;
	}

	public void setCrumbs(ArrayList<Object> crumbs) {
		this.crumbs = crumbs;
	}

	public ArrayList<Object> getStaticLinks() {
		return staticLinks;
	}

	public void setStaticLinks(ArrayList<Object> staticLinks) {
		this.staticLinks = staticLinks;
	}
	 
	 
	}
	