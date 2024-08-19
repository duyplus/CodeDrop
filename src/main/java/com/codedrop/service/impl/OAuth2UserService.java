package com.codedrop.service.impl;

import com.codedrop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        // Delegate to the default implementation for loading the user
        OAuth2User oAuth2User = super.loadUser(userRequest);
        // Extract the necessary user information
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email");
        String username = (String) attributes.get("preferred_username");
        String fullname = (String) attributes.get("name");
        String photo = (String) attributes.get("picture");
        // Perform custom user logic, like saving or updating user in the database
        userService.saveOrUpdateOAuthUser(email, username, fullname, photo);
        // Create a new OAuth2User with the needed authorities
        // "sub" is typically the identifier key, change as needed
        return new DefaultOAuth2User(oAuth2User.getAuthorities(), attributes, "sub");
    }
}
