package com.fpt.EntranceTest.controller;

import com.fpt.EntranceTest.dto.request.ColorRequest;
import com.fpt.EntranceTest.dto.response.ColorResponse;
import com.fpt.EntranceTest.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/color")
public class ColorController {

    @Autowired
    private ColorService colorService;

    @PostMapping("/add")
    public ResponseEntity<String> addColor(@RequestBody ColorRequest colorRequest) {
        boolean isAdded = colorService.addColor(colorRequest);
        if (isAdded) {
            return ResponseEntity.ok("Color added successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add color.");
        }
    }

    @PutMapping("/update/{idColor}")
    public ResponseEntity<String> updateColor(@PathVariable int idColor, @RequestBody ColorRequest colorRequest) {
        boolean isUpdated = colorService.updateColor(idColor, colorRequest);
        if (isUpdated) {
            return ResponseEntity.ok("Color updated successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Color not found or update failed.");
        }
    }

    @DeleteMapping("/delete/{idColor}")
    public ResponseEntity<String> deleteColor(@PathVariable int idColor) {
        boolean isDeleted = colorService.deleteColor(idColor);
        if (isDeleted) {
            return ResponseEntity.ok("Color deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Color not found or delete failed.");
        }
    }

    @GetMapping("")
    public ResponseEntity<List<ColorResponse>> getAllColors() {
        List<ColorResponse> colorResponses = colorService.getAllColors();
        return new ResponseEntity<>(colorResponses, HttpStatus.OK);
    }

    @GetMapping("/{idColor}")
    public ResponseEntity<List<ColorResponse>> getColorById(@PathVariable int idColor) {
        List<ColorResponse> colorResponses = colorService.getColorsById(idColor);
        if (!colorResponses.isEmpty()) {
            return new ResponseEntity<>(colorResponses, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(colorResponses);
        }
    }
}
