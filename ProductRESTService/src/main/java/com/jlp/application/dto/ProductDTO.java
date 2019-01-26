package com.jlp.application.dto;

import java.util.ArrayList;

public class ProductDTO {
	 private String productId;
	 private String type;
	 private String title;
	 private String code;
	 private float averageRating;
	 private float reviews;
	 PriceDTO PriceObject;
	 private String image;
	 ArrayList < Object > additionalServices = new ArrayList < Object > ();
	 private String displaySpecialOffer;
	 PromoMessages PromoMessagesObject;
	 private String nonPromoMessage;
	 private String defaultSkuId;
	 ArrayList < ColorSwatcheDTO > colorSwatches = new ArrayList < ColorSwatcheDTO > ();
	 private float colorSwatchSelected;
	 private String colorWheelMessage;
	 private boolean outOfStock;
	 private boolean emailMeWhenAvailable;
	 private String availabilityMessage;
	 private boolean compare;
	 private String fabric;
	 private boolean swatchAvailable;
	 private boolean categoryQuickViewEnabled;
	 private String swatchCategoryType;
	 private String brand;
	 private float ageRestriction;
	 private boolean isInStoreOnly;
	 private boolean isMadeToMeasure;
	 private boolean isBundle;
	 private boolean isProductSet;
	 ArrayList < Object > promotionalFeatures = new ArrayList < Object > ();
	 ArrayList < Object > features = new ArrayList < Object > ();
	 DynamicAttributes DynamicAttributesObject;
	 private String directorate;
	 private float releaseDateTimestamp;

	 // Getter Methods 

	 public PriceDTO getPriceObject() {
		return PriceObject;
	}

	public void setPriceObject(PriceDTO priceObject) {
		PriceObject = priceObject;
	}

	public ArrayList<ColorSwatcheDTO> getColorSwatches() {
		return colorSwatches;
	}

	public void setColorSwatches(ArrayList<ColorSwatcheDTO> colorSwatches) {
		this.colorSwatches = colorSwatches;
	}

	public String getProductId() {
	  return productId;
	 }

	 public String getType() {
	  return type;
	 }

	 public String getTitle() {
	  return title;
	 }

	 public String getCode() {
	  return code;
	 }

	 public float getAverageRating() {
	  return averageRating;
	 }

	 public float getReviews() {
	  return reviews;
	 }

	 public PriceDTO getPrice() {
	  return PriceObject;
	 }

	 public String getImage() {
	  return image;
	 }

	 public String getDisplaySpecialOffer() {
	  return displaySpecialOffer;
	 }

	 public PromoMessages getPromoMessages() {
	  return PromoMessagesObject;
	 }

	 public String getNonPromoMessage() {
	  return nonPromoMessage;
	 }

	 public String getDefaultSkuId() {
	  return defaultSkuId;
	 }

	 public float getColorSwatchSelected() {
	  return colorSwatchSelected;
	 }

	 public String getColorWheelMessage() {
	  return colorWheelMessage;
	 }

	 public boolean getOutOfStock() {
	  return outOfStock;
	 }

	 public boolean getEmailMeWhenAvailable() {
	  return emailMeWhenAvailable;
	 }

	 public String getAvailabilityMessage() {
	  return availabilityMessage;
	 }

	 public boolean getCompare() {
	  return compare;
	 }

	 public String getFabric() {
	  return fabric;
	 }

	 public boolean getSwatchAvailable() {
	  return swatchAvailable;
	 }

	 public boolean getCategoryQuickViewEnabled() {
	  return categoryQuickViewEnabled;
	 }

	 public String getSwatchCategoryType() {
	  return swatchCategoryType;
	 }

	 public String getBrand() {
	  return brand;
	 }

	 public float getAgeRestriction() {
	  return ageRestriction;
	 }

	 public boolean getIsInStoreOnly() {
	  return isInStoreOnly;
	 }

	 public boolean getIsMadeToMeasure() {
	  return isMadeToMeasure;
	 }

	 public boolean getIsBundle() {
	  return isBundle;
	 }

	 public boolean getIsProductSet() {
	  return isProductSet;
	 }

	 public DynamicAttributes getDynamicAttributes() {
	  return DynamicAttributesObject;
	 }

	 public String getDirectorate() {
	  return directorate;
	 }

	 public float getReleaseDateTimestamp() {
	  return releaseDateTimestamp;
	 }

	 // Setter Methods 

	 public void setProductId(String productId) {
	  this.productId = productId;
	 }

	 public void setType(String type) {
	  this.type = type;
	 }

	 public void setTitle(String title) {
	  this.title = title;
	 }

	 public void setCode(String code) {
	  this.code = code;
	 }

	 public void setAverageRating(float averageRating) {
	  this.averageRating = averageRating;
	 }

	 public void setReviews(float reviews) {
	  this.reviews = reviews;
	 }

	 public void setPrice(PriceDTO priceObject) {
	  this.PriceObject = priceObject;
	 }

	 public void setImage(String image) {
	  this.image = image;
	 }

	 public void setDisplaySpecialOffer(String displaySpecialOffer) {
	  this.displaySpecialOffer = displaySpecialOffer;
	 }

	 public void setPromoMessages(PromoMessages promoMessagesObject) {
	  this.PromoMessagesObject = promoMessagesObject;
	 }

	 public void setNonPromoMessage(String nonPromoMessage) {
	  this.nonPromoMessage = nonPromoMessage;
	 }

	 public void setDefaultSkuId(String defaultSkuId) {
	  this.defaultSkuId = defaultSkuId;
	 }

	 public void setColorSwatchSelected(float colorSwatchSelected) {
	  this.colorSwatchSelected = colorSwatchSelected;
	 }

	 public void setColorWheelMessage(String colorWheelMessage) {
	  this.colorWheelMessage = colorWheelMessage;
	 }

	 public void setOutOfStock(boolean outOfStock) {
	  this.outOfStock = outOfStock;
	 }

	 public void setEmailMeWhenAvailable(boolean emailMeWhenAvailable) {
	  this.emailMeWhenAvailable = emailMeWhenAvailable;
	 }

	 public void setAvailabilityMessage(String availabilityMessage) {
	  this.availabilityMessage = availabilityMessage;
	 }

	 public void setCompare(boolean compare) {
	  this.compare = compare;
	 }

	 public void setFabric(String fabric) {
	  this.fabric = fabric;
	 }

	 public void setSwatchAvailable(boolean swatchAvailable) {
	  this.swatchAvailable = swatchAvailable;
	 }

	 public void setCategoryQuickViewEnabled(boolean categoryQuickViewEnabled) {
	  this.categoryQuickViewEnabled = categoryQuickViewEnabled;
	 }

	 public void setSwatchCategoryType(String swatchCategoryType) {
	  this.swatchCategoryType = swatchCategoryType;
	 }

	 public void setBrand(String brand) {
	  this.brand = brand;
	 }

	 public void setAgeRestriction(float ageRestriction) {
	  this.ageRestriction = ageRestriction;
	 }

	 public void setIsInStoreOnly(boolean isInStoreOnly) {
	  this.isInStoreOnly = isInStoreOnly;
	 }

	 public void setIsMadeToMeasure(boolean isMadeToMeasure) {
	  this.isMadeToMeasure = isMadeToMeasure;
	 }

	 public void setIsBundle(boolean isBundle) {
	  this.isBundle = isBundle;
	 }

	 public void setIsProductSet(boolean isProductSet) {
	  this.isProductSet = isProductSet;
	 }

	 public void setDynamicAttributes(DynamicAttributes dynamicAttributesObject) {
	  this.DynamicAttributesObject = dynamicAttributesObject;
	 }

	 public void setDirectorate(String directorate) {
	  this.directorate = directorate;
	 }

	 public void setReleaseDateTimestamp(float releaseDateTimestamp) {
	  this.releaseDateTimestamp = releaseDateTimestamp;
	 }}
