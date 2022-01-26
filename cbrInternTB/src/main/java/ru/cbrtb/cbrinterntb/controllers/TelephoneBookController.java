package ru.cbrtb.cbrinterntb.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.cbrtb.cbrinterntb.api.StringResponse;
import ru.cbrtb.cbrinterntb.dao.TelephoneBookDAO;
import ru.cbrtb.cbrinterntb.model.TelephoneBook;
import ru.cbrtb.cbrinterntb.services.TelephoneBookService;

@RestController
@RequestMapping(value = "/tb")
public class TelephoneBookController {
        private final TelephoneBookService telephoneBookService;

        public TelephoneBookController(TelephoneBookService telephoneBookService) {
            this.telephoneBookService = telephoneBookService;
        }

        @RequestMapping(value = "add", method = RequestMethod.POST, produces = "application/json")
        public ResponseEntity<StringResponse> addUser (@RequestBody TelephoneBook telephoneBook) {
            StringResponse JSONName =telephoneBookService.addNewRecord(telephoneBook) ;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(JSONName, headers, HttpStatus.OK);
        }

        @RequestMapping(value = "move", method = RequestMethod.POST, produces = "application/json")
        public ResponseEntity<Boolean> sendUser (@RequestBody StringResponse JSONName) {
            Boolean result = telephoneBookService.moveOrDeleteRecord(JSONName, true);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(result, headers, HttpStatus.OK);
        }

        @RequestMapping(value = "cancel", method = RequestMethod.POST, produces = "application/json")
        public ResponseEntity<Boolean> cancelUser (@RequestBody StringResponse JSONName) {
            Boolean result = telephoneBookService.moveOrDeleteRecord(JSONName, false);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(result, headers, HttpStatus.OK);
        }

        @ResponseBody
        @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
        public ModelAndView getPage () {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("add.html");
            return modelAndView;
        }
}
