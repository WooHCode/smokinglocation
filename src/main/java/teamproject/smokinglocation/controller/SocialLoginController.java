package teamproject.smokinglocation.controller;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import teamproject.smokinglocation.dto.socialDto.SocialLoginDto;
import teamproject.smokinglocation.service.SocialService;
import teamproject.smokinglocation.userEnitiy.Member;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SocialLoginController {
	private final SocialService socialService;
	private final Environment env;

    /**
     * 소셜 로그인 URL 공통 호출 함수
     * @param provider
     * @return socialLoginDto, HttpStatus.OK
     * @throws Exception 
     */
    @PostMapping("/getSocialAuthUrl")
    public ResponseEntity<?> kakaoLoginJson(@RequestParam("provider") String provider) throws Exception {
    	
        SocialLoginDto socialLoginDto = new SocialLoginDto();
        // 제공자에 따라 분기처리
        // TODO VIEW 호출 하는 함수가 제공자에 따라 나눠져 있어 하나의 함수에서 호출할수 있도록 수정 필요할 것으로 보임
        if(provider.equals("kakao")) {
            socialLoginDto.setClientId(env.getProperty("kakao.client.id"));
            socialLoginDto.setRedirectUrl(env.getProperty("kakao.redirect.url"));
        } else if(provider.equals("naver")) {
            socialLoginDto.setClientId(env.getProperty("Naver.client.id"));
            socialLoginDto.setRedirectUrl(env.getProperty("Naver.redirect.url"));
        } else if(provider.equals("google")) {
            socialLoginDto.setClientId(env.getProperty("Google.client.id"));
            socialLoginDto.setRedirectUrl(env.getProperty("Google.redirect.url"));
        }
        return new ResponseEntity<>(socialLoginDto, HttpStatus.OK);
    }
    
    /**
     * 카카오 소셜 로그인 호출
     * accessToken, refreshToken 발급
     * @param dto
     * @return
     * @throws Exception 
     */
    @GetMapping("/kakaoLogin")
    public String kakaoLogin(@RequestParam("code") String code, RedirectAttributes redirectAttributes, Model model) throws Exception {
    	log.info("===========kakaoLogin process start===========");
    	Member kakaoInfo = socialService.kakaoLogin(code);	// 토큰 발급 후 개인정보 조회
    	if(kakaoInfo != null) {
    		// 회원가입
    		socialService.registerSocialUser(kakaoInfo);
    	}

    	// 로컬 스토리지,세션에 저장할 accessToken,refreshToken
    	model.addAttribute("accessToken", kakaoInfo.getAccessToken());
    	model.addAttribute("refreshToken", kakaoInfo.getRefreshToken());
    	log.info("===========kakaoLogin process end===========");
    	return "redirect:/map";
    }

    /**
     * 네이버 소셜 로그인 호출
     * accessToken, refreshToken 발급
     * @param model
     * @return Success
     * @throws Exception 
     */
    @GetMapping("/naverLogin")
    public String naverLogin(@RequestParam("code") String code, @RequestParam("state") String state, RedirectAttributes redirectAttributes, Model model) throws Exception {
    	
    	log.info("===========naverLogin process start===========");
    	Member naverInfo = socialService.naverLogin(code,state);	// 토큰 발급 후 개인정보 조회

    	log.info("naverInfo = {}", naverInfo);

    	if(naverInfo != null) {
    		// 회원가입
    		socialService.registerSocialUser(naverInfo);
    	}

    	// 로컬 스토리지,세션에 저장할 accessToken,refreshToken
    	model.addAttribute("accessToken", naverInfo.getAccessToken());
    	model.addAttribute("refreshToken", naverInfo.getRefreshToken());    
    	log.info("===========naverLogin process end===========");
    	
    	return "redirect:/map";
    }
    
   /**
    * 구글 소셜 로그인 호출
    * accessToken, refreshToken 발급
    * @param model
    * @return Success
    * @throws Exception 
    */
   @GetMapping("/googleLogin")
   public String googleLogin(@RequestParam("code") String code, Model model) throws Exception {
   	
   	log.info("===========googleLogin process start===========");
   	Member googleLogin = socialService.googleLogin(code);	// 토큰 발급 후 개인정보 조회
   	if(googleLogin != null) {
   		// 회원가입
   		socialService.registerSocialUser(googleLogin);
   	}

    // 로컬 스토리지,세션에 저장할 accessToken,refreshToken
   	model.addAttribute("accessToken", googleLogin.getAccessToken());
   	model.addAttribute("refreshToken", googleLogin.getRefreshToken());    
   	log.info("googleLogin = {}", googleLogin);
   	log.info("===========googleLogin process end===========");
   	
   	return "redirect:/map";
   }
}
