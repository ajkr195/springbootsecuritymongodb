package com.spring.boot.rocks.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.boot.rocks.model.AppRole;
import com.spring.boot.rocks.model.AppUser;
import com.spring.boot.rocks.repository.AppRoleRepository;
import com.spring.boot.rocks.service.AppUserService;
import com.spring.boot.rocks.validator.AppUserAddValidator;
import com.spring.boot.rocks.validator.AppUserEditValidator;

@Controller
@RequestMapping("/")
//@SessionAttributes({ "roles", "programareas" })
public class AppUserController {
	@Autowired
	private AppUserService appUserService;

	@Autowired
	private AppRoleRepository appRoleRepository;

	@Autowired
	private AppUserAddValidator userAddValidator;

	@Autowired
	private AppUserEditValidator userEditValidator;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String root(Model model) {
//		return "redirect:userlist";
		return "home";
	}
	
	
	@RequestMapping(value = { "home" }, method = RequestMethod.GET)
	public String home(Model model) {
		return "home";
	}

	@RequestMapping(value = { "userlist" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {
		List<AppUser> users = appUserService.findAllUsers();
		model.addAttribute("users", users);
		return "userlist";
	}
	


	@RequestMapping(value = "registration", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("addnewuser", true);
		model.addAttribute("userForm", new AppUser());
		return "userregistration";
	}

	@RequestMapping(value = "registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") AppUser userForm, BindingResult bindingResult, Model model) {
		model.addAttribute("addnewuser", true);
		userAddValidator.validate(userForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return "userregistration";
		}
		appUserService.save(userForm);
		model.addAttribute("addusersuccess", true);
//		model.addAttribute("success", "User created successfully:<br><hr/>" + "ID: "+ userForm.getId() + "<br>ID: "+userForm.getUsername() + "<br>ID: "+userForm.getUseremail()
//				+ "<br>ID: "+userForm.getUserfirstname() + "<br>ID: "+userForm.getUserlastname());
		model.addAttribute("addsuccess", "<div class=\"jumbotron jumbo\">\n" +
//				"ID: " + userForm.getId() + "<br>Username: "
//				+ userForm.getUsername() + "<br>EmailID: " + userForm.getUseremail() + "<br>FirstName: "
//				+ userForm.getUserfirstname() + "<br>LastName: " + userForm.getUserlastname() + "<br>Address: "
//				+ userForm.getUseraddress() + "<br>Roles: " + userForm.getRoles()
//				
//				+
				"<div class=\"row\">\n" + "<label for=\"userfirstname\""
				+ "class=\"font-weight-bold col-sm-4 col-form-label text-right\">" + "	ID: </label>\n"
				+ "<p class=\"p-label col-sm-8 pull-left\" id=\"id\">" + userForm.getId() + "</p>\n"
				+ "						</div>"

				+ "<div class=\"row\">\n" + "<label for=\"username\""
				+ "class=\"font-weight-bold col-sm-4 col-form-label text-right\">" + "Username :</label>\n"
				+ "<p class=\"p-label col-sm-8 pull-left\" id=\"username\">" + userForm.getUsername() + "</p>\n"
				+ "						</div>"

				+ "<div class=\"row\">\n" + "<label for=\"useremail\""
				+ "class=\"font-weight-bold col-sm-4 col-form-label text-right\">\n" + "EmailID :</label>\n"
				+ "<p class=\"p-label col-sm-8 pull-left\" id=\"useremail\">" + userForm.getUseremail() + "</p>\n"
				+ "						</div>"

				+ "<div class=\"row\">\n" + "<label for=\"userfirstname\""
				+ "class=\"font-weight-bold col-sm-4 col-form-label text-right\">\n" + "FirstName :</label>\n"
				+ "<p class=\"p-label col-sm-8 pull-left\" id=\"userfirstname\">" + userForm.getUserfirstname()
				+ "</p>\n" + "						</div>"

				+ "<div class=\"row\">\n" + "<label for=\"userlastname\""
				+ "class=\"font-weight-bold col-sm-4 col-form-label text-right\">\n" + "LastName :</label>\n"
				+ "<p class=\"p-label col-sm-8 pull-left\" id=\"userlastname\">" + userForm.getUserlastname() + "</p>\n"
				+ "						</div>"

				+ "<div class=\"row\">\n" + "<label for=\"useraddress\""
				+ "class=\"font-weight-bold col-sm-4 col-form-label text-right\">\n" + "Address :</label>\n"
				+ "<p class=\"p-label col-sm-8 pull-left\" id=\"useraddress\">" + userForm.getUseraddress() + "</p>\n"
				+ "						</div>"

				+ "<div class=\"row\">\n" + "<label for=\"roles\""
				+ "class=\"font-weight-bold col-sm-4 col-form-label text-right\">\n" + "Roles :</label>\n"
				+ "<p class=\"p-label col-sm-8 pull-left\" id=\"roles\">"
				+ userForm.getRoles().toString().replace("AppRole [id=", "").replace("1, name=", "")
						.replace("2, name=", "").replace("3, name=", "").replace("[", "").replace("]", "")
						.replace("]]", "")
				+ "</p>\n" + "						</div>"

				+ "</p>\n</div><hr/>");
		return "success";

	}

	@RequestMapping(value = { "edit-user-{username}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String username, Model model) {
		AppUser user = appUserService.findByUsername(username);
		model.addAttribute("userForm", user);
		model.addAttribute("edit", true);
		model.addAttribute("editexistinguser", true);
		return "userregistration";
	}

	@RequestMapping(value = { "edit-user-{username}" }, method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("userForm") @Valid AppUser userForm, BindingResult bindingResult,
			Model model, @PathVariable String username) {
		model.addAttribute("editexistinguser", true);
		userEditValidator.validate(userForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return "userregistration";
		}

		appUserService.updateUser(userForm);
		model.addAttribute("editusersuccess", true);
		model.addAttribute("editsuccess", "<div class=\"jumbotron jumbo\">\n" +
//				"ID: " + userForm.getId() + "<br>Username: "
//				+ userForm.getUsername() + "<br>EmailID: " + userForm.getUseremail() + "<br>FirstName: "
//				+ userForm.getUserfirstname() + "<br>LastName: " + userForm.getUserlastname() + "<br>Address: "
//				+ userForm.getUseraddress() + "<br>Roles: " + userForm.getRoles()
//				
//				+
				"<div class=\"row\">\n" + "<label for=\"userfirstname\""
				+ "class=\"font-weight-bold col-sm-4 col-form-label text-right\">" + "	ID: </label>\n"
				+ "<p class=\"p-label col-sm-8 pull-left\" id=\"id\">" + userForm.getId() + "</p>\n"
				+ "						</div>"

				+ "<div class=\"row\">\n" + "<label for=\"username\""
				+ "class=\"font-weight-bold col-sm-4 col-form-label text-right\">" + "Username :</label>\n"
				+ "<p class=\"p-label col-sm-8 pull-left\" id=\"username\">" + userForm.getUsername() + "</p>\n"
				+ "						</div>"

				+ "<div class=\"row\">\n" + "<label for=\"useremail\""
				+ "class=\"font-weight-bold col-sm-4 col-form-label text-right\">\n" + "EmailID :</label>\n"
				+ "<p class=\"p-label col-sm-8 pull-left\" id=\"useremail\">" + userForm.getUseremail() + "</p>\n"
				+ "						</div>"

				+ "<div class=\"row\">\n" + "<label for=\"userfirstname\""
				+ "class=\"font-weight-bold col-sm-4 col-form-label text-right\">\n" + "FirstName :</label>\n"
				+ "<p class=\"p-label col-sm-8 pull-left\" id=\"userfirstname\">" + userForm.getUserfirstname()
				+ "</p>\n" + "						</div>"

				+ "<div class=\"row\">\n" + "<label for=\"userlastname\""
				+ "class=\"font-weight-bold col-sm-4 col-form-label text-right\">\n" + "LastName :</label>\n"
				+ "<p class=\"p-label col-sm-8 pull-left\" id=\"userlastname\">" + userForm.getUserlastname() + "</p>\n"
				+ "						</div>"

				+ "<div class=\"row\">\n" + "<label for=\"useraddress\""
				+ "class=\"font-weight-bold col-sm-4 col-form-label text-right\">\n" + "Address :</label>\n"
				+ "<p class=\"p-label col-sm-8 pull-left\" id=\"useraddress\">" + userForm.getUseraddress() + "</p>\n"
				+ "						</div>"

				+ "<div class=\"row\">\n" + "<label for=\"roles\""
				+ "class=\"font-weight-bold col-sm-4 col-form-label text-right\">\n" + "Roles :</label>\n"
				+ "<p class=\"p-label col-sm-8 pull-left\" id=\"roles\">"
				+ userForm.getRoles().toString().replace("AppRole [id=", "").replace("1, name=", "")
						.replace("2, name=", "").replace("3, name=", "").replace("[", "").replace("]", "")
						.replace("]]", "")+ "</p>\n" + "						</div>"

				+ "</p>\n</div><hr/>");
		return "success";
	}

	@RequestMapping(value = { "view-user-{username}" }, method = RequestMethod.GET)
	public String viewUser(@PathVariable String username, Model model) {
		AppUser user = appUserService.findByUsername(username);
		model.addAttribute("userForm", user);
		return "userview";
	}

	@RequestMapping(value = { "delete-user-{username}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String username) {
		appUserService.deleteUserByUsername(username);
		return "redirect:userlist";
	}

	@ModelAttribute("roles")
	public List<AppRole> initializeRoles() {
		return (List<AppRole>) appRoleRepository.findAll();
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "login";
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);

			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:login?logout";
	}

	@RequestMapping(value = "Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "useraccessDenied";
	}



	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	public String getTimeStamp() {
		TimeZone mytimeZone = TimeZone.getTimeZone("EST");
		Calendar calendar = Calendar.getInstance(mytimeZone);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EE MMM dd HH:mm:ss zzz yyyy", Locale.US);
		simpleDateFormat.setTimeZone(mytimeZone);
		String setTimeStamp = simpleDateFormat.format(calendar.getTime());
		return setTimeStamp;
	}

}
