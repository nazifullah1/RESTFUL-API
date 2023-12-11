package com.zebraIntern.rest.restfulwebservices.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeMongoDbRepository extends MongoRepository<EmployeeEntity, String> {

	Optional<EmployeeEntity> findByEmployeeId(String id);

	Optional<EmployeeEntity> deleteByEmployeeId(String id);

	List<EmployeeEntity> findByLastName(String lastName);

	List<EmployeeEntity> findByLastNameOrderByEmployeeFirstNameAsc(String lastName);
	

}
