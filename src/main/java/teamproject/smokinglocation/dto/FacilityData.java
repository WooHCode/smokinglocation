package teamproject.smokinglocation.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class FacilityData<T> {
    private int currentCount;
    private List<T> data;
    private int matchCount;
    private int page;
    private int perPage;
    private int totalCount;
}
