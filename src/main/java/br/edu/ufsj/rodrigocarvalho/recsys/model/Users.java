package br.edu.ufsj.rodrigocarvalho.recsys.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Users {
	
	@Id	
	private final String userId;
	private final String name;	
	private final Long fans;
	private final Double averageStars;
	
	@OneToMany
	private List<Users> friends;

	public Users(String userId, String name, Long fans, Double averageStars) {
		this.userId = userId;
		this.name = name;
		this.fans = fans;
		this.averageStars = averageStars;
		
		this.friends = new ArrayList<Users>();
	}
	
	public void addFriend(Users user) {
		this.friends.add(user);
	}
	
	public List<Users> getFriends(){
		return Collections.unmodifiableList(friends);
	}
	
	public String getUserId() {
		return userId;
	}
	
	public String getName() {
		return name;
	}
	
	public Long getFans() {
		return fans;
	}
	
	public Double getAverageStars() {
		return averageStars;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		Users other = (Users) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "[" + this.userId + ", " + this.name + ", " + this. fans + ", " + this.averageStars + "]" ; 
	}
	
}
