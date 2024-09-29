package com.fpt.EntranceTest.controller;

import com.fpt.EntranceTest.dto.request.StyleRequest;
import com.fpt.EntranceTest.dto.response.StyleResponse;
import com.fpt.EntranceTest.service.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/style")
public class StyleController {

    @Autowired
    private StyleService styleService;

    @PostMapping("/add")
    public ResponseEntity<String> addStyle(@RequestBody StyleRequest styleRequest) {
        if (styleService.addStyle(styleRequest)) {
            return ResponseEntity.ok("Style added successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add style.");
        }
    }

    @PutMapping("/update/{idStyle}")
    public ResponseEntity<String> updateStyle(@PathVariable int idStyle, @RequestBody StyleRequest styleRequest) {
        if (styleService.updateStyle(idStyle, styleRequest)) {
            return ResponseEntity.ok("Style updated successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Style not found.");
        }
    }

    @DeleteMapping("/delete/{idStyle}")
    public ResponseEntity<String> deleteStyle(@PathVariable int idStyle) {
        if (styleService.deleteStyle(idStyle)) {
            return ResponseEntity.ok("Style deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Style not found.");
        }
    }

    @GetMapping("")
    public ResponseEntity<List<StyleResponse>> getAllStyles() {
        List<StyleResponse> styles = styleService.getAllStyles();
        return ResponseEntity.ok(styles);
    }

    @GetMapping("/{idStyle}")
    public ResponseEntity<List<StyleResponse>> getStylesById(@PathVariable int idStyle) {
        List<StyleResponse> styles = styleService.getStylesById(idStyle);
        return ResponseEntity.ok(styles);
    }
}
