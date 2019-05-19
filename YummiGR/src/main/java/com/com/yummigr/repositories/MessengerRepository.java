package com.com.yummigr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.com.yummigr.models.Messenger;

@Repository
public interface MessengerRepository extends CrudRepository<Messenger,Integer>{
	
	

	@Query(value="select * from messenger_user",nativeQuery=true)
	List<Messenger> getAllMessenger();
}
