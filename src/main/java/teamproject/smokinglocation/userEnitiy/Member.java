package teamproject.smokinglocation.userEnitiy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import teamproject.smokinglocation.inquiryentity.Inquiry;

@Entity
@AllArgsConstructor
@Getter
public class Member extends BaseTime implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String memberId;

    @Column(nullable = false)
    private String memberName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String provider;

    private String refreshToken;
    
    private String OAuthToken;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Inquiry> inquiries = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<SavedSpot> savedSpotList = new ArrayList<>();

    public Member() {

    }

    @Builder
    public void toEntity(String memberId, String memberName, String password, List<String> roles) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.password = password;
        this.roles = roles;
    }
    
    // 소셜 로그인 개인정보 저장을 위해 추가
    @Builder
    public void SocialRegisterEntity(String memberId, String password, String memberName, String OAuthToken, String provider, List<String> roles) {
        this.memberId = memberId;
        this.password = password;
        this.memberName = memberName;
        this.OAuthToken = OAuthToken;
        this.provider = provider;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return memberId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
    public void updatePassword(String password) {
        this.password = password;
    }
}
