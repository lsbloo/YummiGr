package com.com.yummigr.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.com.yummigr.models.Schedule;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule,Integer>{
	

	
	
	@Modifying
	@Transactional
	@Query(value="update schedule_messenger_connector set time=:time  where id=:id",nativeQuery=true)
	public Integer updateScheduleTime(@Param("time") Integer time, @Param("id")
	Integer id);
	
	@Query(value = "select * from schedule_messenger_connector where id=:id",nativeQuery=true)
	Schedule getScheduleById(@Param("id") Long id);
	
	
	
}

