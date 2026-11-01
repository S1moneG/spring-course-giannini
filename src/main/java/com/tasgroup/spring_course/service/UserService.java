package com.tasgroup.spring_course.service;

import com.tasgroup.spring_course.controller.model.CreateUserResponse;
import com.tasgroup.spring_course.controller.model.GetUserResponse;
import com.tasgroup.spring_course.repository.UserRepository;
import com.tasgroup.spring_course.repository.model.UserEntity;
import com.tasgroup.spring_course.service.dto.CreateUserDto;
import com.tasgroup.spring_course.service.dto.GetUserRequestDto;
import com.tasgroup.spring_course.service.mapper.CreateUserMapper;
import com.tasgroup.spring_course.service.mapper.GetUserMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final CreateUserMapper createUserMapper;
    private final GetUserMapper getUserMapper;

    private  final UserRepository userRepository;

    public GetUserResponse getUser(String username) {
        GetUserResponse userResponse;
        if(userRepository.findByUsername(username).isPresent()) {
            UserEntity user = userRepository.findByUsername(username).get();
            GetUserRequestDto getUserRequestDto = getUserMapper.getUserDtoMapper(user);
            userResponse = getUserMapper.getUserResponseMapper(getUserRequestDto);
        } else {
            userResponse = new GetUserResponse();
            userResponse.setUsername(username);

            LocalDate currentDate = LocalDate.now();

            userResponse.setDataCreazione(currentDate);
            userResponse.setRole("Admin");
            userResponse.setPaeseResidenza("Italy");
        }
        return userResponse;
    }

    public CreateUserResponse createUser(CreateUserDto createUserDto) {

        UserEntity userEntityInsert = createUserMapper.createUserEntityMapper(createUserDto);
        userRepository.save(userEntityInsert);

        //TO-DO: aggiungere controlli + exceptionHandler dalla lezione4
        UserEntity userEntityRetrived = userRepository.findByUsernameRole(createUserDto.getUsername(), createUserDto.getRole()).get();

        createUserDto.setDataCreazione(userEntityRetrived.getDataCreazione());

        return createUserMapper.createUserResponseMapper(createUserDto);
    }

    public ResponseEntity<String> deleteUser(String username) {
        Optional<UserEntity> userOptional = userRepository.findByUsername(username);
        if(userOptional.isPresent()) {
            userRepository.deleteById(userOptional.get().getId());
            return new ResponseEntity<>("Rimosso user: " + username, HttpStatus.OK);
        } else {
            return  new ResponseEntity<>("User: " + username + " non esiste", HttpStatus.NOT_FOUND);
        }
    }
}
