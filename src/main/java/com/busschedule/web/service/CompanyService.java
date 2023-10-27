package com.busschedule.web.service;

import com.busschedule.web.models.Company;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CompanyService {
    List<Company> findAllCompanies();
}
