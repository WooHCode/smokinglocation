package teamproject.smokinglocation.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TotalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lat;
    private String lon;
    private String location;
    private String gu;

    @Builder
    public TotalData(String lat, String lon, String location, String gu) {
        this.lat = lat;
        this.lon = lon;
        this.location = location;
        this.gu = gu;
    }
}
