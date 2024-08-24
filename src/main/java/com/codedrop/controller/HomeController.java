package com.codedrop.controller;

import com.codedrop.dto.UserDto;
import com.codedrop.exception.ResourceNotFoundException;
import com.codedrop.model.User;
import com.codedrop.payload.JwtResponse;
import com.codedrop.payload.JwtTokenUtil;
import com.codedrop.service.AuthService;
import com.codedrop.service.impl.UserDetailsImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@CrossOrigin("*")
@RestController
public class HomeController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsImpl userDetailsService;

    @Autowired
    AuthService authService;

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "<!DOCTYPE html>" +
        "<html lang='en'>" +
        "<head>" +
            "<meta charset='UTF-8'>" +
            "<meta name='viewport' content='width=device-width, initial-scale=1'>" +
            "<meta http-equiv='X-UA-Compatible' content='ie=edge'>" +
            "<title>SRTeam</title>" +
            "<link rel='preconnect' href='https://fonts.googleapis.com'>" +
            "<link rel='preconnect' href='https://fonts.gstatic.com' crossorigin>" +
            "<link href='https://fonts.googleapis.com/css2?family=Roboto+Mono&display=swap' rel='stylesheet'>"+
            "<style>" +
                "html,body{font-family:'Roboto Mono',monospace}" +
                ".wrapper{height:98vh;display:grid;place-items:center}" +
                ".footer{position:fixed;font-weight:600;bottom:10px}" +
                ".typing{font-size:2em;font-weight:600;width:16ch;white-space:nowrap;overflow:hidden;border-right:3px solid;animation:typing 1s steps(22),blink .5s step-end infinite alternate}" +
                "@keyframes typing{from{width:0}}@keyframes blink{50%{border-color:transparent}}}" +
            "</style>" +
        "</head>" +
        "<body>" +
            "<div class='wrapper'>" +
                "<div class='typing'>Owned by SRTeam.</div>" +
                "<div class='footer'>&copy " + LocalDate.now().getYear() + "</div>" +
            "</div>" +
        "</body>" +
        "</html>";
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping("auth/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) throws Exception {
        authenticate(userDto.getUsername(), userDto.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("auth/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(userDto));
    }

    @PostMapping("auth/forgot-password")
    public ResponseEntity<Void> forgotPassword(@RequestBody String email) {
        if (authService.sendResetMail(email)) {
            return ResponseEntity.ok().build();
        }
        throw new ResourceNotFoundException("User not exists with email: " + email);
    }

    @SneakyThrows
    @GetMapping("auth/reset-password")
    public ResponseEntity<User> resetPassword(@RequestParam Optional<String> token) {
        ResourceNotFoundException exception = new ResourceNotFoundException("Cannot find token: " + token);
        String tokenVal = token.orElseThrow(() -> exception);
        User user = authService.findByToken(tokenVal);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        throw exception;
    }

    @PostMapping("auth/change-password")
    public ResponseEntity<Void> changePassword(@RequestBody User user) throws Exception {
        authenticate(user.getUsername(), user.getPassword());
        return authService.changePassword(user) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
