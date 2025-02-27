package com.placemates.service.alumni;

import com.placemates.dto.alumni.AlumniDTO;
import com.placemates.exception.DataAlreadyExistsException;
import com.placemates.exception.DataNotFoundException;

import java.util.List;

public interface AlumniService {

    public void createAlumni(AlumniDTO alumniDTO) throws DataAlreadyExistsException;
    public List<AlumniDTO> getAlumni() throws DataNotFoundException;
    public void updateAlumni(Integer id, AlumniDTO alumniDTO) throws DataNotFoundException, DataAlreadyExistsException;
    public void deleteAlumni(Integer id) throws DataNotFoundException;
}
