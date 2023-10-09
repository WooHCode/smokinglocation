package teamproject.smokinglocation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import teamproject.smokinglocation.inquiryentity.Inquiry;

import java.util.List;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

    @Query("select i from Inquiry i where i.member.id=:memberId")
    public List<Inquiry> findAllByMemberId(@Param("memberId") Long memberId);

//    @Query("select i from Inquiry i where i.id=:inquiryId")
//    public Inquiry findById(@Param("inquiryId") Long inquiryId);
}
