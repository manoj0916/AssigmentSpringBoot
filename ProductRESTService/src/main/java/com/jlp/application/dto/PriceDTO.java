package com.jlp.application.dto;

public class PriceDTO {
	 private String was;
	 private String then1;
	 private String then2;
	 private Object now;
	 private String uom;
	 private String currency;


	 // Getter Methods 

	 public String getWas() {
	  return was;
	 }

	 public String getThen1() {
	  return then1;
	 }

	 public String getThen2() {
	  return then2;
	 }

	 public Object getNow() {
	  return this.now;
	 }

	 public String getUom() {
	  return uom;
	 }

	 public String getCurrency() {
	  return currency;
	 }

	 // Setter Methods 

	 public void setWas(String was) {
	  this.was = was;
	 }

	 public void setThen1(String then1) {
	  this.then1 = then1;
	 }

	 public void setThen2(String then2) {
	  this.then2 = then2;
	 }


	public void setNow(Object now) {
			this.now = now;
	}

	 public void setUom(String uom) {
	  this.uom = uom;
	 }

	 public void setCurrency(String currency) {
	  this.currency = currency;
	 }}
