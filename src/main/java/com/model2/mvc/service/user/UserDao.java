package com.model2.mvc.service.user;

import com.model2.mvc.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao extends JpaRepository<UserEntity, String> {

//	// INSERT
//	public void addUser(User user) throws Exception ;
//
//	// SELECT ONE
//	public User getUser(String userId) throws Exception ;
//
//	// SELECT LIST
//	public List<User> getUserList(Search search) throws Exception ;
//
//	// UPDATE
//	public void updateUser(User user) throws Exception ;
//
//	public int getTotalCount(Search search) throws Exception ;
//
//	// autocomplete
//	public List<User> getAutoCompleteUser() throws Exception ;

}