package com.example;
public class Shop {
	 
	 private int id;
	 private String title;
	 private double price;
	 
	 public Shop() {
	 
	 }
	 
	 public Shop(String _title, double _price) {
	  title = _title;
	  price = _price;
	 }
	 
	 public Shop(int _id, String _title, double _price) {
	  id = _id;
	  title = _title;
	  price = _price;
	 }
	 
	 public void setId(int _id) {
	  id = _id;
	 }
	 
	 public void setTitle(String _title) {
	  title = _title;
	 }
	 
	 public void setPrice(double _price) {
	  price = _price;
	 }
	 
	 public int getId() { return id; }
	 
	 public String getTitle() { return title; }
	 
	 public double getPrice() { return price; }
	}