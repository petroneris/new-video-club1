package com.snezana.videoclub.model;

/**
 * Placeholder for WebSocket messages and events.
 */
public class EvtWSMessage {

	private Integer flmId;
	private String title;
	private String genre;
	private Integer year;
	private String username;
	private ActionType action;

	public EvtWSMessage(final Integer flmId, final String title, final String genre, final Integer year,
			final String username, final ActionType action) {
		super();
		this.flmId = flmId;
		this.title = title;
		this.genre = genre;
		this.year = year;
		this.username = username;
		this.action = action;
	}

	/**
	 * Action types detected by WS messages.
	 */
	public enum ActionType {
		RENTED, RETURNED
	}

	public Integer getFlmId() {
		return flmId;
	}

	public String getTitle() {
		return title;
	}

	public String getGenre() {
		return genre;
	}

	public Integer getYear() {
		return year;
	}

	public String getUsername() {
		return username;
	}

	public ActionType getAction() {
		return action;
	}

}
