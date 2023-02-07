package org.source.com;

public class User {

	private String name;
	private String eamil;

	public User() {
	}

	public User(String name, String eamil) {
		this.name = name;
		this.eamil = eamil;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEamil() {
		return eamil;
	}

	public void setEamil(String eamil) {
		this.eamil = eamil;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", eamil=" + eamil + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (!(obj instanceof User)) {
			return false;
		}

		User c = (User) obj;

		if (c.name == null || c.eamil == null || name == null || eamil == null)
			return false;

		return name.equalsIgnoreCase(c.name) == true && eamil.equalsIgnoreCase(c.eamil) == true;
	}

}
