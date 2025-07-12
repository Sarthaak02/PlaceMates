package com.placemates.service.placedalum;

import com.placemates.dao.placedalum.PlacedAlumDAO;
import com.placemates.dto.placedalum.PlacedAlumDTO;
import com.placemates.exception.ResourceAlreadyExistsException;
import com.placemates.exception.ResourceNotFoundException;
import com.placemates.repository.placedalum.PlacedAlumRepository;
import com.placemates.service.user.UserService;
import com.placemates.util.logger.LoggerUtil;
import com.placemates.util.mapper.placedalum.PlacedAlumMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.placemates.constant.AppConstants.*;

@Service
@Slf4j
public class PlacedAlumServiceImpl implements PlacedAlumService {

    private final PlacedAlumRepository placedAlumRepository;
    private final UserService userService;

    public PlacedAlumServiceImpl(PlacedAlumRepository placedAlumRepository, UserService userService) {
        this.placedAlumRepository = placedAlumRepository;
        this.userService = userService;
    }

    @Override
    public PlacedAlumDTO createPlacedAlum(PlacedAlumDTO placedAlumDTO) {
        String username = userService.getCurrentUserUsername();
        double startTime = System.currentTimeMillis();
        double endTime;
        double duration;

        if(placedAlumRepository.findByMail(placedAlumDTO.getMail()) != null){
            endTime = System.currentTimeMillis();
            duration = (endTime - startTime) / 1000;
            log.warn(LoggerUtil.buildLog(PLACED_ALUM,CREATE,"Placed Alum already exists with email- " + placedAlumDTO.getMail(), username, duration, FAIL));
            throw new ResourceAlreadyExistsException("Placed Alum already exists with email:" + placedAlumDTO.getMail());
        }
        if(placedAlumRepository.findByMobileNumber(placedAlumDTO.getMobileNumber()) != null){
            endTime = System.currentTimeMillis();
            duration = (endTime - startTime) / 1000;
            log.warn(LoggerUtil.buildLog(PLACED_ALUM,CREATE,"Placed Alum already exists with mobile number- " + placedAlumDTO.getMobileNumber(), username, duration, FAIL));
            throw new ResourceAlreadyExistsException("Placed Alum already exists with mobile number: " + placedAlumDTO.getMobileNumber());
        }

        PlacedAlumDAO placedAlumDAO = PlacedAlumMapper.INSTANCE.toPlacedAlumDAO(placedAlumDTO);
        placedAlumDAO.setPlacedAlumId(null);

        placedAlumDAO = placedAlumRepository.save(placedAlumDAO);

        endTime = System.currentTimeMillis();
        duration = (endTime - startTime) / 1000;
        log.info(LoggerUtil.buildLog(PLACED_ALUM,CREATE,"Id- " + placedAlumDAO.getPlacedAlumId(), username, duration, SUCCESS));

        return PlacedAlumMapper.INSTANCE.toPlacedAlumDTO(placedAlumDAO);
    }

    @Override
    public PlacedAlumDTO getPlacedAlum(Integer placedAlumId) {
        String username = userService.getCurrentUserUsername();
        double startTime = System.currentTimeMillis();
        double endTime;
        double duration;

        PlacedAlumDAO placedAlumDAO = placedAlumRepository.findById(placedAlumId).orElseThrow(() -> {
            log.error(LoggerUtil.buildLog(PLACED_ALUM,READ,"Placed Alum not found with Id- " + placedAlumId, username, (System.currentTimeMillis() - startTime) / 1000, FAIL));
            return new ResourceNotFoundException("Placed Alum not found with Id: " + placedAlumId);
        });

        endTime = System.currentTimeMillis();
        duration = (endTime - startTime) / 1000;
        log.info(LoggerUtil.buildLog(PLACED_ALUM,READ,"Placed Alum fetched with Id- " + placedAlumDAO.getPlacedAlumId(), username, duration, SUCCESS));

        return PlacedAlumMapper.INSTANCE.toPlacedAlumDTO(placedAlumDAO);
    }

    @Override
    public List<PlacedAlumDTO> getAllPlacedAlums() {
        String username = userService.getCurrentUserUsername();
        double startTime = System.currentTimeMillis();

        List<PlacedAlumDAO> placedAlumDAOList = placedAlumRepository.findAll();

        if(placedAlumDAOList.isEmpty()){
            log.error(LoggerUtil.buildLog(PLACED_ALUM,READ,"Placed Alums not found", username, (System.currentTimeMillis() - startTime) / 1000, FAIL));
            return new ArrayList<>();
        }
        else log.info(LoggerUtil.buildLog(PLACED_ALUM,READ,placedAlumDAOList.size() + " Placed Alums fetched", username, (System.currentTimeMillis() - startTime) / 1000, SUCCESS));

        return PlacedAlumMapper.INSTANCE.toPlacedAlumDTOList(placedAlumDAOList);
    }

    @Override
    public List<PlacedAlumDTO> getAllPlacedAlumsByCompanyId(Integer companyId) {
        String username = userService.getCurrentUserUsername();
        double startTime = System.currentTimeMillis();

        List<PlacedAlumDAO> placedAlumDAOList = placedAlumRepository.findAllByCompanyDAO_CompanyId(companyId);

        if(placedAlumDAOList.isEmpty()){
            log.error(LoggerUtil.buildLog(PLACED_ALUM,READ,"Placed Alums not found with company id-" + companyId, username, (System.currentTimeMillis() - startTime) / 1000, FAIL));
            return new ArrayList<>();
        }
        else log.info(LoggerUtil.buildLog(PLACED_ALUM,READ,placedAlumDAOList.size() + "Placed Alums fetched with company id-" + companyId, username, (System.currentTimeMillis() - startTime) / 1000, SUCCESS));

        return PlacedAlumMapper.INSTANCE.toPlacedAlumDTOList(placedAlumDAOList);
    }

    @Override
    public PlacedAlumDTO updatePlacedAlum(Integer placedAlumId, PlacedAlumDTO placedAlumDTO) {
        String username = userService.getCurrentUserUsername();
        double startTime = System.currentTimeMillis();
        double endTime;
        double duration;

        if(!placedAlumRepository.existsById(placedAlumId)){
            endTime = System.currentTimeMillis();
            duration = (endTime - startTime) / 1000;
            log.error(LoggerUtil.buildLog(PLACED_ALUM,UPDATE,"Placed Alum not found with Id- " + placedAlumId, username, duration, FAIL));
            throw new ResourceNotFoundException("Placed Alum not found with Id: " + placedAlumId);
        }

        if(placedAlumRepository.findByMail(placedAlumDTO.getMail()) != null && placedAlumRepository.findByMail(placedAlumDTO.getMail()).getPlacedAlumId() != placedAlumId){
            endTime = System.currentTimeMillis();
            duration = (endTime - startTime) / 1000;
            log.warn(LoggerUtil.buildLog(PLACED_ALUM,UPDATE,"Placed Alum already exists with email- " + placedAlumDTO.getMail(), username, duration, FAIL));
            throw new ResourceAlreadyExistsException("Placed Alum already exists with email:" + placedAlumDTO.getMail());
        }

        if(placedAlumRepository.findByMobileNumber(placedAlumDTO.getMobileNumber()) != null && placedAlumRepository.findByMobileNumber(placedAlumDTO.getMobileNumber()).getPlacedAlumId() != placedAlumId){
            endTime = System.currentTimeMillis();
            duration = (endTime - startTime) / 1000;
            log.warn(LoggerUtil.buildLog(PLACED_ALUM,UPDATE,"Placed Alum already exists with mobile number- " + placedAlumDTO.getMobileNumber(), username, duration, FAIL));
            throw new ResourceAlreadyExistsException("Placed Alum already exists with mobile number: " + placedAlumDTO.getMobileNumber());
        }

        PlacedAlumDAO placedAlumDAO = PlacedAlumMapper.INSTANCE.toPlacedAlumDAO(placedAlumDTO);
        placedAlumDAO.setPlacedAlumId(placedAlumId);
        placedAlumDAO = placedAlumRepository.save(placedAlumDAO);

        endTime = System.currentTimeMillis();
        duration = (endTime - startTime) / 1000;
        log.info(LoggerUtil.buildLog(PLACED_ALUM,UPDATE,"Id- " + placedAlumDAO.getPlacedAlumId(), username, duration, SUCCESS));

        return PlacedAlumMapper.INSTANCE.toPlacedAlumDTO(placedAlumDAO);
    }

    @Override
    public void deletePlacedAlum(Integer placedAlumId) {
        String username = userService.getCurrentUserUsername();
        double startTime = System.currentTimeMillis();
        double endTime;
        double duration;

        if(!placedAlumRepository.existsById(placedAlumId)){
            log.error(LoggerUtil.buildLog(PLACED_ALUM,DELETE,"Placed Alum not found with Id- " + placedAlumId, username, (System.currentTimeMillis() - startTime) / 1000, FAIL));
            throw new ResourceNotFoundException("Placed Alum not found with Id: " + placedAlumId);
        }

        placedAlumRepository.deleteById(placedAlumId);

        endTime = System.currentTimeMillis();
        duration = (endTime - startTime) / 1000;
        log.info(LoggerUtil.buildLog(PLACED_ALUM,DELETE,"Id- " + placedAlumId, username, duration, SUCCESS));
    }
}
