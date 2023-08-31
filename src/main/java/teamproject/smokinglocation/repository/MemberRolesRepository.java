package teamproject.smokinglocation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import teamproject.smokinglocation.userEnitiy.MemberRoles;

@Repository
public interface MemberRolesRepository extends JpaRepository<MemberRoles, Long> {

	@Query("SELECT roles FROM MemberRoles WHERE member_id = :id")
	String findRolesByMemberId(@Param("id") Long id);
}
	
	

