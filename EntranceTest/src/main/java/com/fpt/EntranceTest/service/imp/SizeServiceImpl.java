package com.fpt.EntranceTest.service.imp;

import com.fpt.EntranceTest.dto.request.SizeRequest;
import com.fpt.EntranceTest.dto.response.SizeResponse;
import com.fpt.EntranceTest.modal.Size;
import com.fpt.EntranceTest.repository.SizeRepository;
import com.fpt.EntranceTest.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public boolean addSize(SizeRequest sizeRequest) {
        try {
            Size size = new Size();
            size.setName(sizeRequest.getName());
            sizeRepository.save(size);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteSize(int idSize) {
        try {
            Optional<Size> sizeOptional = sizeRepository.findById(idSize);
            if (sizeOptional.isPresent()) {
                sizeRepository.delete(sizeOptional.get());
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateSize(int idSize, SizeRequest sizeRequest) {
        try {
            Optional<Size> sizeOptional = sizeRepository.findById(idSize);
            if (sizeOptional.isPresent()) {
                Size size = sizeOptional.get();
                size.setName(sizeRequest.getName());
                sizeRepository.save(size);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<SizeResponse> getAllSize() {
        List<Size> sizes = sizeRepository.findAll();
        List<SizeResponse> sizeResponses = new ArrayList<>();
        for (Size size : sizes) {
            SizeResponse sizeResponse = new SizeResponse();
            sizeResponse.setName(size.getName());
            sizeResponses.add(sizeResponse);
        }
        return sizeResponses;
    }

    @Override
    public List<SizeResponse> getAllSizeById(int idSize) {
        Optional<Size> sizeOptional = sizeRepository.findById(idSize);
        List<SizeResponse> sizeResponses = new ArrayList<>();
        if (sizeOptional.isPresent()) {
            Size size = sizeOptional.get();
            SizeResponse sizeResponse = new SizeResponse();
            sizeResponse.setName(size.getName());
            sizeResponses.add(sizeResponse);
        }
        return sizeResponses;
    }
}
