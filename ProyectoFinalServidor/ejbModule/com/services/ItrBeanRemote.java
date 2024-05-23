package com.services;

import javax.ejb.Remote;

import com.entities.Itr;

@Remote
public interface ItrBeanRemote extends ServiceInterface<Itr> {
	
}
