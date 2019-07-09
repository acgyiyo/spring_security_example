package com.pluralsight.security;

import java.io.Serializable;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

public class FitnessPermissionEvaluator implements PermissionEvaluator {

	private DataSource datasource;

	@Override
	public boolean hasPermission(Authentication auth, Object targetDomainObject, Object permission) {
		JdbcTemplate template = new JdbcTemplate(datasource);
		Object[] args = { ((User) auth.getPrincipal()).getUsername(), targetDomainObject.getClass().getName(),
				permission.toString() };

		int count = template.queryForObject(
				"select count(*) from permissions p where p.username= ? and p.target= ? and p.permission = ?", args,
				Integer.class);

		if (count == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean hasPermission(Authentication auth, Serializable targetId, String targetType, Object permission) {
		// TODO Auto-generated method stub
		return false;
	}

	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}

	public DataSource getDatasource() {
		return datasource;
	}

}
