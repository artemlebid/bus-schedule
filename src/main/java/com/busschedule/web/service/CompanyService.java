package com.busschedule.web.service;

import com.busschedule.web.dto.CompanyDto;
import com.busschedule.web.models.Company;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CompanyService {
    List<CompanyDto> findAllCompanies();
    CompanyDto findCompanyById(Long id);
    void saveCompany(CompanyDto company);
}
