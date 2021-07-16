package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.BaseEntity;
import br.com.alelo.consumer.consumerpat.service.BaseService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Getter
@AllArgsConstructor
public class CrudBaseController<T extends BaseEntity, S extends BaseService> {

    private final S service;

    @ApiOperation(value = "Find all items")
    @GetMapping
    public Page<T> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @ApiOperation(value = "Find item by id")
    @GetMapping("/{id}")
    public ResponseEntity<T> findById(@PathVariable Long id) {
        Optional<T> result = service.findById(id);
        return ResponseEntity.of(result);
    }

    @ApiOperation(value = "Delete item by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<T> result = service.findById(id);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Update item")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody T entity) {
        Optional<T> result = service.findById(id);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.update(id, entity);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Save item")
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody T entity) {
        service.save(entity);
        return ResponseEntity.ok().build();
    }
}
