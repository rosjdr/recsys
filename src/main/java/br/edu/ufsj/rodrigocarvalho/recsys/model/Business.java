package br.edu.ufsj.rodrigocarvalho.recsys.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "recsys")
public class Business {
	
	@Id
	private String businessId;
	private String name;
	private String city;
	
	@Column(columnDefinition =  "NUMERIC(13,7)") 
	private BigDecimal latitude;
	
	@Column(columnDefinition =  "NUMERIC(13,7)") 
	private BigDecimal longitude;
	
	@Column(columnDefinition =  "NUMERIC(5,3)") 
	private BigDecimal stars;
	private Long reviewCount;
	private Long isOpen;
	
	@Column(length = 1000)
	private String categories;
	
	public Business() {
		
	}
	
	public Business(String businessId, String name, String city, BigDecimal latitude, BigDecimal longitude,
			BigDecimal stars, Long reviewCount, Long isOpen, String categories) {
		super();
		this.businessId = businessId;
		this.name = name;
		this.city = city;
		this.latitude = latitude;
		this.longitude = longitude;
		this.stars = stars;
		this.reviewCount = reviewCount;
		this.isOpen = isOpen;
		this.categories = categories;
	}
	
	public String getBusinessId() {
		return businessId;
	}
	
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public BigDecimal getLatitude() {
		return latitude;
	}
	
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	
	public BigDecimal getLongitude() {
		return longitude;
	}
	
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}
	
	public BigDecimal getStars() {
		return stars;
	}
	
	public void setStars(BigDecimal stars) {
		this.stars = stars;
	}
	
	public Long getReviewCount() {
		return reviewCount;
	}
	
	public void setReviewCount(Long reviewCount) {
		this.reviewCount = reviewCount;
	}
	
	public Long getIsOpen() {
		return isOpen;
	}
	
	public void setIsOpen(Long isOpen) {
		this.isOpen = isOpen;
	}
	
	public String getCategories() {
		return categories;
	}
	
	public void setCategories(String categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "Business [businessId=" + businessId + ", name=" + name + ", city=" + city + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", stars=" + stars + ", reviewCount=" + reviewCount + ", isOpen="
				+ isOpen + ", categories=" + categories + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((businessId == null) ? 0 : businessId.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Business other = (Business) obj;
		if (businessId == null) {
			if (other.businessId != null)
				return false;
		} else if (!businessId.equals(other.businessId))
			return false;
		return true;
	}
	

}
