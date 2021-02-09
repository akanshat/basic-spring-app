package com.rakuten.training.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "review")
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String reviewer;
	String reviewText;
	float rating;

	// whatever persist operation we perform in review it will be done in product
	// too, if we update a review the contained product will also the merged, if a
	// new prod is added and it contains reviews, we want its reviews to be delete
	// along with the product, therefore cascade = persist

	// SRP single responsibility principle
	// only once access of change, all persistence related stuff about Reviews is
	// responsibility, of this class
	// but with inclusion of @JsonIgnore, we have completely changed what it is
	// meant to do, we added this annotation so that we don't get the same copy of
	// the product in about a 100 review objects fetched for a particular product

	@JsonIgnore // ignores product when review objects converted to json
	@ManyToOne
	@JoinColumn(name = "p_id") // foreign key
	Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Review() {

	}

	public Review(String reviewer, String reviewText, float rating) {
		super();
		this.reviewer = reviewer;
		this.reviewText = reviewText;
		this.rating = rating;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

}
