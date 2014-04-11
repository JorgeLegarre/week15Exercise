package controllers;

import java.util.List;
import java.util.Map;

import models.Book;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.ajaxIndex;
import views.html.ajaxResult;

import com.avaje.ebean.Ebean;

public class AjaxWork extends Controller {

	public static Result index() {
		return ok(ajaxIndex.render(""));
	}

	public static Result getBooks() {

		List<Book> books = Ebean.find(Book.class).findList();

		return ok(Json.toJson(books));

	}

	public static Result getBook(int id) {

		Book book = Ebean.find(Book.class, id);

		if (book == null) {
			return notFound("not found!");
		}

		return ok(ajaxResult.render(book));
	}

	public static Result putBook() {
		Book book = parseBook();
		;
		if (book.id == 0) {
			Ebean.save(book);
		} else {
			Ebean.update(book);
		}

		return ok();
	}

	public static Result deleteBook(int id) {

		Book book = Ebean.find(Book.class, id);
		if (book != null) {
			Ebean.delete(book);
		}

		return ok();

	}

	private static Book parseBook() {
		Map<String, String[]> form = request().body().asFormUrlEncoded();

		Book book = new Book();
		book.id = Integer.parseInt(form.get("id")[0]);
		book.isbn = Integer.parseInt(form.get("isbn")[0]);
		book.title = form.get("title")[0];
		book.author = form.get("author")[0];
		book.nPages = Integer.parseInt(form.get("nPages")[0]);

		return book;
	}

}
