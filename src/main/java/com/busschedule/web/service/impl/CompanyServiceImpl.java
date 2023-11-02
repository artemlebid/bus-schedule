package com.busschedule.web.service.impl;

import com.busschedule.web.models.Company;
import com.busschedule.web.repository.CompanyRepository;
import com.busschedule.web.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public void saveCompany(Company company){
        companyRepository.save(company);
    }

    @Override
    public List<Company> findAllCompanies() {
        List<Company> allCompanies = companyRepository.findAll();
        return allCompanies;
    }

    @Override
    public Company findCompanyById(Long id) {
        Company company = new Company();
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if(optionalCompany.isPresent()){
            company = optionalCompany.get();
        }
        return company;
    }
}
