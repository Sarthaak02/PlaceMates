package com.placemates.service.company;

import com.placemates.dao.company.CompanyDAO;
import com.placemates.dto.company.CompanyDTO;
import com.placemates.exception.ResourceAlreadyExistsException;
import com.placemates.exception.ResourceNotFoundException;
import com.placemates.repository.company.CompanyRepository;
import com.placemates.service.user.UserService;
import com.placemates.util.logger.LoggerUtil;
import com.placemates.util.mapper.compnay.CompanyMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.placemates.constant.AppConstants.*;

@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;
    private final UserService userService;

    public CompanyServiceImpl(CompanyRepository companyRepository, UserService userService) {
        this.companyRepository = companyRepository;
        this.userService = userService;
    }

    @Transactional
    @Override
    public CompanyDTO createCompany(CompanyDTO companyDTO) {
        String username = userService.getCurrentUserUsername();
        double startTime = System.currentTimeMillis();
        double endTime;
        double duration;

        if(companyRepository.findByName(companyDTO.getName()) != null){
            endTime = System.currentTimeMillis();
            duration = (endTime - startTime) / 1000;
            log.warn(LoggerUtil.buildLog(COMPANY,CREATE,"Company already exists with name- " + companyDTO.getName(), username, duration, FAIL));
            throw new ResourceAlreadyExistsException("Company already exists with name: " + companyDTO.getName());
        }

        CompanyDAO companyDAO = CompanyMapper.INSTANCE.toCompanyDAO(companyDTO);
        companyDAO.setCompanyId(null);
        companyDAO = companyRepository.save(companyDAO);

        endTime = System.currentTimeMillis();
        duration = (endTime - startTime) / 1000;
        log.info(LoggerUtil.buildLog(COMPANY,CREATE,"Id- " + companyDAO.getCompanyId(), username, duration, SUCCESS));

        companyDTO = CompanyMapper.INSTANCE.toCompanyDTO(companyDAO);

        return companyDTO;
    }

    @Override
    public CompanyDTO getCompany(Integer companyId) {
        String username = userService.getCurrentUserUsername();
        double startTime = System.currentTimeMillis();
        double endTime;
        double duration;

        CompanyDAO companyDAO = companyRepository.findById(companyId).orElseThrow( () -> {
            log.error(LoggerUtil.buildLog(COMPANY,READ,"Company not found with id.- " + companyId, username, (System.currentTimeMillis() - startTime) / 1000, FAIL));
            return new ResourceNotFoundException("Company not found with id:" + companyId);
        });

        endTime = System.currentTimeMillis();
        duration = (endTime - startTime) / 1000;
        log.info(LoggerUtil.buildLog(COMPANY,READ,"Company fetched with id- " + companyDAO.getCompanyId(), username, duration, SUCCESS));

        CompanyDTO companyDTO = CompanyMapper.INSTANCE.toCompanyDTO(companyDAO);

        return companyDTO;
    }

    @Override
    public List<CompanyDTO> getAllCompanies() {
        String username = userService.getCurrentUserUsername();
        double startTime = System.currentTimeMillis();

        List<CompanyDAO> companyDAOList = companyRepository.findAll();

        List<CompanyDTO> companyDTOList = CompanyMapper.INSTANCE.toCompanyDTOList(companyDAOList);
        if(companyDAOList.isEmpty()){
            log.error(LoggerUtil.buildLog(COMPANY,READ,"Companies not found", username, (System.currentTimeMillis() - startTime) / 1000, FAIL));
            return new ArrayList<>();
        }
        else{
            log.info(LoggerUtil.buildLog(COMPANY,READ,companyDAOList.size() + "companies fetched", username, (System.currentTimeMillis() - startTime) / 1000, SUCCESS));
        }
        return companyDTOList;
    }

    @Transactional
    @Override
    public CompanyDTO updateCompany(Integer companyId, CompanyDTO companyDTO) {
        String username = userService.getCurrentUserUsername();
        double startTime = System.currentTimeMillis();
        double endTime;
        double duration;

        if(!companyRepository.existsById(companyId)){
            log.error(LoggerUtil.buildLog(COMPANY,UPDATE,"Company not found with id.- " + companyId, username, (System.currentTimeMillis() - startTime) / 1000, FAIL));
            throw new ResourceNotFoundException("Company not found with id:" + companyId);
        }
        if(companyRepository.findByName(companyDTO.getName()) != null && companyRepository.findByName(companyDTO.getName()).getCompanyId() != companyId){
            endTime = System.currentTimeMillis();
            duration = (endTime - startTime) / 1000;
            log.warn(LoggerUtil.buildLog(COMPANY,UPDATE,"Company already exists with name- " + companyDTO.getName(), username, duration, FAIL));
            throw new ResourceAlreadyExistsException("Company already exists with name: " + companyDTO.getName());
        }
        CompanyDAO companyDAO = CompanyMapper.INSTANCE.toCompanyDAO(companyDTO);
        companyDAO.setCompanyId(companyId);
        companyRepository.save(companyDAO);

        endTime = System.currentTimeMillis();
        duration = (endTime - startTime) / 1000;
        log.info(LoggerUtil.buildLog(COMPANY,UPDATE,"Id- " + companyDAO.getCompanyId(), username, duration, SUCCESS));

        companyDTO = CompanyMapper.INSTANCE.toCompanyDTO(companyDAO);

        return companyDTO;
    }

    @Override
    public void deleteCompany(Integer companyId) {
        String username = userService.getCurrentUserUsername();
        double startTime = System.currentTimeMillis();
        double endTime;
        double duration;

        if(!companyRepository.existsById(companyId)){
            log.error(LoggerUtil.buildLog(COMPANY,DELETE,"Company not found with id.- " + companyId, username, (System.currentTimeMillis() - startTime) / 1000, FAIL));
            throw new ResourceNotFoundException("Company not found with id:" + companyId);
        }

        companyRepository.deleteById(companyId);

        endTime = System.currentTimeMillis();
        duration = (endTime - startTime) / 1000;
        log.info(LoggerUtil.buildLog(COMPANY,DELETE,"Id- " + companyId, username, duration, SUCCESS));
    }
}
