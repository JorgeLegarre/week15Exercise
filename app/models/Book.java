package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public final class Book {

	@Id
	@GeneratedValue
	public Integer id;
	public Integer isbn;
	public String title;
	public String author;
	public Integer nPages;

}
