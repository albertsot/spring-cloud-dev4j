package com.umax.ms_security.service;

import com.umax.ms_security.config.JwtProvider;
import com.umax.ms_security.model.dto.TokenDTO;
import com.umax.ms_security.model.dto.UserDTO;
import com.umax.ms_security.model.entities.UserEntity;
import com.umax.ms_security.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private PasswordEncoder encoder;

    public UserDTO save(UserDTO user){
        Optional<UserEntity> userOptional=userRepository.findByUsername(user.getUserName());
        if(userOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User %s already exist",user.getUserName()));
        }
        UserEntity userEntity1=userRepository.save(new UserEntity(user.getUserName(), encoder.encode(user.getPassword())));
        return modelMapper.map(userEntity1,UserDTO.class);
    }

    public TokenDTO login(UserDTO userDTO){
        UserEntity userEntity=userRepository.findByUsername(userDTO.getUserName()).orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        if(encoder.matches(userDTO.getPassword(),userEntity.getPassword())){
            return new TokenDTO(jwtProvider.createToken(userEntity));
        }
        else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        }
    public TokenDTO validate(String token){
        jwtProvider.validate(token);
        String username=jwtProvider.getUsernameToken(token);
        userRepository.findByUsername(username).orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        return new TokenDTO(token);
    }

}
