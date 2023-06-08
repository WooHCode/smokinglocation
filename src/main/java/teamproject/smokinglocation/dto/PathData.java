package teamproject.smokinglocation.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PathData<T> {
    private Object code;
    private Object message;
    private Object currentDateTime;
    private List<T> route;

}
