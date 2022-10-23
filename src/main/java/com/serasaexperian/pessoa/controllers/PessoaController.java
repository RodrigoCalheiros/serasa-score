package com.serasaexperian.pessoa.controllers;

import com.serasaexperian.pessoa.dtos.request.PessoaRequestDto;
import com.serasaexperian.pessoa.dtos.response.PessoaResponseDto;
import com.serasaexperian.pessoa.models.PessoaModel;
import com.serasaexperian.pessoa.services.PessoaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/pessoa")
public class PessoaController {

    final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<Object> savePessoa(@RequestBody @Valid final PessoaRequestDto pessoaRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.save(pessoaRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<PessoaResponseDto>> getAllPessoas() {
        List<PessoaResponseDto> allPessoas = pessoaService.findAll();
        if (allPessoas.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(allPessoas);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPessoa(@PathVariable(value = "id") final UUID id) {
        Optional<PessoaResponseDto> pessoaModelOptional = pessoaService.findById(id);
        if (!pessoaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(pessoaModelOptional.get());
    }
}