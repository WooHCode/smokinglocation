package teamproject.smokinglocation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import teamproject.smokinglocation.inquiryentity.Inquiry;

import java.util.List;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

    @Query("select i from Inquiry i, Member m where m.id=:memberId")
    public List<Inquiry> findAllByMemberId(@Param("memberId") Long memberId);
}
