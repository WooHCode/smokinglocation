package teamproject.smokinglocation.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class UncompletedAddressRepository {

    private final EntityManager em;


}
