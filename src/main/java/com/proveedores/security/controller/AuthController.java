package com.proveedores.security.controller;

import com.proveedores.catalogos.TipoRolEnum;
import com.proveedores.security.dto.JwtDto;
import com.proveedores.security.dto.LoginUsuarioDto;
import com.proveedores.security.dto.NuevoUsuarioDto;
import com.proveedores.security.entity.Rol;
import com.proveedores.security.entity.RolTipo;
import com.proveedores.security.entity.Usuario;
import com.proveedores.security.jwt.JwtProvider;
import com.proveedores.security.service.RolService;
import com.proveedores.security.service.UsuarioService;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private RolService rolService;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody NuevoUsuarioDto nuevoUsuarioDto, BindingResult bindingResult) {
        //Verificar si hay errorres.
        if (bindingResult.hasErrors()) {
            return new ResponseEntity("Datos incorrectos.", HttpStatus.BAD_REQUEST);
        }
        //Verificar si ya existe el nombre de usuario.
        if (usuarioService.existsByUserName(nuevoUsuarioDto.getUserName())) {
            return new ResponseEntity("El nombre de usuario ya existe.", HttpStatus.BAD_REQUEST);
        }
        //Verificar si ya existe el email.
        if (usuarioService.existsByEmail(nuevoUsuarioDto.getEmail())) {
            return new ResponseEntity("El email ya existe.", HttpStatus.BAD_REQUEST);
        }
        //Crear el objeto de usuario a guardar
        //Codificar contraseña
        Usuario usuario = new Usuario(nuevoUsuarioDto.getUserName(), passwordEncoder.encode(nuevoUsuarioDto.getPassword()), nuevoUsuarioDto.getEmail(), nuevoUsuarioDto.getNombre(), nuevoUsuarioDto.getApellidoPaterno(), nuevoUsuarioDto.getApellidoMaterno(), new Date(), 1);
        //Asignar los roles
        Set<Rol> roles = new HashSet<>();
        roles.addAll(rolService.findByRolTipo(new RolTipo(TipoRolEnum.ADMINISTRADOR.value())));
        usuario.setRoles(roles);
        //Guardar usuario con sus roles
        usuarioService.save(usuario);

        return new ResponseEntity("Usuario creado.", HttpStatus.CREATED);
    }

    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuarioDto loginUsuarioDto, BindingResult bindingResult) {
        //Verificar si hay errorres.
        if (bindingResult.hasErrors()) {
            return new ResponseEntity("Datos incorrectos.", HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuarioDto.getUserName(), loginUsuarioDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}
