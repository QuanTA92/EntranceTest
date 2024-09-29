package com.fpt.EntranceTest.service.imp;

import com.fpt.EntranceTest.dto.request.ColorRequest;
import com.fpt.EntranceTest.dto.response.ColorResponse;
import com.fpt.EntranceTest.modal.Color;
import com.fpt.EntranceTest.repository.ColorRepository;
import com.fpt.EntranceTest.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public boolean addColor(ColorRequest colorRequest) {
        try {
            Color color = new Color();
            color.setName(colorRequest.getName());
            colorRepository.save(color);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateColor(int idColor, ColorRequest colorRequest) {
        try {
            Optional<Color> optionalColor = colorRepository.findById(idColor);
            if (optionalColor.isPresent()) {
                Color color = optionalColor.get();
                color.setName(colorRequest.getName());
                colorRepository.save(color);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteColor(int idColor) {
        try {
            Optional<Color> optionalColor = colorRepository.findById(idColor);
            if (optionalColor.isPresent()) {
                colorRepository.delete(optionalColor.get());
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<ColorResponse> getAllColors() {
        List<Color> colors = colorRepository.findAll();
        List<ColorResponse> colorResponses = new ArrayList<>();
        for (Color color : colors) {
            ColorResponse response = new ColorResponse();
            response.setName(color.getName());
            colorResponses.add(response);
        }
        return colorResponses;
    }

    @Override
    public List<ColorResponse> getColorsById(int idColor) {
        Optional<Color> color = colorRepository.findById(idColor);
        if (color.isPresent()) {
            ColorResponse response = new ColorResponse();
            response.setName(color.get().getName());
            return List.of(response);
        }
        return List.of();
    }
}
