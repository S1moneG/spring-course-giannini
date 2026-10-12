package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.beans.ConstructorProperties;
import java.time.LocalDate;

@Getter
@Setter
public class GetUserResponseDto {

    private String username;

    private LocalDate dataCreazione;

    private String role;

    private String paeseResidenza;

    public GetUserResponseDto() {
    }
}
