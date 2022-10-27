package org.example.introspection.controller;

import org.example.introspection.domain.Note;
import org.example.introspection.domain.User;
import org.example.introspection.repo.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;


@Controller
public class MainController {
    @Autowired
    private NoteRepo noteRepo;

    @PostMapping("/main")
    public String addNewNote(
            @AuthenticationPrincipal User currentUser,
            @Valid Note note,
            BindingResult bindingResult,
            Model model) {

        note.setAuthor(currentUser);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("note", note);

            return "main";

        } else {

            model.addAttribute("note", null);
            noteRepo.save(note);
        }
        Iterable<Note> notes = noteRepo.findAll();
        model.addAttribute("notes", notes);
        return "save";
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        model.put("greeting", "Добро пожаловать в Дневник Самоанализа");
        return "greeting";
    }

    @GetMapping("/main")
    public String main(
            @AuthenticationPrincipal User user,
            Model model) {
        model.addAttribute("user", user.getUsername());
        return "main";
    }
}
