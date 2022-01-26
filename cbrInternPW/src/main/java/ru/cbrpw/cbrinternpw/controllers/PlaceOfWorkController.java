package ru.cbrpw.cbrinternpw.controllers;

import org.springframework.ui.Model;
import ru.cbrpw.cbrinternpw.model.PlaceOfWork;
import ru.cbrpw.cbrinternpw.services.PlaceOfWorkService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(value = "/pow")
public class PlaceOfWorkController {
    private final PlaceOfWorkService placeOfWorkService;

    public PlaceOfWorkController(PlaceOfWorkService placeOfWorkService) {
        this.placeOfWorkService = placeOfWorkService;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> addUser(@RequestBody PlaceOfWork pow) {
        placeOfWorkService.savePlaceOfWork(pow);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add.html");
        return modelAndView;
    }

    @RequestMapping(value = "getAll", method = RequestMethod.POST)
    public ResponseEntity<List> getAll() {
        List <PlaceOfWork> list = placeOfWorkService.getAllPlaceOfWork();
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(list, headers, HttpStatus.OK);
    }
}

