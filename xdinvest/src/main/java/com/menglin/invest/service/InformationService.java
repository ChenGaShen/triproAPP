package com.menglin.invest.service;

import java.util.List;
import com.menglin.invest.entity.Information;



public interface InformationService {
		
	public List<Information> findAll();

    public void saveInformation(Information book);
   
    public Information findOne(int id);

    public void delete(int id);

    public List<Information> findByName(String name);
}
