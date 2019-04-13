package com.snezana.videoclub.controller;

import java.security.Principal;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.snezana.videoclub.dao.UserDao;
import com.snezana.videoclub.model.EvtWSMessage;
import com.snezana.videoclub.model.EvtWSMessage.ActionType;
import com.snezana.videoclub.model.Film;
import com.snezana.videoclub.model.User;
import com.snezana.videoclub.service.EventWSService;
import com.snezana.videoclub.service.LoginService;

/**
 * Controller class for user activities.
 */
@Controller
@RequestMapping("home")
public class UserController {

//	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private LoginService loginService;

	@Autowired
	private EventWSService eventWSService;

	/**
	 * Shows rented films per specific user.
	 */
	@RequestMapping(value = "/rentedFilmsPerUser", method = RequestMethod.GET)
	public String viewRentedFilmsPerUser(ModelMap model, Principal principal) {
		String username = principal.getName();
		model.addAttribute("username", username);
		model.addAttribute("rentedFilmsPerUser", userDao.filmsRentedByUser(username));
		int i = userDao.filmsRentedByUser(username).size();
		model.addAttribute("nrFlmUser", i);
		return "rentedFilmsPerUser";
	}

	/**
	 * List of all currently available films that user can rent
	 */
	@RequestMapping(value = "/userRentNewFilm", method = RequestMethod.GET)
	public String userViewFreeFilms(ModelMap model, Principal principal) {
		String username = principal.getName();
		model.addAttribute("username", username);
		model.addAttribute("availableFilmsList", userDao.availableFilms());
		return "userRentNewFilm";
	}

	/**
	 * Action rent(film) by user
	 */
	@RequestMapping(value = "/userSelReq/{title}", method = RequestMethod.GET)
	public String userRentNewFilm(@PathVariable(value = "title") String title, ModelMap model, Principal principal) {
		String username = principal.getName();
		Film f = userDao.getFilm(title);
		String message = "";
		int rflm = 0;
		if (f != null) {
			boolean rented = userDao.isRented(title);
			boolean has5 = userDao.hasAlready5(username);
			if (rented) {
				rflm = 1;
				message = "Film '" + title + "' has already been rented";
			} else if (has5) {
				rflm = 2;
				message = "User '" + username + "' has 5 rented films already!";
			} else if (!has5 && !rented) {
				rflm = 3;
				User u = userDao.getUser(username);
				userDao.rentFilm(title, username);
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
		return "redirect:/home/userRentNewFilm";
	}

	/**
	 * Change user password page
	 */
	@RequestMapping(value = "/changeUserPassw", method = RequestMethod.GET)
	public String changePassword(ModelMap model, Principal principal) {
		String usernamep = principal.getName();
		model.addAttribute("usernamep", usernamep);
		return "/changeUserPassw";
	}

	/**
	 * Action for checking old password and changing it by new one
	 */
	@RequestMapping(value = "/changeUserPassw", method = RequestMethod.POST)
	public String changePassword2(ModelMap model,
			@RequestParam(value = "oldpassword", required = true) String oldpassword,
			@RequestParam(value = "newpassword", required = true) String newpassword,
			@RequestParam(value = "confirmpassword", required = true) String confirmpassword, Principal principal) {
		String usernamep = principal.getName();
		User user = userDao.getUser(usernamep);
		if (!loginService.checkIfValidOldPassword(user, oldpassword)) {
			return "redirect:/home/changeUserPassw?err";
		}
		loginService.changeUserPassword(user, newpassword);
		userDao.updateUserPassword(user);
//		LOG.info("oldpassword= " + oldpassword);
//		LOG.info("newpassword= " + newpassword);
		return "redirect:/home/changeUserPassw?succ";
	}

}
