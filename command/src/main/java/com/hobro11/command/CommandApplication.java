package com.hobro11.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hobro11.command.domain.members.MemberRole;
import com.hobro11.command.domain.members.service.MemberService;
import com.hobro11.command.domain.members.service.dto.MemberCreateDto;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class CommandApplication {

	@Autowired
	private  MemberService memberService;

	public static void main(String[] args) {
		SpringApplication.run(CommandApplication.class, args);
	}

	@PostConstruct
	public void startedAt() {
		MemberCreateDto dto = new MemberCreateDto(null, "test_user", "010-0000-0000", MemberRole.BASIC);
		memberService.createMember(dto);
	}

}
