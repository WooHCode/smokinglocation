package teamproject.smokinglocation.service;

import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import teamproject.smokinglocation.repository.MemberRepository;
import teamproject.smokinglocation.userEnitiy.Member;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SocialService {
	
	private final Environment env;
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member kakaoLogin(String code) throws Exception{
    	String accessToken = "";
    	String refreshToken = "";
    	try {
    		log.info("===========kakaoLogin process start===========");
            HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-type", "application/x-www-form-urlencoded");

	        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	        params.add("grant_type"   , "authorization_code");
	        params.add("client_id"    , env.getProperty("kakao.client.id"));
	        params.add("client_secret", env.getProperty("kakao.secret_id"));
	        params.add("code"         , code);
	        params.add("redirect_uri" , env.getProperty("kakao.redirect.url"));

	        //HttpHeader 담기
	        RestTemplate restTemplate = new RestTemplate();
	        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);

	        ResponseEntity<String> response = restTemplate.exchange(
	        		env.getProperty("kakao.auth.url") + "/oauth/token",
	                HttpMethod.POST,
	                httpEntity,
	                String.class
	        );
	        //Response 데이터 파싱
	        JSONObject jsonObj = new JSONObject(response.getBody());

            accessToken  = (String) jsonObj.get("access_token");
            
            log.info("accessToken : " + accessToken);
            log.info("===========kakaoLogin process end===========");
        } catch (Exception e) {
            throw new Exception("API call failed");
        }

        return getUserInfoWithToken(accessToken);
    }
    
    @Transactional
    private Member getUserInfoWithToken(String accessToken) throws Exception {
        //HttpHeader 생성
    	log.info("===========kakaoLogin process start===========");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //HttpHeader 담기
        RestTemplate rt = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = rt.exchange(
                env.getProperty("kakao.api.url") + "/v2/user/me",
                HttpMethod.POST,
                httpEntity,
                String.class
        );

        //Response 데이터 파싱
        JSONObject jsonObj    = new JSONObject(response.getBody()); 
        JSONObject account = (JSONObject) jsonObj.get("kakao_account");
        JSONObject profile = (JSONObject) account.get("profile");
        
        Member member = new Member();
        String id = String.valueOf(jsonObj.get("id"));
        String email = String.valueOf(account.get("email"));
        String name = String.valueOf(profile.get("nickname"));
        String provider = "kakao";
        String password = id;
        List<String> roles = Collections.singletonList("USER"); // 사용자 역할 설정
        
        member.SocialRegisterEntity(email, password, name, accessToken, provider, roles);
        log.info("id : " + id);
        log.info("email : " + member.getMemberId());
        log.info("name : " + member.getMemberName());
        log.info("provider : " + member.getProvider());
        log.info("password : " + password);
        log.info("accessToken : " + accessToken);
        log.info("===========kakaoLogin process end===========");
        return member;
    }
    
    @Transactional
    public Member naverLogin(String code, String state) throws Exception{
    	String accessToken = "";
    	String refreshToken = "";
    	try {
    		log.info("===========kakaoLogin process start===========");
            HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

	        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	        params.add("grant_type"   , "authorization_code");
	        params.add("client_id"    , env.getProperty("Naver.client.id"));
	        params.add("client_secret", env.getProperty("Naver.secret_id"));
	        params.add("code"         , code);
	        params.add("state", URLEncoder.encode(state, "UTF-8"));
	        //params.add("redirect_uri" , env.getProperty("Naver.redirect.url"));

	        RestTemplate restTemplate = new RestTemplate();
	        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);

	        ResponseEntity<String> response = restTemplate.exchange(
	        		env.getProperty("Naver.auth.url")+ "/oauth2.0/token",
	                HttpMethod.POST,
	                httpEntity,
	                String.class
	        );
	        JSONObject jsonObj = new JSONObject(response.getBody());

            accessToken  = (String) jsonObj.get("access_token");
            
            log.info("accessToken : " + accessToken);
            log.info("===========NaverLogin process end===========");
        } catch (Exception e) {
            throw new Exception("API call failed");
        }

        return getNaverUserInfoWithToken(accessToken);
    }
    
    @Transactional
    private Member getNaverUserInfoWithToken(String accessToken) throws Exception {
        //HttpHeader 생성
    	log.info("===========NaverLogin process start===========");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer " + accessToken);
        //headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //HttpHeader 담기
        RestTemplate rt = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = rt.exchange(
                env.getProperty("Naver.api.url")+"/v1/nid/me",
                HttpMethod.POST,
                httpEntity,
                String.class
        );
        //Response 데이터 파싱
        JSONObject jsonObj    = new JSONObject(response.getBody()); 
        JSONObject account = (JSONObject) jsonObj.get("response");

        Member member = new Member();
        String id = String.valueOf(account.get("id"));				// ID 고유의 값
        String name = String.valueOf(account.get("name"));			// 성명
        String nickname = String.valueOf(account.get("nickname"));	// 
        String email = String.valueOf(account.get("email"));		// 이메일
        String provider = "naver";									// 제공자
        String password = id;				// 비밀번호(암호화)
        List<String> roles = Collections.singletonList("USER"); // 사용자 역할 설정
        
        member.SocialRegisterEntity(email, password, name, accessToken, "naver", roles);
        log.info("email : " + member.getMemberId());
        log.info("name : " + member.getMemberName());
        log.info("provider : " + member.getProvider());
        log.info("password : " + password);
        log.info("accessToken : " + accessToken);
        log.info("===========NaverLogin process end===========");
        return member;
    }
    
    @Transactional
    public Member googleLogin(String code) throws Exception{
    	String accessToken = "";
    	String refreshToken = "";
    	try {
    		log.info("===========googleLogin process start===========");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

	        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	        params.add("grant_type"   , "authorization_code");
	        params.add("client_id"    , env.getProperty("Google.client.id"));
	        params.add("client_secret", env.getProperty("Google.secret_id"));
	        params.add("code"         , code);
	        params.add("redirect_uri" , env.getProperty("Google.redirect.url"));

	        RestTemplate restTemplate = new RestTemplate();
	        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);

 	        ResponseEntity<String> response = restTemplate.exchange(
	        		env.getProperty("Google.auth.url")+"/token",
	                HttpMethod.POST,
	                httpEntity,
	                String.class
	        );
	        JSONObject jsonObj = new JSONObject(response.getBody());
            accessToken  = (String) jsonObj.get("access_token");
            //refreshToken = (String) jsonObj.get("id_token");
            log.info("accessToken : " + accessToken);
            log.info("===========googleLogin process end===========");
        } catch (Exception e) {
            throw new Exception("API call failed");
        }

        return getGoogleUserInfoWithToken(accessToken);
    }
    
    @Transactional
    private Member getGoogleUserInfoWithToken(String accessToken) throws Exception {
    	String name = "";
    	String email = "";
    	String id = "";
        //HttpHeader 생성
    	log.info("===========NaverLogin process start===========");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer " + accessToken);

        //HttpHeader 담기
        RestTemplate rt = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = rt.exchange(
                env.getProperty("Google.api.url")+"/drive/v2/files",
                //env.getProperty("Google.api.url")+"/oauth2/v2/userinfo",
                HttpMethod.GET,
                httpEntity,
                String.class
        );

        //Response 데이터 파싱
        JSONObject jsonObj = new JSONObject(response.getBody());
        JSONArray items = jsonObj.getJSONArray("items"); // items 배열 추출
        log.info("jsonObj : " + response.getBody());

        // Google API 호출 가이드 참고
        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i); // 각 item 객체 추출
            JSONArray ownersArray = item.getJSONArray("owners");
            // owners 배열 내의 displayName 추출
            for (int j = 0; j < ownersArray.length(); j++) {
                JSONObject ownerObject = ownersArray.getJSONObject(i);
                
                name = ownerObject.getString("displayName");	// 이름
                email = ownerObject.getString("emailAddress");	// 이메일
                id = ownerObject.getString("permissionId");	// 고유 아이디
            }
        }
        
        
        Member member = new Member();
        String password = id;							// 비밀번호(암호화)
        List<String> roles = Collections.singletonList("USER"); // 사용자 역할 설정
        
        member.SocialRegisterEntity(email, password, name, accessToken, "google", roles);
        log.info("email : " + member.getMemberId());
        log.info("id : " + name);
        log.info("name : " + member.getMemberName());
        log.info("password : " + member.getPassword());
        log.info("accessToken : " + accessToken);
        log.info("===========NaverLogin process end===========");
        return member;
    }
    
    // 소셜 로그인 공통 회원가입
    @Transactional
	public void registerSocialUser(Member member) throws Exception {
    	// 회원정보 있는지 체크
    	int dup_check = memberRepository.findMemberEmailCountByMemberId(member.getMemberId());

    	if(dup_check == 0) {
    		// 소셜 로그인 회원가입
    		memberRepository.save(member);
    	}
    }
}