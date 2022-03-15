package com.chseidler.invoicesappbe.service.impl;

import com.chseidler.invoicesappbe.dto.UserDTO;
import com.chseidler.invoicesappbe.entity.UserEntity;
import com.chseidler.invoicesappbe.exception.UserServiceException;
import com.chseidler.invoicesappbe.repository.UserRepository;
import com.chseidler.invoicesappbe.service.UserService;
import com.chseidler.invoicesappbe.utils.ErrorMessages;
import com.chseidler.invoicesappbe.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        UserEntity userEntityByEmail = userRepository.findByEmail(userDTO.getEmail());
        if (userEntityByEmail != null) {
            throw new UserServiceException(ErrorMessages.RECORD_ALREADY_EXIST.getErrorMessage());
        }

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDTO, userEntity);

        String publicUserId = utils.generateUserId(20);
        userEntity.setUserId(publicUserId);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));

        UserEntity storedUserEntity = userRepository.save(userEntity);

        UserDTO returnUserDTO = new UserDTO();
        BeanUtils.copyProperties(storedUserEntity, returnUserDTO);

        return returnUserDTO;
    }

    @Override
    public UserDTO getUser(String nickname) {

        UserEntity userEntity = userRepository.findByNickname(nickname);
        if (userEntity == null) {
            throw new UserServiceException(ErrorMessages.CAN_NOT_LOGIN.getErrorMessage());
        }

        UserDTO returnUserDTO = new UserDTO();
        BeanUtils.copyProperties(userEntity, returnUserDTO);

        return returnUserDTO;
    }

    @Override
    public UserDTO getUserByUserId(String userId) {

        UserDTO returnUserDTO = new UserDTO();
        UserEntity userEntityByUserId = userRepository.findByUserId(userId);

        if(userEntityByUserId == null) {
            throw new UsernameNotFoundException(userId);
        }

        BeanUtils.copyProperties(userEntityByUserId, returnUserDTO);

        return returnUserDTO;
    }

    @Override
    public List<UserDTO> getUsers(int page, int limit) {

        List<UserDTO> userDTOList = new ArrayList<>();

        if (page > 0) { page -= 1;}

        Pageable pageable = PageRequest.of(page, limit);
        Page<UserEntity> usersPage = userRepository.findAll(pageable);

        List<UserEntity> userEntityList = usersPage.getContent();

        for (UserEntity userEntity : userEntityList) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userEntity, userDTO);
            userDTOList.add(userDTO);
        }

        return userDTOList;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }

        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }
}
