package allliveyoung.allliveinbound.service;

import allliveyoung.allliveinbound.domain.InboundRequest;
import allliveyoung.allliveinbound.web.dto.InboundRequestUpdateDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class InboundRequestServiceImpl implements InboundRequestService {
    @Override
    public List<InboundRequest> findInbounds() {
        return null;
    }

    @Override
    public InboundRequest findInbound(Long id) {
        return null;
    }

    @Override
    public void updateInbound(InboundRequestUpdateDTO inboundRequestUpdateDTO) {

    }

    @Override
    public void deleteInbound(Long id) {

    }

    @Override
    public void updateInboundStatus(Long id, String status) {

    }
}
