package com.fpt.EntranceTest.service;

import com.fpt.EntranceTest.dto.request.SizeRequest;
import com.fpt.EntranceTest.dto.response.SizeResponse;

import java.util.List;

public interface SizeService {

    boolean addSize(SizeRequest sizeRequest);

    boolean deleteSize(int idSize);

    boolean updateSize(int idSize ,SizeRequest sizeRequest);

    List<SizeResponse> getAllSize();

    List<SizeResponse> getAllSizeById(int idSize);
}
