package fr.epita.filrouge.exposition.api.person;

import fr.epita.filrouge.application.person.AppUserDto;
import fr.epita.filrouge.application.person.AppUserLightDto;
import fr.epita.filrouge.application.person.AppUserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
            @ApiResponse(code = 200, message = "OK", response = AppUserLightDto.class),
            @ApiResponse (code = 404, message = "Resource not exists", response = AppUserLightDto.class),
            @ApiResponse (code = 500, message = "Interne error, try again please", response = AppUserLightDto.class)
    })
    public AppUserLightDto getAppUser(@ApiParam(value="email", required = true) @PathVariable("email") final String emailAppUser) {
        return appUserService.getAppUser(emailAppUser);
    }


    @PostMapping(value = "/add",produces = {"application/json"}, consumes = {"application/json"})
    @ApiOperation(value = "Create one user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "AppUser is created", response = AppUserDto.class),
            @ApiResponse (code = 500, message = "Impossible to create AppUser", response = AppUserDto.class)
    })
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
