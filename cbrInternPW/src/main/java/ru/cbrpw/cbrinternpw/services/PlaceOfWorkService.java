package ru.cbrpw.cbrinternpw.services;
import org.springframework.beans.factory.annotation.Autowired;
import ru.cbrpw.cbrinternpw.dao.PlaceOfWorkDAO;
import ru.cbrpw.cbrinternpw.model.PlaceOfWork;
import org.springframework.stereotype.Service;
import ru.cbrpw.cbrinternpw.repository.PlaceOfWorkRepository;

import java.util.List;

@Service
public class PlaceOfWorkService {

    private PlaceOfWorkDAO placeOfWorkDAO;
    private PlaceOfWorkRepository pow;

    public void savePlaceOfWork(PlaceOfWork placeOfWork) {
        placeOfWorkDAO.save(placeOfWork);
    }

    public List <PlaceOfWork> getAllPlaceOfWork() {
        return placeOfWorkDAO.getAllFromPlaceOfWork();
    }

    @Autowired
    public PlaceOfWorkService(PlaceOfWorkRepository pows) {
        this.pow = pows;
    }
}

