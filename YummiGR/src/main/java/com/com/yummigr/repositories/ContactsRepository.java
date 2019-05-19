package com.com.yummigr.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.com.yummigr.models.Contacts;

@Repository
public interface ContactsRepository extends CrudRepository<Contacts,Integer>{

}
