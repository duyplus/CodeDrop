package com.codedrop.service.impl;

import com.codedrop.common.Link;
import com.codedrop.dto.MailDto;
import com.codedrop.model.User;
import com.codedrop.service.AuthService;
import com.codedrop.service.MailerService;
import com.codedrop.service.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserService userService;

    @Autowired
    MailerService mailerService;

    @Autowired
    private PasswordEncoder pe;

    @Override
    public User findByToken(String token) {
        User user = userService.findByToken(token);
        return user != null ? User.builder().id(user.getId()).token(user.getToken()).build() : null;
    }

    @Override
    public boolean changePassword(User user) {
        User userDb = userService.findById(user.getId());
        userDb.setPassword(pe.encode(user.getPassword()));
        userDb.setToken(null);
        User updateUser = userService.update(userDb);
        return updateUser != null;
    }

    @Override
    public boolean sendResetMail(String email) {
        User user = userService.findByEmail(email);
        if (user != null) {
            String token = RandomString.make(30);
            user.setToken(token);
            MailDto mail = getResetMail(email, token);
            mailerService.queue(mail);
            userService.update(user);
            return true;
        }
        return false;
    }

    private MailDto getResetMail(String email, String token) {
        User user = userService.findUsernameByEmail(email);
        String username = user.getUsername();
        String link = Link.ClientLink.RESET_PASSWORD_URL;
        String url = String.format("%s?token=%s", link, token);
        String subject = "Yêu cầu đặt lại mật khẩu";
        String button = "background-color:#783ecf;color:#fff;font-size:15px;padding:12px 10px;text-decoration:none;border-radius:3px;font-weight:bold";
        String body =
                "<div style='width:50%;margin:0 auto'>"
                    + "<div style='background-color:#f0f8ff;font-size:14px;padding:2em 5em'>"
                        + "<div style='display:flex;border:1px;height:1px;background:lightblue'></div>"
                        + "<p>Hi <b>" + username + "</b>,</p>"
                        + "<p>Bạn đã yêu cầu đặt lại mật khẩu được liên kết với " + email + ". Chưa có thay đổi nào đối với tài khoản của bạn.</p>"
                        + "<p>Bạn có thể đặt lại mật khẩu bằng cách nhấp vào liên kết bên dưới:</p>"
                        + "<p style='display:grid;margin:0 auto;text-align:center'><a href=\"" + url + "\" style='" + button + "'>Đặt lại mật khẩu</a></p>"
                        + "<p>Nếu bạn không yêu cầu mật khẩu mới, vui lòng cho chúng tôi biết ngay lập tức bằng cách trả lời email này.</p>"
                        + "<div style='display:flex;border:1px;height:1px;background:lightblue'></div>"
                        + "<div style='margin-top:1em'>"
                            + "Copyright © " + LocalDate.now().getYear() +" <b>SRTeam</b>. All rights reserved."
                        + "</div>"
                    + "</div>"
                + "</div>";
        return new MailDto(email, subject, body);
    }
}
