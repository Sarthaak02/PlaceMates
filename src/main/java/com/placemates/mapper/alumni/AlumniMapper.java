package com.placemates.mapper.alumni;

import com.placemates.dao.alumni.AlumniDAO;
import com.placemates.dto.alumni.AlumniDTO;

public class AlumniMapper {

    public static AlumniDAO mapAlumniDTOToAlumniDAO(AlumniDTO alumniDTO){
        AlumniDAO alumniDAO = new AlumniDAO();
        alumniDAO.setId(alumniDTO.getId());
        alumniDAO.setFirstName(alumniDTO.getFirstName());
        alumniDAO.setLastName(alumniDTO.getLastName());
        alumniDAO.setBranch(alumniDTO.getBranch());
        alumniDAO.setGraduationYear(alumniDTO.getGraduationYear());
        alumniDAO.setCompanyName(alumniDTO.getCompanyName());
        alumniDAO.setDesignation(alumniDTO.getDesignation());
        alumniDAO.setCtc(alumniDTO.getCtc());
        alumniDAO.setPhoneNumber(alumniDTO.getPhoneNumber());
        alumniDAO.setEmail(alumniDTO.getEmail());
        alumniDAO.setSocialLink(alumniDTO.getSocialLink());
        return alumniDAO;
    }

    public static AlumniDTO mapAlumniDAOToAlumniDTO(AlumniDAO alumniDAO){
        AlumniDTO alumniDTO = new AlumniDTO();
        alumniDTO.setId(alumniDAO.getId());
        alumniDTO.setFirstName(alumniDAO.getFirstName());
        alumniDTO.setLastName(alumniDAO.getLastName());
        alumniDTO.setBranch(alumniDAO.getBranch());
        alumniDTO.setGraduationYear(alumniDAO.getGraduationYear());
        alumniDTO.setCompanyName(alumniDAO.getCompanyName());
        alumniDTO.setDesignation(alumniDAO.getDesignation());
        alumniDTO.setCtc(alumniDAO.getCtc());
        alumniDTO.setPhoneNumber(alumniDAO.getPhoneNumber());
        alumniDTO.setEmail(alumniDAO.getEmail());
        alumniDTO.setSocialLink(alumniDAO.getSocialLink());
        return alumniDTO;
    }
}
