package com.boxi.personalizationform.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.util.*;
import javax.persistence.*;

@Entity(name="PersonalizationForm")
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@Table(name="TBL_PERSONALIZATIONFORM")
public class PersonalizationForm implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", nullable=false, insertable=true, updatable=true)
    private Long id;

    @Basic(optional=false)
    @Column(name="USERID", nullable=false, insertable=true, updatable=true)
    private Long userId;

    @Basic(optional=false)
    @Column(name="PERMISSIONID", nullable=false, insertable=true, updatable=true)
    private Long permissionId;

    @Basic(optional=true)
    @Column(name="FILTERPERSONALIZE", insertable=true, updatable=true, length=254)
    private String filterPersonalize;

    @Basic(optional=true)
    @Column(name="OPERATIONPERSONALIZE", insertable=true, updatable=true, length=254)
    private String operationPersonalize;

    @Basic(optional=true)
    @Column(name="LISTPERSONALIZE", insertable=true, updatable=true, length=254)
    private String listPersonalize;

    public PersonalizationForm() {
        // TODO Add your own initialization code here.
    }

}
