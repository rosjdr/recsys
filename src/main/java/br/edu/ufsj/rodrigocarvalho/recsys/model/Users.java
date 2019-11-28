package br.edu.ufsj.rodrigocarvalho.recsys.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(schema = "recsys")
public class Users {

	@Id
	private String userId;
	private String name;
	private Long fans;
	private Double averageStars;

	@Transient
	private String friendsStr;
	
	@JoinTable(name = "friends", schema = "recsys", joinColumns = {
			@JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false, foreignKey = @javax.persistence.ForeignKey(foreignKeyDefinition = "none", value = ConstraintMode.NO_CONSTRAINT)), }, 
			inverseJoinColumns = {@JoinColumn(name = "friendId", referencedColumnName = "userId", nullable = false, foreignKey = @javax.persistence.ForeignKey(foreignKeyDefinition = "none", value = ConstraintMode.NO_CONSTRAINT)) })
	@ManyToMany
	private Collection<Users> friends;

	public Users() {
	}
	
	public Users(String userId, String name, Long fans, Double averageStars) {
		this.userId = userId;
		this.name = name;
		this.fans = fans;
		this.averageStars = averageStars;

		this.friends = new ArrayList<Users>();
	}

	public String getFriendsStr() {
		return friendsStr;
	}

	public void setFriendsStr(String friendsStr) {
		this.friendsStr = friendsStr;
	}

	public void addFriend(Users user) {
		this.friends.add(user);
	}

	public Collection<Users> getFriends() {
		return Collections.unmodifiableCollection(friends);
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

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFans(Long fans) {
		this.fans = fans;
	}

	public void setAverageStars(Double averageStars) {
		this.averageStars = averageStars;
	}

	public void setFriends(Collection<Users> friends) {
		this.friends = friends;
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
		return "[" + this.userId + ", " + this.name + ", " + this.fans + ", " + this.averageStars + "]";
	}

}
