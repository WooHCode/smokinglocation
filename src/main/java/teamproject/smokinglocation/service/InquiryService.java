package teamproject.smokinglocation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamproject.smokinglocation.inquiryentity.Inquiry;
import teamproject.smokinglocation.dto.inquiryDto.InquiryDto;
import teamproject.smokinglocation.repository.InquiryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InquiryService {

    private final InquiryRepository inquiryRepository;
    private final MemberService memberService;

    @Transactional
    public Inquiry saveInquiry(InquiryDto dto) {
        Inquiry inquiry = new Inquiry();
        inquiry.createInquiry(memberService.findById(1L),dto.getTitle(),dto.getContent());
        return inquiryRepository.save(inquiry);
    }

    public List<Inquiry> findAll() {
        return inquiryRepository.findAll();
    }


    public Inquiry findOne(Long id) {
        return inquiryRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Inquiry addReply(Long id,String reply) {
        Inquiry inquiry = inquiryRepository.findById(id).orElseThrow();
        inquiry.addReply(reply);
        return inquiry;
    }





    public List<Inquiry> getAllMine(Long memberId) {
        return inquiryRepository.findAllByMemberId(memberId);
    }
}
