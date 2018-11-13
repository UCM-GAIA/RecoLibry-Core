package es.ucm.fdi.gaia.recolibry.examples.test1;

import java.util.Arrays;

import es.ucm.fdi.gaia.jcolibri.cbrcore.Attribute;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseComponent;

public class MovieCase implements CaseComponent {

	private int id;
	private String title;
	private String[] genres;
	
	public MovieCase(int id, String title, String[] genres) {
		super();
		this.id = id;
		this.title = title;
		this.genres = genres;
	}

	public Attribute getIdAttribute() {
		return new Attribute("id", MovieCase.class);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String[] getGenres() {
		return genres;
	}

	public void setGenres(String[] genres) {
		this.genres = genres;
	}

	@Override
	public String toString() {
		return "MovieCase [id=" + id + ", title=" + title + ", genres=" + Arrays.toString(genres) + "]";
	}
	
}
