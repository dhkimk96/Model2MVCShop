package com.model2.mvc.service.user.impl;

import com.model2.mvc.common.Search;
import com.model2.mvc.entity.UserEntity;
import com.model2.mvc.mapper.UserMapper;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserDao;
import com.model2.mvc.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service("userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService{

    private final UserDao userDao;
    private final UserMapper userMapper;

	@Override
	public void addUser(User user) throws Exception {
		userDao.save(userMapper.userToUserEntity(user));
	}

	@Override
	public User getUser(String userId) throws Exception {
		return userDao.findById(userId).map(userMapper::UserEntityToUser).orElse(null);
	}

	@Override
	public Map<String , Object> getUserList(Search search) throws Exception {
		Sort sort = Sort.by(search.getOrderBy());
		Pageable pageable = PageRequest.of(search.getCurrentPage(), search.getPageSize(), sort);
		Page<UserEntity> page;
		if(search.getSearchKeyword() == null || search.getSearchKeyword().isEmpty()){
			page = userDao.findAll(pageable);
		}else{
			page = userDao.findByUserIdContaining(search.getSearchKeyword(), pageable);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", page.map(userMapper::UserEntityToUser).toList());
		map.put("totalCount", page.getTotalPages());
		return map;
	}

	@Override
	public void updateUser(User user) throws Exception {
		userDao.save(userMapper.userToUserEntity(user));
	}

	@Override
	public boolean checkDuplication(String userId) throws Exception {
		boolean result=true;
		User user=userDao.findById(userId).map(userMapper::UserEntityToUser).orElse(null);
		if(user != null) {
			result=false;
		}
		return result;
	}

//	@Override
//	public List<String> getAutoCompleteUser() throws Exception {
//	    List<User> userList = userDao.getAutoCompleteUser();
//	    List<String> autoCompleteList = new ArrayList<>();
//
//	    for(int i=0;i<userList.size();i++) {
//	    	autoCompleteList.add(userList.get(i).getUserId());
//	    }
//
//	    System.out.println(autoCompleteList);
//
//		return autoCompleteList;
//	}
}