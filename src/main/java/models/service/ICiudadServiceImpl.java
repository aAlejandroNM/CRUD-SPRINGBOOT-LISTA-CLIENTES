package models.service;

import models.entity.Ciudad;
import models.repository.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ICiudadServiceImpl implements ICiudadService {
    @Autowired
    private CiudadRepository ciudadRepository;

    @Override
    public List<Ciudad> listaCiudades() {
        return (List<Ciudad>) ciudadRepository.findAll();
    }
}
