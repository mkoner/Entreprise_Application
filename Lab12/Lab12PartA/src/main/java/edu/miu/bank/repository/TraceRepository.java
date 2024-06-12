package edu.miu.bank.repository;

import edu.miu.bank.domain.TraceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraceRepository extends JpaRepository<TraceRecord, Integer> {
}
