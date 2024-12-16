package data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResourceDto {
    private Integer page;
    private Integer per_page;
    private Integer total;
    private Integer total_pages;
    private List<ResourceDto> data;
    private SupportDto support;
}
