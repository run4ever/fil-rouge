package fr.epita.filrouge.exposition.controller;

import fr.epita.filrouge.application.person.AppUserDto;
import fr.epita.filrouge.application.person.AppUserLightDto;
import fr.epita.filrouge.application.person.AppUserService;
import fr.epita.filrouge.domain.exception.AlreadyExistingException;
import fr.epita.filrouge.domain.exception.ErrorCodes;
import fr.epita.filrouge.exposition.exception.ErrorModel;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/appuser")
@Api(value="Controller REST for AppUser")
@Validated
public class AppUserResource {

    @Autowired
    private AppUserService appUserService;


    @GetMapping(value = "/{email}", produces = {"application/json"})
    @ApiOperation(value = "Show one user detail by email")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ErrorModel.class),
            @ApiResponse(code = 404, message = "User not exists", response = ErrorModel.class),
            @ApiResponse(code = 500, message = "Interne error, try again please", response = ErrorModel.class)
    })
    public AppUserLightDto getAppUser(@ApiParam(value="email", required = true) @PathVariable("email") final String emailAppUser) {
        return appUserService.getAppUser(emailAppUser);
    }


    @PostMapping(value = "/add",produces = {"application/json"}, consumes = {"application/json"})
    @ApiOperation(value = "Create one user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "AppUser is created", response = ErrorModel.class),
            @ApiResponse(code = 400, message ="Bad request", response = ErrorModel.class),
            @ApiResponse(code = 409, message = "Conflict", response = ErrorModel.class),
            @ApiResponse(code = 500, message = "Impossible to create AppUser", response = ErrorModel.class)
    })
    public ResponseEntity<?> addAppUser(@Valid @RequestBody final AppUserDto appUserDto){

        appUserService.create(appUserDto);

        final URI location = ServletUriComponentsBuilder
                                .fromCurrentRequest()
                                .path("/{email}")
                                .buildAndExpand(appUserDto.getEmail())
                                .toUri();
        return ResponseEntity.created(location).build();
    }
}
