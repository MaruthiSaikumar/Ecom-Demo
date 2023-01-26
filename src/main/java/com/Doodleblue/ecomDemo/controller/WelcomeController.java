package com.Doodleblue.ecomDemo.controller;

import com.Doodleblue.ecomDemo.entity.AuthRequest;
import com.Doodleblue.ecomDemo.entity.JwtToken;
import com.Doodleblue.ecomDemo.entity.User;
import com.Doodleblue.ecomDemo.repository.UserRepository;
import com.Doodleblue.ecomDemo.service.UserService;
import com.Doodleblue.ecomDemo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class WelcomeController {
    @Autowired
    UserService userService;
    @Autowired
    private UserRepository repository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to javatechie !!";
    }

    @ResponseBody
    @PostMapping("/cereteUser")
    public  User createUser(@RequestBody User user) throws  Exception{

        try {
            return  userService.registerUser(user);

        }
        catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }


    @ResponseBody
    @PostMapping("/authenticate")
    public JwtToken generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password" + ex.getMessage());
        }
        //return  jwtUtil.generateToken(authRequest.getEmail());
        return new JwtToken(jwtUtil.generateToken(authRequest.getEmail()));
    }




}
