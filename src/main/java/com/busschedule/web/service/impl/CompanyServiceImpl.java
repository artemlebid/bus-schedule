package com.busschedule.web.service.impl;

import com.busschedule.web.models.Company;
import com.busschedule.web.repository.CompanyRepository;
import com.busschedule.web.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> findAllCompanies() {
        List<Company> allCompanies = companyRepository.findAll();
        return allCompanies;
    }
}
