package edu.integricert.service;

import java.util.List;

import edu.integricert.model.Company;
import edu.integricert.request.CompanyRequest;
import edu.integricert.request.CompanyUpdateRequest;

public interface CompanyService {

	Company createCompany(CompanyRequest companyRequest);

	List<Company> findAll();

	Company findById(Integer id);

	Company findByName(String name);

	void save(Company company);

	void saveAll(List<Company> companys);

	void deleteById(Integer id);

	Company updateCompany(CompanyUpdateRequest companyRequest);

}
