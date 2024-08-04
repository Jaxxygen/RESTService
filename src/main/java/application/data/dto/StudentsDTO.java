package application.data.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentsDTO {
    private int id;
    private String name;
    private int coordinator_id;
}
