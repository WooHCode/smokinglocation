package teamproject.smokinglocation.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter @Setter
public class Notifications implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date timestamp;

    private String nodeId;

    private Long dossierId;

    private String payload;




}
