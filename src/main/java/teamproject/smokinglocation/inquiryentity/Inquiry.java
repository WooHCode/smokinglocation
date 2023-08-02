package teamproject.smokinglocation.inquiryentity;

import lombok.Getter;
import teamproject.smokinglocation.userEnitiy.Member;

import javax.persistence.*;

@Entity
@Getter
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="inquiry_id")
    private Long id;
    private String title;
    private String content;
    private String reply;
    private Boolean hasReply;
    private Boolean isPrivate;

    @ManyToOne
    @JoinColumn(name="id")
    private Member member;
}
