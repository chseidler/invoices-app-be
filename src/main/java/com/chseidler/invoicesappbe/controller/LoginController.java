package com.chseidler.invoicesappbe.controller;

import com.chseidler.invoicesappbe.dto.UserDTO;
import com.chseidler.invoicesappbe.exception.UserServiceException;
import com.chseidler.invoicesappbe.model.request.UserLoginRequestModel;
import com.chseidler.invoicesappbe.model.ui.LoginRest;
import com.chseidler.invoicesappbe.service.UserService;
import com.chseidler.invoicesappbe.utils.ErrorMessages;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping
    public LoginRest loginUser(@RequestBody UserLoginRequestModel loginRequestModel) {

        LoginRest loginRest = new LoginRest();
        UserDTO userDTO = userService.getUser(loginRequestModel.getNickname());

        boolean isAuthenticated = BCrypt.checkpw(loginRequestModel.getPassword(), userDTO.getEncryptedPassword());

        if (isAuthenticated) {
            BeanUtils.copyProperties(userDTO, loginRest);
        } else {
            throw new UserServiceException(ErrorMessages.CAN_NOT_LOGIN.getErrorMessage());
        }

        return loginRest;
    }
}
