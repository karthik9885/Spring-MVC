package com.practise.tasks.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.practise.tasks.entity.Tasks;
import com.practise.tasks.entity.User;
import com.practise.tasks.exception.UserException;
import com.practise.tasks.repository.TasksRepository;
import com.practise.tasks.repository.UserRepository;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TasksController {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	private TasksRepository tasksRepository;
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/signin")
	public String signInPage() {
		return "signInPage";
	}
	
	@PostMapping("/signin")
	public String signInUser(@RequestParam String userName, @RequestParam String password, ModelMap model) throws UserException {
		User userDetails = new User();
		User userExists = userRepository.findByUserName(userName);
		if(userExists != null) {
				model.put("errorMessage", "existing username");
				return "signInPage";
		}		
		userDetails.setUserName(userName);
		userDetails.setPassword(passwordEncoder.encode(password));
		userRepository.save(userDetails);
		return "redirect:/login";
	}
	
	@GetMapping({"/","/login"})
	public String loginPage() {
		return "login";
	}

	@PostMapping("/login")
	public String welcomePage(@RequestParam String userName, @RequestParam String password, ModelMap model) {
				
		User userDetails = userRepository.findByUserName(userName);
		  if (userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())) {
		        model.put("errorMessage", "Invalid username or password");
		        return "login"; 
		    }
	        model.put("name", userName);
	        return "redirect:/welcome";
	}

	@GetMapping("/welcome")
	public String welcomePage() {
		return "welcome";
	}
	
	@GetMapping("/list-todos")
	public String listAlltodos(@SessionAttribute("name") String userName, ModelMap model){
		List<Tasks> tasks = tasksRepository.findByUserName(userName);
		model.addAttribute("tasks",tasks);
		return "listTodos";
	}
	
	@GetMapping("/add-todo")
	public String showNewTaskspage(@SessionAttribute("name") String userName, ModelMap model) {
		Tasks task = new Tasks(0, userName, "", LocalDate.now().plusYears(1), false);
		model.put("task", task);
		return "task";
	}
	
	@PostMapping("/add-todo")
	public String addNewTask(@SessionAttribute("name") String userName, ModelMap model,@Valid Tasks tasks,BindingResult result) {
		if(result.hasErrors()) {
			return "todo";
		}
		tasks.setUserName(userName);
		tasksRepository.save(tasks);
		return "redirect:list-todos";
	}
	
	@DeleteMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {
		tasksRepository.deleteById(id);
		return "redirect:list-todos";
	}
	
	@GetMapping("/update-todo")
	public String showUpdateTodoPage(ModelMap model,int id) {
		Tasks task = tasksRepository.findById(id).get();
		model.addAttribute("task", task);
		return "task";
	}
	
	@PutMapping("/update-todo")
	public String updateTodo(@SessionAttribute("name") String userName, ModelMap model,@Valid Tasks tasks,BindingResult result) {
		if(result.hasErrors()) {
			return "task";
		}
		tasks.setUserName(userName);
		tasksRepository.save(tasks);
		return "redirect:list-todos";
	}
	
//	private String getLoggedInUserName() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	    if (authentication == null) {
//	        return null;
//	    }
//	    return authentication.getName();
//	}
	
}
