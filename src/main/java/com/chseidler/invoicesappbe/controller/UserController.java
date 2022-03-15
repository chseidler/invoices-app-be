package com.chseidler.invoicesappbe.controller;

import com.chseidler.invoicesappbe.dto.UserDTO;
import com.chseidler.invoicesappbe.exception.UserServiceException;
import com.chseidler.invoicesappbe.model.request.UserDetailsRequestModel;
import com.chseidler.invoicesappbe.model.ui.UserRest;
import com.chseidler.invoicesappbe.service.UserService;
import com.chseidler.invoicesappbe.utils.ErrorMessages;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {

        if (userDetails.getEmail().isEmpty()) {
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELDS.getErrorMessage());
        }

        if (userDetails.getNickname().isEmpty()) {
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELDS.getErrorMessage());
        }

        if (userDetails.getPassword().isEmpty()) {
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELDS.getErrorMessage());
        }

        if (userDetails.getFullName().isEmpty()) {
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELDS.getErrorMessage());
        }

        UserRest userRest = new UserRest();

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDetails, userDTO);

        UserDTO createdUserDTO = userService.createUser(userDTO);
        BeanUtils.copyProperties(createdUserDTO, userRest);

        return userRest;
    }

    @GetMapping(path = "/{id}")
    public UserRest getUser(@PathVariable("id") String userId) {

        UserRest userRest = new UserRest();
        UserDTO userDTO = userService.getUserByUserId(userId);
        BeanUtils.copyProperties(userDTO, userRest);
        return userRest;
    }

    @GetMapping
    public List<UserRest> getUsers(@RequestParam(value = "page", defaultValue = "0") int page
            , @RequestParam(value = "limit", defaultValue = "100") int limit) {

        List<UserRest> userRestList = new ArrayList<>();

        List<UserDTO> userDTOList = userService.getUsers(page, limit);

        for (UserDTO userDTO : userDTOList) {
            UserRest userRest = new UserRest();
            BeanUtils.copyProperties(userDTO, userRest);
            userRestList.add(userRest);
        }

        return userRestList;
    }
}
