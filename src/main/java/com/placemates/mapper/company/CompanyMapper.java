package com.placemates.mapper.company;

import com.placemates.dao.company.CompanyDAO;
import com.placemates.dto.company.CompanyDTO;

public class CompanyMapper {

    public static CompanyDAO mapCompanyDTOToCompanyDAO(CompanyDTO companyDTO){
        CompanyDAO companyDAO = new CompanyDAO();
        companyDAO.setId(companyDTO.getId());
        companyDAO.setName(companyDTO.getName());
        companyDAO.setRole(companyDTO.getRole());
        companyDAO.setCtc(companyDTO.getCtc());
        companyDAO.setJobDescription(companyDTO.getJobDescription());
        companyDAO.setLogoURL(companyDTO.getLogoURL());
        companyDAO.setWebURL(companyDTO.getWebURL());
        companyDAO.setAllowBranch(companyDTO.getAllowBranch());
        companyDAO.setRequireSkills(companyDTO.getRequireSkills());
        companyDAO.setLocation(companyDTO.getLocation());
        return companyDAO;
    }

    public static CompanyDTO mapCompanyDAOToCompanyDTO(CompanyDAO companyDAO){
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(companyDAO.getId());
        companyDTO.setName(companyDAO.getName());
        companyDTO.setRole(companyDAO.getRole());
        companyDTO.setCtc(companyDAO.getCtc());
        companyDTO.setJobDescription(companyDAO.getJobDescription());
        companyDTO.setLogoURL(companyDAO.getLogoURL());
        companyDTO.setWebURL(companyDAO.getWebURL());
        companyDTO.setAllowBranch(companyDAO.getAllowBranch());
        companyDTO.setRequireSkills(companyDAO.getRequireSkills());
        companyDTO.setLocation(companyDAO.getLocation());
        return companyDTO;
    }
}
