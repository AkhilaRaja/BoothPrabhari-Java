package com.springBoot.boothPrabhari.config;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.springBoot.boothPrabhari.service.VoterService;
import com.springBoot.boothPrabhari.service.VoterServiceImpl;
import com.springBoot.boothPrabhari.service.WardService;
import com.springBoot.boothPrabhari.service.WardServiceImpl;

@Configuration
public class ServiceConfig implements WebMvcConfigurer{
	
	@Bean
	@Autowired
	public DistrictService districtService() {
		return new DistrictServiceImpl();
	}
	
	@Bean
	@Autowired
	public ReligionService religionService() {
		return new ReligionServiceImpl();
	}
	
	@Bean
	@Autowired
	public CasteService casteService() {
		return new CasteServiceImpl();
	}
	
	@Bean
	@Autowired
	public LocalbodyService localbodyService() {
		return new LocalbodyServiceImpl();
	}
	
	@Bean
	@Autowired
	public WardService wardService() {
		return new WardServiceImpl();
	}
	
	@Bean
	@Autowired
	public PollingstationService pollingstationService() {
		return new PollingstationServiceImpl();
	}
	
	@Bean
	@Autowired
	public CandidateService candidateService() {
		return new CandidateServiceImpl();
	}
	
	@Bean
	@Autowired
	public VoterService voterService() {
		return new VoterServiceImpl();
	}

}
