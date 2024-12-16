package data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResourceDto {
    private Integer id;
    private String name;
    private Integer year;
    private String color;
    private String pantone_value;
}
