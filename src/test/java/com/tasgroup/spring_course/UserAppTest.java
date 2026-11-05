package com.tasgroup.spring_course;

import com.tasgroup.spring_course.controller.model.CreateUserResponse;
import com.tasgroup.spring_course.repository.UserRepository;
import com.tasgroup.spring_course.repository.model.UserEntity;
import com.tasgroup.spring_course.service.UserService;
import com.tasgroup.spring_course.service.dto.CreateUserDto;
import com.tasgroup.spring_course.service.mapper.CreateUserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserAppTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CreateUserMapper createUserMapper;

    @Test
    public void calcolaTotale() {
        double prezzo = 10.0;
        int quantita = 3;
        double totale = prezzo * quantita;
        assertEquals(30.0, totale);
    }

    @Test
    public void testCreateUser() {
        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setUsername("babbo");
        createUserDto.setPassword("Simone0!");
        createUserDto.setRole("ADMIN");
        createUserDto.setPaeseResidenza("Italia");
        createUserDto.setCittaResidenza("Milano");
        createUserDto.setResidenza("Italia - Milano");
        createUserDto.setDataDiNascita(LocalDate.parse("2001-01-01"));

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("babbo");
        userEntity.setRole("ADMIN");
        userEntity.setDataCreazione(LocalDateTime.now());

        CreateUserResponse createUserResponseMock = new CreateUserResponse();
        createUserResponseMock.setUsername("babbo");

        when(createUserMapper.createUserEntityMapper(any(CreateUserDto.class))).thenReturn(userEntity);
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        when(userRepository.findByUsernameRole(anyString(), anyString())).thenReturn(Optional.of(userEntity));
        when(createUserMapper.createUserResponseMapper(any(CreateUserDto.class))).thenReturn(createUserResponseMock);

        CreateUserResponse createUserResponse = userService.createUser(createUserDto);

        assertNotNull(createUserResponse);
        assertEquals("babbo", createUserResponse.getUsername());
        assertThrows(Exception.class, ()->{
           userService.createUser(null);
        });
    }

    @Test
    public void testThrowException() {
        assertThrows(NullPointerException.class, ()->{
            userService.createUser(null);
        });
    }
}
