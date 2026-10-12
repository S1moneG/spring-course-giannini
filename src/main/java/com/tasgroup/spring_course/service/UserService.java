package service;

import dto.GetUserResponseDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService {


    public GetUserResponseDto getUser(String username) {
        GetUserResponseDto user = new GetUserResponseDto();
        user.setUsername(username);

        LocalDate currentDate = LocalDate.now();

        user.setDataCreazione(currentDate);
        user.setRole("Admin");
        user.setPaeseResidenza("Italy");

        return user;
    }
}
