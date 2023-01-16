package com.example.demo.repo;

import com.example.demo.models.Compilation;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CompilationRepository extends PagingAndSortingRepository<Compilation,Long> {
    Compilation findByTitle(String title);
}
