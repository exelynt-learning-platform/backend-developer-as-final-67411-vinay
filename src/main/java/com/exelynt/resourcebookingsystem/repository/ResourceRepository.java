package com.exelynt.resourcebookingsystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.exelynt.resourcebookingsystem.entity.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Integer> {

}
