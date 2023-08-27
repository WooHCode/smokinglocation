package teamproject.smokinglocation.inquiryentity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teamproject.smokinglocation.userEnitiy.Member;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String reply;
    private Boolean hasReply;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    public void createInquiry(Member member,String title,String content) {
        this.member = member;
        this.title = title;
        this.content = content;
    }

    public void addReply(String reply) {
        this.reply = reply;
        this.hasReply = true;

    }
}
