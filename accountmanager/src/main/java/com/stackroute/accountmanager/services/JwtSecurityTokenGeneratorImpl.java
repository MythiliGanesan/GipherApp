package com.stackroute.accountmanager.services;

import java.util.Map;
import org.springframework.stereotype.Service;

import com.stackroute.accountmanager.model.User;

import java.util.Date;
import java.util.HashMap;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtSecurityTokenGeneratorImpl implements SecurityTokenGenerator {

	@Override
	public Map<String, String> generateToken(User user) {
		String jwtToken = "";
		jwtToken = Jwts.builder().setSubject(user.getUserId()).setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey").compact();
		Map<String, String> map = new HashMap<>();
		map.put("token", jwtToken);
		System.out.println(jwtToken);
		map.put("message", "User successfully logged in");
		return map;
	}

}


