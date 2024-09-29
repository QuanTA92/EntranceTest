package com.fpt.EntranceTest.service.imp;

import com.fpt.EntranceTest.dto.request.StyleRequest;
import com.fpt.EntranceTest.dto.response.StyleResponse;
import com.fpt.EntranceTest.modal.Style;
import com.fpt.EntranceTest.repository.StyleRepository;
import com.fpt.EntranceTest.service.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StyleServiceImpl implements StyleService {

    @Autowired
    private StyleRepository styleRepository;

    @Override
    public boolean addStyle(StyleRequest styleRequest) {
        Style style = new Style();
        style.setName(styleRequest.getName());
        style.setCreateDate(new Date());
        styleRepository.save(style);
        return true;
    }

    @Override
    public boolean updateStyle(int idStyle, StyleRequest styleRequest) {
        if (!styleRepository.existsById(idStyle)) {
            return false; // Style not found
        }
        Style style = styleRepository.findById(idStyle).orElseThrow();
        style.setName(styleRequest.getName());
        style.setCreateDate(new Date());
        styleRepository.save(style);
        return true;
    }

    @Override
    public boolean deleteStyle(int idStyle) {
        if (!styleRepository.existsById(idStyle)) {
            return false;
        }
        styleRepository.deleteById(idStyle);
        return true;
    }

    @Override
    public List<StyleResponse> getAllStyles() {
        List<Style> styles = styleRepository.findAll();
        List<StyleResponse> styleResponses = new ArrayList<>();

        for (Style style : styles) {
            StyleResponse styleResponse = new StyleResponse();
            styleResponse.setName(style.getName());
            styleResponses.add(styleResponse);
        }

        return styleResponses;
    }

    @Override
    public List<StyleResponse> getStylesById(int idStyle) {
        List<StyleResponse> styleResponses = new ArrayList<>();
        if (styleRepository.existsById(idStyle)) {
            Style style = styleRepository.findById(idStyle).orElseThrow();
            StyleResponse styleResponse = new StyleResponse();
            styleResponse.setName(style.getName());
            styleResponses.add(styleResponse);
        }
        return styleResponses;
    }
}
