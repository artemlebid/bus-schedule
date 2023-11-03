package com.busschedule.web.service.impl;

import com.busschedule.web.dto.CompanyDto;
import com.busschedule.web.models.Company;
import com.busschedule.web.repository.CompanyRepository;
import com.busschedule.web.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public void saveCompany(CompanyDto company){
        companyRepository.save(mapToCompany(company));
    }

    @Override
    public List<CompanyDto> findAllCompanies() {
        List<Company> allCompanies = companyRepository.findAll();
        return allCompanies.stream().map(company -> mapToCompanyDto(company)).collect(Collectors.toList());
    }

    @Override
    public CompanyDto findCompanyById(Long id) {
        Company company = new Company();
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if(optionalCompany.isPresent()){
            company = optionalCompany.get();
        }
        return mapToCompanyDto(company);
    }

    private CompanyDto mapToCompanyDto(Company company){
        CompanyDto companyDto = CompanyDto.builder()
                .id(company.getId())
                .name(company.getName())
                .build();
        return companyDto;
    }

    private Company mapToCompany(CompanyDto companyDto){
        Company company = Company.builder()
                .id(companyDto.getId())
                .name(companyDto.getName())
                .build();
        return company;
    }

}
