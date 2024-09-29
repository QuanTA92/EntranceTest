package com.fpt.EntranceTest.service;

import com.fpt.EntranceTest.dto.request.ColorRequest;
import com.fpt.EntranceTest.dto.response.ColorResponse;

import java.util.List;

public interface ColorService {

    boolean addColor(ColorRequest colorRequest);

    boolean updateColor(int idColor, ColorRequest colorRequest);

    boolean deleteColor(int idColor);

    List<ColorResponse> getAllColors();

    List<ColorResponse> getColorsById(int idColor);

}
