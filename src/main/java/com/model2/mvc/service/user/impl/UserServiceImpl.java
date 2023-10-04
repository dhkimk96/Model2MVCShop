package com.model2.mvc.service.user.impl;

import com.model2.mvc.common.Search;
import com.model2.mvc.mapper.UserMapper;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserDao;
import com.model2.mvc.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	public Map<String , Object > getUserList(Search search) throws Exception {
		List<User> list= userDao.getUserList(search);
		int totalCount = userDao.getTotalCount(search);

		Map<String, Object> map = new HashMap<>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));

		return map;
	}

	@Override
	public void updateUser(User user) throws Exception {
		userDao.updateUser(user);
	}

	@Override
	public boolean checkDuplication(String userId) throws Exception {
		boolean result=true;
		User user=userDao.getUser(userId);
		if(user != null) {
			result=false;
		}
		return result;
	}

	@Override
	public List<String> getAutoCompleteUser() throws Exception {
	    List<User> userList = userDao.getAutoCompleteUser();
	    List<String> autoCompleteList = new ArrayList<>();

	    for(int i=0;i<userList.size();i++) {
	    	autoCompleteList.add(userList.get(i).getUserId());
	    }

	    System.out.println(autoCompleteList);

		return autoCompleteList;
	}
}