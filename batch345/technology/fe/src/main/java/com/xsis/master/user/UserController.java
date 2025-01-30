package com.xsis.master.user;

import com.xsis.authentication.AuthenticationRoles;
import com.xsis.util.ProcessAPI;
import com.xsis.util.RequestType;
import com.xsis.util.error.ErrorMessage;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final RestTemplate restTemplate;
    private final ProcessAPI<UserDTO, UserModel> request;
    private final String API_URL;

    public UserController(ProcessAPI<UserDTO, UserModel> request, Environment env) {
        this.restTemplate = new RestTemplate();
        this.request = request;
        this.API_URL = env.getProperty("api.url") + "/user";
    }

    @GetMapping("/login")
    public ModelAndView login() {
        var view = new ModelAndView("auth/login");

        view.addObject("title", "Login Page");

        return view;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession httpSession) {
        httpSession.invalidate();
        SecurityContextHolder.clearContext();

        return new ModelAndView("redirect:/");
    }

    @GetMapping("/add")
    public ModelAndView add() {
        var view = new ModelAndView("master/user/add");

        view.addObject("title", "Create Customer");
        view.addObject("button", "Save");

        return view;
    }

    @GetMapping("/register")
    public ModelAndView register() {
        var view = new ModelAndView("master/user/add");

        view.addObject("title", "Register User");
        view.addObject("button", "Register");

        return view;
    }

    @GetMapping("/{type}/{id}")
    public ModelAndView detail(@PathVariable("type") RequestType type, @PathVariable("id") int id) {
        ModelAndView view = getViewModel(type);

        ResponseEntity<UserModel> apiResponse;

        var header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        var httpEntity = new HttpEntity<>(header);

        try {
            apiResponse = restTemplate.exchange(API_URL + "/" + id,
                    HttpMethod.GET, httpEntity, UserModel.class);

            if (apiResponse.getStatusCode() == HttpStatus.OK)
                view.addObject("customer", apiResponse.getBody());

        } catch (HttpClientErrorException e) {
            ErrorMessage er = e.getResponseBodyAs(ErrorMessage.class);

            log.error(Objects.requireNonNull(er).toString());
            view.addObject("error", er);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

        return view;
    }


    // ==================== REST ====================

    @GetMapping
    public ModelAndView customers() {
        var view = new ModelAndView("master/user/index");
        ResponseEntity<List<UserModel>> apiResponse;

        try {
            apiResponse = restTemplate.exchange(API_URL, HttpMethod.GET,
                    new HttpEntity<>(new ArrayList<UserModel>()),
                    new ParameterizedTypeReference<>() {
                    });

            if (apiResponse.getStatusCode().is2xxSuccessful()) {
                view.addObject("customers", apiResponse.getBody());
                return view;
            }

            view.addObject("error", apiResponse.getBody());

        } catch (Exception e) {
            view.addObject("error", e.getMessage());
        }

        return view;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCustomer(@ModelAttribute UserDTO userDTO) {
        log.info(userDTO.toString());

        return request.send(userDTO, UserModel.class,
                HttpMethod.POST, HttpStatus.CREATED,
                API_URL);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCustomer(@ModelAttribute UserDTO userDTO) {
        return request.send(userDTO, UserModel.class,
                HttpMethod.PUT, HttpStatus.OK,
                API_URL);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCustomer(@ModelAttribute UserDTO userDTO) {
        return request.send(userDTO, UserModel.class,
                HttpMethod.DELETE, HttpStatus.OK,
                API_URL);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@ModelAttribute UserDTO model, HttpSession httpSession) {
        ResponseEntity<?> resp = request.send(model, UserModel.class,
                HttpMethod.POST, HttpStatus.OK,
                API_URL + "/login");

        if (resp.getStatusCode().is2xxSuccessful()) {
            UserModel userData = (UserModel) Objects.requireNonNull(resp.getBody());

            AuthenticationRoles role = switch (userData.getRoleId()) {
                case 1 -> AuthenticationRoles.ROLE_ADMIN;
                case 2 -> AuthenticationRoles.ROLE_USER;
                default -> AuthenticationRoles.ROLE_GOBLIN;
            };

            httpSession.setAttribute("ROLE", role);
            httpSession.setAttribute("user", userData);

            log.info("Login as {}", role);
            return new ResponseEntity<>(userData, HttpStatus.OK);
        }

        // Any kind of error WILL be handled
        // by ControllerAdvice (ErrorsHandler class)
        return null;
    }

    private ModelAndView getViewModel(RequestType type) {
        Map<String, Object> attributes = new HashMap<>();

        switch (type) {
            case DETAIL -> {
                attributes.put("title", "Customer Details");
                return new ModelAndView("master/user/detail", attributes);
            }
            case EDIT -> {
                attributes.put("title", "Edit Customer");
                return new ModelAndView("master/user/edit", attributes);
            }
            case DELETE -> {
                attributes.put("title", "Delete Customer");
                return new ModelAndView("master/user/delete", attributes);
            }
            default -> throw new UnsupportedOperationException();
        }
    }

}
