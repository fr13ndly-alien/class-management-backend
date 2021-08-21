package com.cm.core.service;

import com.cm.core.entity.Centre;
import com.cm.core.repository.CentreRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CentreService {

    @Autowired
    private CentreRepository centreRepository;

    public Optional<Centre> findById(String id) {
        return findById(new ObjectId(id));
    }

    public Optional<Centre> findById(ObjectId id) {
        return centreRepository.findById(id);
    }

    public List<Centre> getAll() { return centreRepository.findAll(); }

    public Centre create(Centre centre) {
        Optional<Centre> existingCentre = centreRepository.findByName(centre.getName());
        if (existingCentre.isPresent())
            throw new IllegalStateException("Centre named " + centre.getName() + " has been taken");
        return centreRepository.save(centre);
    }

    public Centre update(String id, Centre centre) {
        Optional<Centre> existingCentre = findById(id);
        if (!existingCentre.isPresent())
            throw new IllegalStateException("Centre id: " + id + " not existing.");

        return centreRepository.save(centre);
    }

    public void removeCentre(String id) {
        Optional<Centre> centreOpt = findById(id);
        if (!centreOpt.isPresent())
            throw new IllegalStateException("Centre id: " + id + " not existing.");

        centreRepository.delete(centreOpt.get());
    }
}
