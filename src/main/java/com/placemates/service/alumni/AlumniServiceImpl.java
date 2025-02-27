package com.placemates.service.alumni;

import com.placemates.dao.alumni.AlumniDAO;
import com.placemates.dto.alumni.AlumniDTO;
import com.placemates.exception.DataAlreadyExistsException;
import com.placemates.exception.DataNotFoundException;
import com.placemates.mapper.alumni.AlumniMapper;
import com.placemates.repository.alumni.AlumniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AlumniServiceImpl implements AlumniService {

    @Autowired
    private AlumniRepository alumniRepository;

    @Override
    public void createAlumni(AlumniDTO alumniDTO) throws DataAlreadyExistsException {
        if (alumniRepository.existsByEmail(alumniDTO.getEmail())) {
            throw new DataAlreadyExistsException("Alumni with email '" + alumniDTO.getEmail() + "' already exists.");
        }
        AlumniDAO alumniDAO = AlumniMapper.mapAlumniDTOToAlumniDAO(alumniDTO);
        alumniRepository.save(alumniDAO);
    }

    @Override
    public List<AlumniDTO> getAlumni() throws DataNotFoundException {
        List<AlumniDAO> alumniDAOList = alumniRepository.findAll();

        if (alumniDAOList.isEmpty()) {
            throw new DataNotFoundException("No alumni records found.");
        }

        List<AlumniDTO>alumniDTOList = new ArrayList<>();
        for (AlumniDAO alumniDAO : alumniDAOList) {
            alumniDTOList.add(AlumniMapper.mapAlumniDAOToAlumniDTO(alumniDAO));
        }

        return alumniDTOList;
    }

    @Override
    public void updateAlumni(Integer id, AlumniDTO alumniDTO) throws DataNotFoundException, DataAlreadyExistsException {
        AlumniDAO alumniDAO = alumniRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Alumni with ID " + id + " not found"));

        if (!alumniDTO.getEmail().equals(alumniDAO.getEmail())) {
            if (alumniRepository.existsByEmail(alumniDTO.getEmail())) {
                throw new DataAlreadyExistsException("Email '" + alumniDTO.getEmail() + "' is already in use by another alumni.");
            }
            alumniDAO.setEmail(alumniDTO.getEmail());
        }

        if (alumniDTO.getBranch() != null) {
            alumniDAO.setBranch(alumniDTO.getBranch());
        }

        if (alumniDTO.getCompanyName() != null) {
            alumniDAO.setCompanyName(alumniDTO.getCompanyName());
        }

        if (alumniDTO.getCtc() != null) {
            alumniDAO.setCtc(alumniDTO.getCtc());
        }

        if (alumniDTO.getDesignation() != null) {
            alumniDAO.setDesignation(alumniDTO.getDesignation());
        }

        if (alumniDTO.getFirstName() != null) {
            alumniDAO.setFirstName(alumniDTO.getFirstName());
        }

        if (alumniDTO.getGraduationYear() != null) {
            alumniDAO.setGraduationYear(alumniDTO.getGraduationYear());
        }

        if (alumniDTO.getLastName() != null) {
            alumniDAO.setLastName(alumniDTO.getLastName());
        }

        if (alumniDTO.getPhoneNumber() != null) {
            alumniDAO.setPhoneNumber(alumniDTO.getPhoneNumber());
        }

        if (alumniDTO.getSocialLink() != null) {
            alumniDAO.setSocialLink(alumniDTO.getSocialLink());
        }

        alumniRepository.save(alumniDAO);
    }


    @Override
    public void deleteAlumni(Integer id) throws DataNotFoundException {
        alumniRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Alumni with ID '" + id + "' not found"));
        alumniRepository.deleteById(id);
    }
}
