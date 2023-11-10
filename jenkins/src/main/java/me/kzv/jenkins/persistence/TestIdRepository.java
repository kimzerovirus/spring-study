package me.kzv.jenkins.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestIdRepository extends JpaRepository<TestId, Long> {
}
