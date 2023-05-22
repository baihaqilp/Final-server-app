package id.co.metrodata.serverApp.services;

import id.co.metrodata.serverApp.models.SegmentMateri;
import id.co.metrodata.serverApp.models.dto.request.SegmentMateriRequest;
import id.co.metrodata.serverApp.repositories.SegmentMateriRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class SegmentMateriService {
    private SegmentMateriRepository segmentMateriRepository;
    private ModelMapper modelMapper;
    private SegmentService segmentService;
    private MateriService materiService;
    public List<SegmentMateri> getAll() {
        return segmentMateriRepository.findAll();
    }

    public SegmentMateri getById(Long id) {
        return segmentMateriRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SegmentMateri not Found!"));
    }

    public SegmentMateri create(SegmentMateriRequest segmentMateriRequest) {
        if (segmentMateriRepository.existsBySegment_Id(segmentMateriRequest.getSegmentId())) {
            for (SegmentMateri segmentMateriCheck : segmentMateriRepository.findAllBySegment_Id(segmentMateriRequest.getSegmentId())) {
                if (Objects.equals(segmentMateriCheck.getMateri().getId(), segmentMateriRequest.getMateriId())) {
                    throw new ResponseStatusException(
                            HttpStatus.CONFLICT,
                            "The materi has been added to the segment!"
                    );
                }
            }
        }
        SegmentMateri segmentMateri = modelMapper.map(segmentMateriRequest, SegmentMateri.class);
        segmentMateri.setSegment(segmentService.getById(segmentMateriRequest.getSegmentId()));
        segmentMateri.setMateri(materiService.getById(segmentMateriRequest.getMateriId()));
        return segmentMateriRepository.save(segmentMateri);
    }

    public SegmentMateri update(Long id, SegmentMateriRequest segmentMateriRequest) {
        SegmentMateri segmentMateriOld = getById(id);
        if (!Objects.equals(segmentMateriOld.getSegment().getId(), segmentMateriRequest.getSegmentId()) && !Objects.equals(segmentMateriOld.getMateri().getId(), segmentMateriRequest.getMateriId())) {
            if (segmentMateriRepository.existsBySegment_Id(segmentMateriRequest.getSegmentId())) {
                for (SegmentMateri segmentMateriCheck : segmentMateriRepository.findAllBySegment_Id(segmentMateriRequest.getSegmentId())) {
                    if (Objects.equals(segmentMateriCheck.getMateri().getId(), segmentMateriRequest.getMateriId())) {
                        throw new ResponseStatusException(
                                HttpStatus.CONFLICT,
                                "The materi has been added to the segment!"
                        );
                    }
                }
            }
        }
        SegmentMateri segmentMateri = modelMapper.map(segmentMateriRequest, SegmentMateri.class);
        segmentMateri.setSegment(segmentService.getById(segmentMateriRequest.getSegmentId()));
        segmentMateri.setMateri(materiService.getById(segmentMateriRequest.getMateriId()));
        segmentMateri.setId(id);
        return segmentMateriRepository.save(segmentMateri);
    }

    public SegmentMateri delete(Long id) {
        SegmentMateri segmentMateri = getById(id);
        segmentMateriRepository.delete(segmentMateri);
        return segmentMateri;
    }
}
