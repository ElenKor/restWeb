package com.example.demo.services;

import com.example.demo.models.Compilation;
import com.example.demo.repo.CompilationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompilationService {

    private final CompilationRepository compilationRepository;

    @Transactional
    public List<Compilation> findAll() {
        List<Compilation> compilations = new ArrayList<>();
        compilationRepository.findAll().forEach(compilations::add);
        return compilations;
    }

    @Transactional
    public Page<Compilation> findAll(int page, int size) {
        return compilationRepository.findAll(PageRequest.of(page, size, Sort.by("title")));
    }

    @Transactional
    public Compilation save(Compilation compilation) {
        return compilationRepository.save(compilation);
    }

    @Transactional
    public Optional<Compilation> findById(long id) {
        return  compilationRepository.findById(id);
    }

    @Transactional
    public boolean existsById(long id) {
        return compilationRepository.existsById(id);
    }

    @Transactional
    public void delete(Compilation compilation) {
        compilationRepository.delete(compilation);
    }

    @Transactional
    public Compilation findByTitle(String title){
        return compilationRepository.findByTitle(title);
    }
}
