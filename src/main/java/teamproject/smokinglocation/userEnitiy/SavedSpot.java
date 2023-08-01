package teamproject.smokinglocation.userEnitiy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SavedSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lng;
    private String lat;
    private String loc;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
