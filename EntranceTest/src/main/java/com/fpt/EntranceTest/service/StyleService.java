package com.fpt.EntranceTest.service;

import com.fpt.EntranceTest.dto.request.StyleRequest;
import com.fpt.EntranceTest.dto.response.StyleResponse;

import java.util.List;

public interface StyleService {

    boolean addStyle(StyleRequest styleRequest);

    boolean updateStyle(int idStyle, StyleRequest styleRequest);

    boolean deleteStyle(int idStyle);

    List<StyleResponse> getAllStyles();

    List<StyleResponse> getStylesById(int idStyle);

}
