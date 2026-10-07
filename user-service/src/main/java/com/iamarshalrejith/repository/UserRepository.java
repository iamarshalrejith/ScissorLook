package com.iamarshalrejith.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iamarshalrejith.modal.User;

public interface UserRepository extends JpaRepository<User, Long>{

}