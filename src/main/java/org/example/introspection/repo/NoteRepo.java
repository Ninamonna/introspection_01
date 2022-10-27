package org.example.introspection.repo;

import org.example.introspection.domain.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface NoteRepo extends CrudRepository<Note, Long> {

    Page<Note> findByDate(Date date, Pageable pageable);
    Page<Note> findAll(Pageable pageable);

}
