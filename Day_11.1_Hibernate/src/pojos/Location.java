package pojos;

import javax.persistence.*;

@Entity
@Table(name = "locations_tbl")
public class Location extends BaseEntity {

	@Column(length = 20)
	private String city;
	@Column(length = 20)
	private String state;
	@Column(length = 20)
	private String country;
	// unidirectional one to one association with vendor
	@OneToOne
	@JoinColumn(name = "vendor_id")
	// w/o mapsId there would be separate primary key for location table i.e 5
	// column id ,city, state, country, vendor_id
	// how to tell hibernate to create shared pk with vendors table : mapsId
	@MapsId // after adding this column in location table : vendor_id(shared PK : both fk &
			// pk referencing PK of vendors table), city, state, country
	private Vendor currentVendor;

	public Location() {
		System.out.println("In location ctor...");
	}

	public Location(String city, String state, String country) {
		super();
		this.city = city;
		this.state = state;
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Vendor getCurrentVendor() {
		return currentVendor;
	}

	public void setCurrentVendor(Vendor currentVendor) {
		this.currentVendor = currentVendor;
	}

	@Override
	public String toString() {
		return "Location [city=" + city + ", state=" + state + ", country=" + country + "]";
	}

}
