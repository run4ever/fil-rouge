package fr.epita.filrouge.exposition.person;

import fr.epita.filrouge.application.person.AppUserDto;
import fr.epita.filrouge.application.person.AppUserLightDto;
import fr.epita.filrouge.application.person.AppUserService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
@Validated
public class AppUserResource {

    @Autowired
    private AppUserService appUserService;

    @GetMapping(value = "/appuser/{email}", produces = {"application/json"})
    public AppUserLightDto getAppUser(@ApiParam(value="email", required = true) @PathVariable("email") final String emailAppUser) {
        return appUserService.getAppUser(emailAppUser);
    }

    @PostMapping(value = "/appuser/add",produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> addAppUser(@RequestBody final AppUserDto appUserDto){
        appUserService.create(appUserDto);
        final URI location = ServletUriComponentsBuilder
                                .fromCurrentRequest()
                                .path("/{email}")
                                .buildAndExpand(appUserDto.getEmail())
                                .toUri();
        return ResponseEntity.created(location).build();
    }
}
