package com.parachutes.hateoasspring.controller;

import com.parachutes.hateoasspring.model.Users;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/rest/users")
public class UsersController {
    @GetMapping("/all")
    public List<Users> getAll(){
        Users users1 = new Users("Rodolfo", 2300L);
        Users users2 = new Users("Max", 2500L);
        return Arrays.asList(users1, users2);
    }

    @GetMapping(value = "/hateoas/all", produces = MediaTypes.HAL_JSON_VALUE)
    public List<Users> getHateoasgetAll(){
        Users users1 = new Users("Rodolfo", 2300L);
        Link link = ControllerLinkBuilder.linkTo(UsersController.class)
                .slash(users1.getName()).withSelfRel();
        Link link2 = ControllerLinkBuilder.linkTo(UsersController.class)
                .slash(users1.getSalary()).withRel("salary");
        users1.add(link, link2);
        Users users2 = new Users("Max", 2500L);
        users2.add(ControllerLinkBuilder.linkTo(UsersController.class)
                .slash(users1.getSalary()).withRel("salary"));
        return Arrays.asList(users1, users2);
    }
}
