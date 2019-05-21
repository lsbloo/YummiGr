package com.com.yummigr.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.com.yummigr.models.Contacts;

@Repository
public interface ContactsRepository extends CrudRepository<Contacts,Integer>{

	
	
	@Query(value= "select * from contacts_yummi where email=:email or phone_number=:number", nativeQuery=true)
	Contacts checkExistContact(@Param("email") String email, @Param("number") String number);
	
	
	
	@Query(value ="select * from contacts_yummi where id=:id",nativeQuery=true)
	Contacts getContact(@Param("id") Long id);
	
	
	@Modifying
	@Transactional
	@Query(value="insert into contacts_messenger (messenger_id,contacts_id)"
			+ "values (:messenger_id,:contacts_id)",nativeQuery=true)
	void insertRelationContactsMessenger(@Param("messenger_id") Long messenger_id,
			@Param("contacts_id") Long contact_id);
	
	
	@Modifying
	@Transactional
	@Query(value="update contacts_yummi set email=:email , phone_number=:phone_number , message=:message"
			+ " where id=:id", nativeQuery=true)
	Integer updateContacts(@Param("email") String email, @Param("phone_number") String phone_number,
			@Param("message") String message , @Param("id") Long id);
}
