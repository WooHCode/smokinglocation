package teamproject.smokinglocation.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamproject.smokinglocation.inquiryentity.Inquiry;
import teamproject.smokinglocation.dto.inquiryDto.InquiryDto;
import teamproject.smokinglocation.repository.InquiryRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class InquiryService {

    private final InquiryRepository inquiryRepository;
    private final MemberService memberService;

    @Transactional
    public Inquiry saveInquiry(InquiryDto dto) {
        Inquiry inquiry = new Inquiry();
        inquiry.createInquiry(memberService.findById(1L),dto.getTitle(),dto.getContent());
        return inquiryRepository.save(inquiry);
    }

    @Transactional
    public void update(Long id, InquiryDto dto) {
        Inquiry inquiry = inquiryRepository.findById(id).orElseThrow();
        inquiry.update(dto.getTitle(), dto.getContent());
    }

    public List<Inquiry> findAll() {
        log.info("====================InquiryService.findAll");
        return inquiryRepository.findAll();
    }

    public List<Inquiry> findAllByMemberId(Long memberId) {
        log.info("====================InquiryService.findAllByMemberId");
        return inquiryRepository.findAllByMemberId(memberId);
    }

    public Inquiry findOne(Long id) {
        log.info("====================InquiryService.findOne");
        return inquiryRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Inquiry addReply(Long id,String reply) {
        log.info("===============reply : {}", reply);
        Inquiry inquiry = inquiryRepository.findById(id).orElseThrow();
        inquiry.addReply(reply);
        return inquiry;
    }

    public InquiryDto entityToDto(Inquiry entity) {
        InquiryDto inquiryDto = new InquiryDto();
        inquiryDto.setId(entity.getId());
        inquiryDto.setTitle(entity.getTitle());
        inquiryDto.setContent(entity.getContent());
        inquiryDto.setReply(entity.getReply());

        return inquiryDto;
    }

    @Transactional
    public void deleteInquiry(Long id) {
        inquiryRepository.delete(inquiryRepository.findById(id).orElseThrow());
    }



}