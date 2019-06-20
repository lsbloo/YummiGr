package com.com.yummigr.repositories;

import com.com.yummigr.models.LoggerSender;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggerSenderRepository extends CrudRepository<LoggerSender,Integer> {

	
	
	@Query(value="select * from logger_sender where id=:id",nativeQuery=true)
	public LoggerSender getLoggerById(@Param("id") Long id );
	
	
	@Query(value="select distinct logger_senders_id from contacts_yummi_logger_senders where contacts_id=:id_contact",nativeQuery=true)
	List<Long> getIdsLoggersRelatedContact(@Param("id_contact") Long contact_id);
	
	
	@Query(value="select count(*) from logger_sender where id=:id and month=:month and tracker=:param",nativeQuery=true)
	Integer getLoggerEmailByDataMonth(@Param("id") Long id , @Param("month") String month , @Param("param") String param);
	

	@Query(value="select distinct contacts_id from contacts_yummi_logger_senders where logger_senders_id=:id",nativeQuery=true)
	List<Long> getDistinctContactsIdByLoggerId(@Param("id") Long id_logger);
}
