package am.complaints;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("complaint_query_store")
public interface ComplaintQueryObjectRepository extends JpaRepository<ComplaintQueryObject, String> {
}
