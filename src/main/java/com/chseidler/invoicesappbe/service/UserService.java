package com.chseidler.invoicesappbe.service;

import com.chseidler.invoicesappbe.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    public UserDTO createUser(UserDTO userDTO);
    public UserDTO getUser(String nickname);
    public UserDTO getUserByUserId(String userId);
    public List<UserDTO> getUsers(int page, int limit);
}
