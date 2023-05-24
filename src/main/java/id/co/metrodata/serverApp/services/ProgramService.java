package id.co.metrodata.serverApp.services;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.metrodata.serverApp.models.Program;
import id.co.metrodata.serverApp.repositories.ProgramRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProgramService {
    private ProgramRepository programRepository;

    public List<Program> getAll() {
        return programRepository.findAll();
    }

    public Program getById(Long id) {
        return programRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Program id not found"));
    }

    public Program create(Program program) {
        if (programRepository.existsByName(program.getName())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Program name is already exists!"
            );
        }
        return programRepository.save(program);
    }

    public Program update(Long id, Program program) {
        Program programOld = getById(id);
        for (Program programCheck : getAll()) {
            if (!Objects.equals(programCheck.getId(), programOld.getId())) {
                if (Objects.equals(programCheck.getName(), program.getName())) {
                    throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Program name is already exists!"
                    );
                }
            }
        }
        program.setId(id);
        return programRepository.save(program);
    }

    public Program delete(Long id) {
        Program program = getById(id);
        programRepository.delete(program);
        return program;
    }
}
