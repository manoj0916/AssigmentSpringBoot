package com.jlp.application.dto;

public class PromoMessages {

	 private String priceMatched;
	 private String offer;
	 private String customPromotionalMessage;
	 private String bundleHeadline;
	 CustomSpecialOffer CustomSpecialOfferObject;


	 // Getter Methods 

	 public String getPriceMatched() {
	  return priceMatched;
	 }

	 public String getOffer() {
	  return offer;
	 }

	 public String getCustomPromotionalMessage() {
	  return customPromotionalMessage;
	 }

	 public String getBundleHeadline() {
	  return bundleHeadline;
	 }

	 public CustomSpecialOffer getCustomSpecialOffer() {
	  return CustomSpecialOfferObject;
	 }

	 // Setter Methods 

	 public void setPriceMatched(String priceMatched) {
	  this.priceMatched = priceMatched;
	 }

	 public void setOffer(String offer) {
	  this.offer = offer;
	 }

	 public void setCustomPromotionalMessage(String customPromotionalMessage) {
	  this.customPromotionalMessage = customPromotionalMessage;
	 }

	 public void setBundleHeadline(String bundleHeadline) {
	  this.bundleHeadline = bundleHeadline;
	 }

	 public void setCustomSpecialOffer(CustomSpecialOffer customSpecialOfferObject) {
	  this.CustomSpecialOfferObject = customSpecialOfferObject;
	 }
}
