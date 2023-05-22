package teamproject.smokinglocation.controller;

import lombok.Getter;

@Getter
public enum UuidData {
    YONGSAN("17fbd06c-45bb-48aa-9be7-b26dbc708c9c"),
    SEONGBUK("d0bd14d5-4a72-4926-9c7d-d19b9440162f"),
    JUNGNANG("4250aae2-a50a-4a5d-8d70-9ec4c55a92f3"),
    SEODAEMUN("280fb8c7-7bd8-4633-896e-99a76d23d2de"),
    SONGPA("abd3c39d-796a-4433-b298-e1f1cb4e2540"),
    GANGSEO("92996e84-3919-4fc6-a751-a57aaf48f0f3"),
    JUNGGU("87a3b7f3-fa03-4345-8001-b0fb950a1ab1"),
    YEONGDEUNGPO("2653cc01-60d7-4e8b-81f4-80b24a39d8f6"),
    GWANGJIN("d494c578-f45e-4c42-9dde-c277cbd8717a"),
    YANGCHEON("c88f8999-4362-4eb0-8a69-b8c4dc7e243a"),
    DONGJAK("03e47093-48b5-442c-a6a5-bd756148f6ae"),
    GWANAK("3d363ba6-3fcf-49af-8689-92b2a77d1c69"),
    DONGDAEMUN("aef69bb4-d848-4088-9abd-f6e3dd361cfb"),
    GURO("6e9ed9bd-b5ad-4188-9bbe-f9516113ddf4"),
    GANGBUK("0d7a603a-608e-481a-8ff0-a4cd23d7c449"),
    SEONGDONG("68c14d9d-6a3c-4cd6-9199-959ef803e3f3_201908021643"),
    SEOCHO("16735cd3-6305-4539-b0b4-e92ad6653ec7"),
    NOWON("21993e35-919a-4241-9220-a4828c5d9d96"),
    GANGNAM("06daef13-9cbe-463d-b7b4-b80b1b3ab815"),
    JONGNO("8d03feef-5b39-435d-b423-9f9842bfdce3");

    private final String uuid;

    UuidData(String uuid) {
        this.uuid = uuid;
    }
}
