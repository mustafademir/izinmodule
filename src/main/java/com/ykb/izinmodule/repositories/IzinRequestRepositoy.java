package com.ykb.izinmodule.repositories;

import com.ykb.izinmodule.domain.entity.IzinRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IzinRequestRepositoy extends JpaRepository<IzinRequest, Long> {

}
