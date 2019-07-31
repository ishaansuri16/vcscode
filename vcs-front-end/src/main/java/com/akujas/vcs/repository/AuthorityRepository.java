package com.akujas.vcs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akujas.vcs.domain.Authority;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
