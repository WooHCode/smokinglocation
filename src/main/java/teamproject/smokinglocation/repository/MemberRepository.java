package teamproject.smokinglocation.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import teamproject.smokinglocation.userEnitiy.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberId(String username);

    @Query("SELECT m.memberId FROM Member m")
    List<String> findMemberId();

    @Modifying
    @Query("UPDATE Member m SET m.refreshToken = :refreshToken WHERE m.memberId = :memberId")
    void updateRefreshToken(@Param("refreshToken") String refreshToken, @Param("memberId") String memberId);

    @Query("SELECT m.refreshToken FROM Member m WHERE m.memberId = :memberId")
    String findRefreshToken(@Param("memberId") String memberId);

    @Query("SELECT m.password FROM Member m WHERE m.memberId = :memberId")
    String findPasswordByMemberId(@Param("memberId") String memberId);

    @Query("SELECT m.memberName FROM Member m WHERE m.memberId = :memberId")
    String findMemberNameByMemberId(@Param("memberId") String memberId);
    
    @Query("SELECT m.id FROM Member m WHERE m.refreshToken=:refreshToken")
    Long findMemberIdByRefreshToken(@Param("refreshToken") String refreshToken);

    // 소셜 로그인 회원 여부 확인을 위해 생성
    @Query("SELECT COUNT(m) FROM Member m WHERE m.memberId = :memberId")
    int findMemberEmailCountByMemberId(@Param("memberId") String memberId);
    
    // 비밀번호 찾기
    @Transactional
    Member findByMemberIdAndMemberName(String email, String memberName);

    @Query("SELECT m FROM Member m WHERE m.memberId = :memberId")
    Member findMemberEntityByEmail(@Param("memberId") String memberId);

    // 아이디 조히
    @Query("select id from Member where memberId =:memberId")
    Long findById(@Param("memberId") String memberId);
}
