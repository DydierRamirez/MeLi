package com.meli.ipvalidator.service.blacklistip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.meli.ipvalidator.model.BlackList;

@Service
public class BlackListIP implements IBlackListIP{
	
	@Autowired
    private IBlackListIPRespository repository;


	@Override
	public BlackList getByIP(String ip) {
		return repository.findByIp(ip);
	}

	@Override
	public BlackList add(String ip) {
		var bList = new BlackList();
		bList.setIp(ip);
		return repository.save(bList);
	}

}
