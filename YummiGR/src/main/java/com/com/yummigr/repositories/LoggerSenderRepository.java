package com.com.yummigr.repositories;

import com.com.yummigr.models.LoggerSender;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggerSenderRepository extends CrudRepository<LoggerSender,Integer> {


}
