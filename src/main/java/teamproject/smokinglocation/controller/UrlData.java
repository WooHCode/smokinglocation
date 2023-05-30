package teamproject.smokinglocation.controller;

import lombok.Getter;

@Getter
public enum UrlData {
    YONGSAN(15073796),
    SEONGBUK(15103853),
    JUNGNANG(15040636),
    SEODAEMUN(15040413),
    SONGPA(15067591),
    GANGSEO(15068987),
    JUNGGU(15080296),
    YEONGDEUNGPO(15069051),
    GWANGJIN(15040615),
    YANGCHEON(15040511),
    DONGJAK(15049031),
    GWANAK(15040591),
    DONGDAEMUN(15070168),
    GURO(15069274),
    GANGBUK(15049030),
    SEONGDONG(15029169),
    SEOCHO(15074379),
    NOWON(15078097),
    GANGNAM(15103349),
    JONGNO(15102250);

    private final int number;

    UrlData(int number) {
        this.number = number;
    }
}
