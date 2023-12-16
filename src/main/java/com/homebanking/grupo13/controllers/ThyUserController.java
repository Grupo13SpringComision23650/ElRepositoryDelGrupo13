package com.homebanking.grupo13.controllers;

import com.homebanking.grupo13.services.AccountService;
import com.homebanking.grupo13.services.UserService;

import jakarta.validation.Valid;

import com.homebanking.grupo13.entities.dtos.AccountDto;
import com.homebanking.grupo13.entities.dtos.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


// no se utiliza RestController solo controller para el front por eso tenemos un control para el front separado
@Controller
public class ThyUserController {

  @Autowired
  public UserService userService;

  @Autowired
  public AccountService accountService;


    // pagina inicio
    @RequestMapping("/")
    public String inicio(){
    return "home";
    }

    // Mostrar datos de usuario creado   
    @GetMapping("/show/{id}")
    public String showUser(@PathVariable("id") Long userId, @RequestParam(value = "message", required = false) String message, Model model) {
        // Lógica para obtener el usuario con el userId y preparar los datos del usuario
        UserDto userDto = userService.getUserById(userId);
        model.addAttribute("user", userDto);
    
        // Pasar el mensaje al modelo si está presente en la URL
        if (message != null && !message.isEmpty()) {
            model.addAttribute("successMessage", message);
        }    
        return "showUser";
    }

    
    // Crear un nuevo Usuario y cta por defecto
    @GetMapping("/create")
    public String createUserForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "createUser"; // Nombre de la página Thymeleaf para crear usuario
    }

    // Guardamos los datos del nuevo usuario
    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") @Valid UserDto userDto, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "createUser"; // Volver al formulario si hay errores
        }
        
        UserDto createdUser = userService.createUser(userDto);
        if (createdUser != null && createdUser.getId() != null) {
            // Si el usuario se creó correctamente y tiene un ID válido
            redirectAttributes.addAttribute("id", createdUser.getId()); // Agregar el ID como atributo de redirección
            redirectAttributes.addFlashAttribute("successMessage","Usuario y Cuenta por Defecto creadas correctamente");
            return "redirect:/show/{id}"; // Redirigir a la página del usuario creado
        } else {
            // Manejar el caso donde no se pudo crear el usuario
            return "redirect:/error";
        }
    }

    // controlador que envia a pagina de error si el user existe
    @GetMapping("/error")
    public String error(){
        return "error_user";        
    }

    // Actualizar solo el usuario
    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") @Valid UserDto userDto, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "showUser"; // Volver a la página de detalles si hay errores
        }
    
        UserDto updatedUser = userService.updateUser(userDto); // Actualizar usuario
    
        if (updatedUser != null && updatedUser.getId() != null) {
            redirectAttributes.addAttribute("id", updatedUser.getId()); // Agregar el ID como atributo de redirección
            redirectAttributes.addFlashAttribute("successMessage", "Usuario actualizado correctamente"); // Mensaje de éxito
            return "redirect:/show/{id}"; // Redirigir a la página de detalles del usuario actualizado
        } else {
            // Manejar el caso donde no se pudo actualizar el usuario
            // Puedes redirigir a una página de error o a donde sea apropiado
            return "redirect:/error";
        }
    }

    //listar todos los registros
    @GetMapping("/list")
    public String listar(Model model){
    List<UserDto> ulist = userService.getUsers();
    model.addAttribute("ulist", ulist);
    return "userlist";
    }

    // listar registros con boton de ver
    @GetMapping("/busca")
    public String veruser(Model model){
    List<UserDto> ulist = userService.getUsers();
    model.addAttribute("listu", ulist);
    return "buscar";
    }

}
