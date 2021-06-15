package net.guides.springboot.crud.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "Movie")
public class Movie {

	@Transient
    public static final String SEQUENCE_NAME = "users_sequence";
	
	@Id
	private long id;
	
	@Indexed(unique=true)
	private String name;
	
	private long year;
	
	private String image;
	
	private String description;
	
	private String premiere;
	
	private long tickets;
	
	public Movie() {
		
	}


	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + name + ", year=" + year + ", image=" + image + ", description="
				+ description + ", premiere=" + premiere + ", tickets=" + tickets + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getYear() {
		return year;
	}

	public void setYear(long year) {
		this.year = year;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPremiere() {
		return premiere;
	}

	public void setPremiere(String premiere) {
		this.premiere = premiere;
	}

	public long getTickets() {
		return tickets;
	}

	public void setTickets(long tickets) {
		this.tickets = tickets;
	}

	public Movie(long tickets) {
		this.tickets = tickets;
	}
	

}
