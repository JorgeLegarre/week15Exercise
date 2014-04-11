package forms;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Min;
import play.data.validation.Constraints.Required;

public final class BookForm {
	public Integer id;

	@Required
	@Min(1)
	public Integer isbn;
	// I know isbn is a String because have mixed numbers and letters, but its
	// only an example

	@Required
	@MaxLength(255)
	public String title;

	@Required
	@MaxLength(255)
	public String author;

	@Min(1)
	public Integer nPages;
}
