package controllers;

import java.util.Map;

import models.Book;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.autoFormForm;
import views.html.autoFormResult;
import forms.BookForm;

public class FormsWork extends Controller {
	public static Result showAutoForm() {

		return ok(autoFormForm.render());
	}

	public static Result autoForm() {
		Form<BookForm> form = Form.form(BookForm.class).bindFromRequest();

		if (form.hasErrors()) {
			prepareResponseWithErrors(form);
			return ok(autoFormForm.render());
		}

		BookForm bookForm = form.get();
		Book book = parseForm(bookForm);
		// save in data base, operations

		return ok(autoFormResult.render(book));
	}

	private static void prepareResponseWithErrors(Form<BookForm> form) {
		prepareErrors(form);
		prepareFormDataToError();

	}

	private static void prepareErrors(Form<BookForm> form) {
		for (final String propertyName : form.errors().keySet()) {

			String errorMessage = form.errorsAsJson().findValue(propertyName)
					.get(0).asText().replace('[', ' ').replace(']', ' ');

			flash().put(propertyName + "-error", errorMessage);
		}
	}

	private static void prepareFormDataToError() {
		final Map<String, String[]> formData = request().body()
				.asFormUrlEncoded();
		flash().put("isbn", formData.get("isbn")[0]);
		flash().put("title", formData.get("title")[0]);
		flash().put("author", formData.get("author")[0]);
		flash().put("nPages", formData.get("nPages")[0]);

	}

	private static Book parseForm(BookForm bookForm) {
		Book book = new Book();
		book.id = (bookForm.id != null) ? bookForm.id : 0;
		book.isbn = bookForm.isbn;
		book.title = bookForm.title;
		book.author = bookForm.author;
		book.nPages = bookForm.nPages;

		// Obtain inner objects through id from form(asking database), etc

		return book;
	}
}
