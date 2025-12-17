package es.unex.cum.tw.rest;

import java.util.Objects;

public class Planet {

	private int id;
	private String name;
	private float radius;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getRadius() {
		return radius;
	}
	public void setRadius(float radius) {
		this.radius = radius;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, name, radius);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planet other = (Planet) obj;
		return id == other.id && Objects.equals(name, other.name)
				&& Float.floatToIntBits(radius) == Float.floatToIntBits(other.radius);
	}
	@Override
	public String toString() {
		return "Planet [id=" + id + ", name=" + name + ", radius=" + radius + "]";
	}
	public Planet(int id, String name, float radius) {
		super();
		this.id = id;
		this.name = name;
		this.radius = radius;
	}
	
}
