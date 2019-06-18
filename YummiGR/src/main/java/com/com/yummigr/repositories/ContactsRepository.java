package com.com.yummigr.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.com.yummigr.models.Contacts;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Repository
public interface ContactsRepository extends CrudRepository<Contacts,Integer>{


	@Transactional
	@Modifying
	@Query(value="delete from contacts_yummi where id=:id",nativeQuery=true)
	void deleteContact(@Param("id") Long id);

	@Transactional
	@Modifying
	@Query(value="delete from contacts_messenger where contacts_id=:id",nativeQuery=true)
	void deleteContactRelation(@Param("id") Long id);


	@Query(value="select * from contacts_yummi where id=:id",nativeQuery = true)
	Contacts getContactById(@Param("id") Long id);


	@Query(value="select id from contacts_yummi where email=:email and phone_number=:number",nativeQuery=true)
	Long  getContactsByEmailId(@Param("email") String email , @Param("number") String phone_number);

	@Query(value="select * from contacts_yummi where email=:email",nativeQuery=true)
	Contacts getContactsByEmail(@Param("email") String email);
	
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
	@Query(value="update contacts_yummi set email=:email , phone_number=:phone_number , message=:message , subject_message=:subject , update_at=:update_at"
			+ " where id=:id", nativeQuery=true)
	Integer updateContacts(@Param("email") String email, @Param("phone_number") String phone_number,
						   @Param("message") String message , @Param("subject") String subject_message,
						   @Param("update_at") String update_at, @Param("id") Long id);
	
	
	@Query(value=" select contacts_id from contacts_messenger where messenger_id=:id", nativeQuery=true)
	List<Integer> getAllIdContactByMessengerId(@Param("id") Long id);
	
	@Query(value ="select * from contacts_yummi where id=:id",nativeQuery=true)
	Contacts getContact(@Param("id") Integer id);


	@Query(value="select messenger_id from contacts_messenger where contacts_id=:id",nativeQuery=true)
	Long getMessengerId(@Param("id") Long contact_id);


	@Modifying
	@Transactional
	@Query(value="insert into contacts_yummi_logger_senders (contacts_id,logger_senders_id) values (:contacts_id,:logger_senders_id)",nativeQuery=true)
	void insertRelationContactsSenderLogger(@Param("contacts_id") Long contacts_id, @Param("logger_senders_id") Long logger_sender_id);

}
