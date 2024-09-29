package com.fpt.EntranceTest.controller;

import com.fpt.EntranceTest.dto.request.SizeRequest;
import com.fpt.EntranceTest.dto.response.SizeResponse;
import com.fpt.EntranceTest.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/size")
public class SizeController {

    @Autowired
    private SizeService sizeService;

    @PostMapping("/add")
    public ResponseEntity<?> addSize(@RequestBody SizeRequest sizeRequest) {
        boolean isAdded = sizeService.addSize(sizeRequest);
        if (isAdded) {
            return ResponseEntity.ok("Size added successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add size.");
        }
    }

    @DeleteMapping("/delete/{idSize}")
    public ResponseEntity<?> deleteSize(@PathVariable int idSize) {
        boolean isDeleted = sizeService.deleteSize(idSize);
        if (isDeleted) {
            return ResponseEntity.ok("Size deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Size not found or delete failed.");
        }
    }

    @PutMapping("/update/{idSize}")
    public ResponseEntity<?> updateSize(@PathVariable int idSize, @RequestBody SizeRequest sizeRequest) {
        boolean isUpdated = sizeService.updateSize(idSize, sizeRequest);
        if (isUpdated) {
            return ResponseEntity.ok("Size updated successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Size not found or update failed.");
        }
    }

    @GetMapping("")
    public ResponseEntity<List<SizeResponse>> getAllSizes() {
        List<SizeResponse> sizes = sizeService.getAllSize();
        if (sizes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(sizes);
        }
        return new ResponseEntity<>(sizes, HttpStatus.OK);
    }

    @GetMapping("/{idSize}")
    public ResponseEntity<List<SizeResponse>> getSizeById(@PathVariable int idSize) {
        List<SizeResponse> sizeResponses = sizeService.getAllSizeById(idSize);
        if (sizeResponses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(sizeResponses);
        }
        return new ResponseEntity<>(sizeResponses, HttpStatus.OK);
    }
}
