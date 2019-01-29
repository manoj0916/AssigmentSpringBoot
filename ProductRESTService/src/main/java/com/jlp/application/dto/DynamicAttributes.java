package com.jlp.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DynamicAttributes {
		 private String newinproducttype;
		 private String washinginstructions;
		 private String dressbyoccasion;
		 private String dressshape;
		 private String typeofpattern;


		 // Getter Methods 

		 public String getNewinproducttype() {
		  return newinproducttype;
		 }

		 public String getWashinginstructions() {
		  return washinginstructions;
		 }

		 public String getDressbyoccasion() {
		  return dressbyoccasion;
		 }

		 public String getDressshape() {
		  return dressshape;
		 }

		 public String getTypeofpattern() {
		  return typeofpattern;
		 }

		 // Setter Methods 

		 public void setNewinproducttype(String newinproducttype) {
		  this.newinproducttype = newinproducttype;
		 }

		 public void setWashinginstructions(String washinginstructions) {
		  this.washinginstructions = washinginstructions;
		 }

		 public void setDressbyoccasion(String dressbyoccasion) {
		  this.dressbyoccasion = dressbyoccasion;
		 }

		 public void setDressshape(String dressshape) {
		  this.dressshape = dressshape;
		 }

		 public void setTypeofpattern(String typeofpattern) {
		  this.typeofpattern = typeofpattern;
		 }
}
