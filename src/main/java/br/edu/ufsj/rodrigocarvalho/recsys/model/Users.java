package br.edu.ufsj.rodrigocarvalho.recsys.model;

public class Users {
	
	private final String userId;
	private final String name;
	
	private final Long fans;

	public Users(String userId, String name, Long fans) {
		this.userId = userId;
		this.name = name;
		this.fans = fans;
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
	
	
}
