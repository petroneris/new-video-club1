package com.snezana.videoclub.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.snezana.videoclub.dao.AdminDao;
import com.snezana.videoclub.model.EvtWSMessage;
import com.snezana.videoclub.model.EvtWSMessage.ActionType;
import com.snezana.videoclub.model.Film;
import com.snezana.videoclub.model.FilmData;
import com.snezana.videoclub.model.User;
import com.snezana.videoclub.model.UserData;
import com.snezana.videoclub.service.EventWSService;
import com.snezana.videoclub.util.FilmTimeUtil;

/**
 * Controller class for role 'Administrator' activities.
 */
@Controller
@RequestMapping("admin")
public class AdminController {

//	private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private AdminDao adminDao;

	@Autowired
	private EventWSService eventWSService; // Spring webSocket service

	/**
	 * {@code /allUsers} page
	 */
	@RequestMapping(value = "/allUsers", method = RequestMethod.GET)
	public String getPage1(ModelMap model, Principal principal) {
		String usernameAdm = principal.getName();
		model.addAttribute("usernameAdm", usernameAdm);
		return "/allUsers";
	}

	/**
	 * {@code /allAdmins} page
	 */
	@RequestMapping(value = "/allAdmins", method = RequestMethod.GET)
	public String getPage2(ModelMap model, Principal principal) {
		String usernameAdm = principal.getName();
		model.addAttribute("usernameAdm", usernameAdm);
		return "/allAdmins";
	}

	/**
	 * Actions 'save/update' on pages allAdmins and allUsers
	 */
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getSaved(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "firstName", required = true) String firstName,
			@RequestParam(value = "lastName", required = true) String lastName,
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "telephone", required = true) String telephone,
			@RequestParam(value = "nname", required = false) String name,
			@RequestParam(value = "actn", required = true) String actn) {
		Map<String, Object> map = new HashMap<String, Object>();
		User u = adminDao.getUser(username);
		if (u == null) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(password);
			User un = new User(username, hashedPassword, firstName, lastName, email, telephone);
			adminDao.addUser(un);
			adminDao.giveRoleToUser(username, name);
			map.put("message", "Succes.\n'" + username + "' is added.");
		} else {
			if (actn.equals("edit")) {
				User user = new User(username, firstName, lastName, email, telephone);
				adminDao.updateUser(user);
//				LOG.info("Updated User : {}", user);
				map.put("message", "'" + username + "' is updated successfully.");
			} else {
				map.put("message", "Addition is failed!\nUsername already exists.");
			}
		}
		return map;
	}

	/**
	 * Action 'Delete' on pages allAdmins and allUsers
	 */
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteUser(
			@RequestParam(value = "username", required = true) String username) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (adminDao.hasGotRentedFilms(username)) {
			map.put("message", "Warning!\nUser has rented films.\nDelete action is canceled...");
		} else {
			adminDao.deleteUser(username);
			map.put("message", "User '" + username + "' successfully deleted.");
		}
		return map;
	}

	/**
	 * Creates users list on page {@code /allUsers}
	 */
	@RequestMapping(value = "/listUsers", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getAll(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<User> userslist = adminDao.usersList();
		if (userslist != null) {
			List<UserData> dataList = new ArrayList<>();
			for (User user : userslist) {
				dataList.add(new UserData(user));
			}
			map.put("message", "Data found");
			map.put("data", dataList);
		} else {
			map.put("message", "Data not found");
		}
		return map;
	}

	/**
	 * Creates admins list on page {@code /allAdmins}
	 */
	@RequestMapping(value = "/listAdmins", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getAlla() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<User> adminlist = adminDao.adminList();
		if (adminlist != null) {
			List<UserData> dataList = new ArrayList<>();
			for (User user : adminlist) {
				dataList.add(new UserData(user));
			}
			map.put("message", "Data found");
			map.put("data", dataList);
		} else {
			map.put("message", "Data not found");
		}
		return map;
	}

	/**
	 * {@code /allFilms} page
	 */
	@RequestMapping(value = "/allFilms", method = RequestMethod.GET)
	public String getPage4(ModelMap model, Principal principal) {
		String usernameAdm = principal.getName();
		model.addAttribute("usernameAdm", usernameAdm);
		return "/allFilms";
	}

	/**
	 * Actions 'Save/Update' on page {@code /allFilms}
	 */
	@RequestMapping(value = "/saveOrUpdateFilm", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getSavedFilm(@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "genre", required = true) String genre,
			@RequestParam(value = "year", required = true) Integer year,
			@RequestParam(value = "actn", required = true) String actn) {
		Map<String, Object> map = new HashMap<String, Object>();
		Film f = adminDao.getFilm(title);
		Film flm = new Film(title, genre, year);
		if (f == null) {
			adminDao.addFilm(flm);
			map.put("message", "Succes.\nAdded new film '" + title + "'.");
		} else {
			if (actn.equals("edit")) {
				adminDao.updateFilm(flm);
//				LOG.info("Updated Film : {}", flm);
				map.put("message", "Film '" + title + "' successfully updated.");
			} else {
				map.put("message", "Failed to create film!\nTitle already exists.");
			}
		}
		return map;
	}
	
	/**
	 * Action 'Delete' on page {@code /allFilms}
	 */
	@RequestMapping(value = "/deleteFilm", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteFilm(@RequestParam(value = "title", required = true) String title) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (adminDao.isRented(title)) {
			map.put("message", "Warning!\nFilm is rented.\nDelete action is canceled...");
		} else {
			adminDao.deleteFilm(title);
			map.put("message", "Film '" + title + "' is successfully deleted.");
		}
		return map;
	}
	
	/**
	 * Creates list of films on page {@code allFilms}
	 */
	@RequestMapping(value = "/listFilms", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getAllFilms() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Film> filmsLlist = adminDao.listOfFilms();
		if (filmsLlist != null) {
			List<FilmData> dataList = new ArrayList<>();
			for (Film film : filmsLlist) {
				dataList.add(new FilmData(film));
			}
			map.put("message", "Data found");
			map.put("data", dataList);
		} else {
			map.put("message", "Data not found");
		}
		return map;
	}

	/**
	 * Goes to {@code /rentNewFilm} page with list of users that want to rent some film(s). 
	 */
	@RequestMapping(value = "rentNewFilm", method = RequestMethod.GET)
	public String viewUsers(ModelMap model, Principal principal) {
		String usernameAdm = principal.getName();
		model.addAttribute("usernameAdm", usernameAdm);
		model.addAttribute("usersList2", adminDao.usersList());
		return "rentNewFilm";
	}

	/**
	 * Passing user data to {@code /availableFilms} page.
	 */
	@RequestMapping(value = "availableFilms/{username}", method = RequestMethod.GET)
	public String rentNewFilm(@PathVariable(value = "username") String username, ModelMap model, Principal principal) {
		User user = adminDao.getUser(username);
		String usernameAdm = principal.getName();
		model.addAttribute("usernameAdm", usernameAdm);
		model.addAttribute("user3", user);
		model.addAttribute("availableFilmsList", adminDao.availableFilms());
		return "availableFilms";
	}

	/**
	 * Performs renting of a film <strong>title</strong> to user <strong>username</strong> on
	 * the {@code /availableFilms} page.
	 */
	@RequestMapping(value = "rentingFilm/{title}/{username}", method = RequestMethod.GET)
	public String selectFilm(@PathVariable(value = "title") String title,
			@PathVariable(value = "username") String username, ModelMap model) {
		User u = adminDao.getUser(username);
		Film f = adminDao.getFilm(title);
		String message = "";
		int rflm = 0;
		if (f != null && u != null) {
			boolean rented = adminDao.isRented(title);
			boolean have5 = adminDao.hasAlready5(username);
			if (rented) {
				rflm = 1;
				message = "Film '" + title + "' has already been rented";
			} else if (have5) {
				rflm = 2;
				message = "User '" + username + "' has 5 rented films already!";
			} else if (!have5 && !rented) {
				rflm = 3;
				adminDao.rentFilm(title, username);
				EvtWSMessage evtMssg = new EvtWSMessage(f.getId(), f.getTitle(), f.getGenre(), f.getYear(),
						u.getUsername(), ActionType.RENTED);
				eventWSService.sendEventWS(evtMssg);
				message = "User '" + u.getUsername() + "' has rented film '" + f.getTitle() + "'.";
			}
		} else {
			message = "Some error, try another film!";
		}
		model.addAttribute("message", message);
		model.addAttribute("rflm", rflm);		
		return "redirect:/admin/availableFilms/{username}";
	}

	/**
	 * {@code /rentedFilms} page with data related to all rented films.
	 */
	@RequestMapping(value = "rentedFilms", method = RequestMethod.GET)
	public String viewRentedFilms(ModelMap model, Principal principal) {
		String usernameAdm = principal.getName();
		model.addAttribute("usernameAdm", usernameAdm);
		model.addAttribute("currTime", FilmTimeUtil.currentTime());
		model.addAttribute("rentedFilmsList", adminDao.rentedFilms());
		return "rentedFilms";
	}
	
	/**
	 * Return to the {@code /rentedFilms} page after film <strong>title</strong> has been
	 * un-rented (returned) by the user.
	 */
	@RequestMapping(value = "getBackFilm/{title}", method = RequestMethod.GET)
	public String returnFilm(@PathVariable(value = "title") String title, ModelMap model) {
		String message = "";
		Film f = adminDao.getFilm(title);
		User u = adminDao.returnFilm(title);
		EvtWSMessage evtMssg = new EvtWSMessage(f.getId(), f.getTitle(), f.getGenre(), f.getYear(), u.getUsername(),
				ActionType.RETURNED);
		eventWSService.sendEventWS(evtMssg);
		message = "User '" + u.getUsername() + "' has returned film '" + f.getTitle() + "'.";
		model.addAttribute("message", message);
		return "redirect:/admin/rentedFilms";
	}

	/**
	 * Page with list of users with rented films.
	 */
	@RequestMapping(value = "usersWithFilms", method = RequestMethod.GET)
	public String viewUsersWithFilms(ModelMap model, Principal principal) {
		String usernameAdm = principal.getName();
		model.addAttribute("usernameAdm", usernameAdm);
		model.addAttribute("usersWithFilms", adminDao.userListWithRent());
		return "usersWithFilms";
	}

	/**
	 * Page with list of users without any rented films.
	 */
	@RequestMapping(value = "usersWithoutFilms", method = RequestMethod.GET)
	public String viewUsersWithoutFilms(ModelMap model, Principal principal) {
		String usernameAdm = principal.getName();
		model.addAttribute("usernameAdm", usernameAdm);
		model.addAttribute("usersWithoutFilms", adminDao.userListNoRent());
		return "usersWithoutFilms";
	}
	
	/**
	 * Statistics page.
	 */
	@RequestMapping(value = "statistics", method = RequestMethod.GET)
	public String viewStatistics(ModelMap model, Principal principal) {
		String usernameAdm = principal.getName();
		model.addAttribute("usernameAdm", usernameAdm);
		model.addAttribute("usersList", adminDao.usersList());
		model.addAttribute("nrUsers", adminDao.totalNoOfUsers());
		model.addAttribute("statNrFilmPerUser", adminDao.statsAvgFilmsPerUser());
		model.addAttribute("filmsList", adminDao.listOfFilms());
		model.addAttribute("nrFilms", adminDao.filmsCount());
		model.addAttribute("statNrRentPerFilm", adminDao.avgRentsPerFilm());
		return "statistics";
	}

}
