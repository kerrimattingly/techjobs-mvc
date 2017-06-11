package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value="results")
    public String search(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        //if searchType = all
        ArrayList<HashMap<String, String>> jobs;
        if (searchType.equals("all")) {
            //then search using findAll passing in searchTerm
            jobs = JobData.findByValue(searchTerm);
        } else {
            //else continue to use rest of code
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("title", jobs.size() + " Result(s)");
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("jobs", jobs);
        return "search";
    }
}
