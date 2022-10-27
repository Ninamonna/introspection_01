package org.example.introspection.controller;

import org.example.introspection.domain.Note;
import org.example.introspection.domain.User;
import org.example.introspection.repo.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.Set;


@Controller
public class NoteController {

    @Autowired
    private NoteRepo noteRepo;

    @GetMapping("/userNotes")
    public String notes(
            @RequestParam(required = false, defaultValue = "")
            @DateTimeFormat(pattern="yyyy-MM-dd") Date filter,
            Model model,
            @PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<Note> page;

        if (filter != null && !filter.toString().isEmpty()) {
            page = noteRepo.findByDate(filter, pageable);
        } else {
            page = noteRepo.findAll(pageable);
        }

        model.addAttribute("page", page);
        model.addAttribute("url", "/userNotes");
        model.addAttribute("filter", filter);

        return "userNotes";
    }

    @GetMapping("/user-notes/{user}")
    public String userNotes(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            @RequestParam(required = false, defaultValue = "")
            @DateTimeFormat(pattern="yyyy-MM-dd") Date filter,
            Model model,
            @RequestParam(required = false) Note note
    ) {
        Set<Note> notes = user.getNotes();

        model.addAttribute("note", note);
        model.addAttribute("isCurrentUser", currentUser.equals(user));
        model.addAttribute("filter", filter);

        return "userNotes";
    }

    @PostMapping("/user-notes/{user}")
    public String updateNote(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long user,
            @RequestParam("id") Note note,
            @RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
            @RequestParam("good") String good,
            @RequestParam("bad") String bad,
            @RequestParam("another") String another,
            @RequestParam("targetApproach") String targetApproach,
            @RequestParam("together") String together

    )  {

        if (note.getAuthor().equals(currentUser)) {

            if (!StringUtils.isEmpty(date)) {
                note.setDate(date);
            }

            if (!StringUtils.isEmpty(good)) {
                note.setGood(good);
            }

            if (!StringUtils.isEmpty(bad)) {
                note.setBad(bad);
            }

            if (!StringUtils.isEmpty(another)) {
                note.setAnother(another);
            }

            if (!StringUtils.isEmpty(targetApproach)) {
                note.setTargetApproach(targetApproach);
            }

            if (!StringUtils.isEmpty(together)) {
                note.setTogether(together);
            }

            noteRepo.save(note);
        }

        return "redirect:/user-notes/" + user;
    }

    @GetMapping("/del-user-note/{user}")
    public String deleteNote(
            @PathVariable Long user,
            @RequestParam("note") Long noteId
    ) throws IOException {

        noteRepo.deleteById(noteId);

        return "redirect:/user-note/" + user;
    }

}

