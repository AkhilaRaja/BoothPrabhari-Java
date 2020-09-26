package com.springBoot.boothPrabhari.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.springBoot.boothPrabhari.service.CandidateService;
import com.springBoot.boothPrabhari.service.CandidateServiceImpl;
import com.springBoot.boothPrabhari.service.CasteService;
import com.springBoot.boothPrabhari.service.CasteServiceImpl;
import com.springBoot.boothPrabhari.service.DistrictService;
import com.springBoot.boothPrabhari.service.DistrictServiceImpl;
import com.springBoot.boothPrabhari.service.LocalbodyService;
import com.springBoot.boothPrabhari.service.LocalbodyServiceImpl;
import com.springBoot.boothPrabhari.service.PollingstationService;
import com.springBoot.boothPrabhari.service.PollingstationServiceImpl;
import com.springBoot.boothPrabhari.service.ReligionService;
import com.springBoot.boothPrabhari.service.ReligionServiceImpl;
import com.springBoot.boothPrabhari.service.UserService;
import com.springBoot.boothPrabhari.service.UserServiceImpl;
import com.springBoot.boothPrabhari.service.VoterService;
import com.springBoot.boothPrabhari.service.VoterServiceImpl;
import com.springBoot.boothPrabhari.service.WardService;
import com.springBoot.boothPrabhari.service.WardServiceImpl;

@Configuration
public class ServiceConfig implements WebMvcConfigurer {

	@Bean
	public DistrictService districtService() {
		return new DistrictServiceImpl();
	}

	@Bean
	public ReligionService religionService() {
		return new ReligionServiceImpl();
	}

	@Bean
	public CasteService casteService() {
		return new CasteServiceImpl();
	}

	@Bean
	public LocalbodyService localbodyService() {
		return new LocalbodyServiceImpl();
	}

	@Bean
	public WardService wardService() {
		return new WardServiceImpl();
	}

	@Bean
	public PollingstationService pollingstationService() {
		return new PollingstationServiceImpl();
	}

	@Bean
	public CandidateService candidateService() {
		return new CandidateServiceImpl();
	}

	@Bean
	public VoterService voterService() {
		return new VoterServiceImpl();
	}

	@Bean
	public UserService suerService() {
		return new UserServiceImpl();
	}

}
