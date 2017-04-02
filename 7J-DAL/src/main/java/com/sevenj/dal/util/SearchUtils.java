package com.sevenj.dal.util;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import com.mysql.jdbc.StringUtils;

public class SearchUtils {
   public static void addOrderIfNeeded(Criteria criteria, String sortingColumn){
	   if (!StringUtils.isNullOrEmpty(sortingColumn)){
			String[] values = sortingColumn.split(" ");
			if (values.length > 1){
				if ("asc".equalsIgnoreCase(values[1].toLowerCase())){
					criteria.addOrder(Order.asc(values[0]));
				}else{
					criteria.addOrder(Order.desc(values[0]));
				}
			}
		}
   }
}
