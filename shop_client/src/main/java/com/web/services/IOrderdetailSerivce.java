package com.web.services;


import java.util.List;

import com.web.models.Orderdetail;
import com.web.models.ResponseObject;

public interface IOrderdetailSerivce {
	public ResponseObject<Orderdetail> createOrderdetail(Orderdetail orderdetail, String cookie);
	public ResponseObject<List<Orderdetail>> getOrderdetail(Integer ordId, String cookie);
}
