/*
//package com.boxi.core;

//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.verify;
//
//
//import com.boxi.hub.entity.Hub;
//import com.boxi.hub.enums.HubType;
//import com.boxi.hub.payload.dto.HubDto;
//import com.boxi.hub.repo.HubRepository;
//import com.boxi.hub.service.impl.HubServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;


//@ExtendWith(MockitoExtension.class)
//public class HubTest
//{

//    @InjectMocks
//    HubServiceImpl service;
//
//    @Mock
//    HubRepository hubRepository;
//
//    Hub tehran, zanjan,abhar,qazvin;
//
//
//    @BeforeEach
//    public void setup(){
//
//        tehran =new Hub();
//        tehran.setCode("A100");
//        tehran.setName("Tehran");
//        tehran.setIsActive(true);
//        tehran.setIsDeleted(false);
//        tehran.setType(HubType.MAIN_HUB);
//        tehran.setId(1L);
//
//        zanjan =new Hub();
//        zanjan.setCode("A100B3");
//        zanjan.setName("zanjan");
//        zanjan.setIsActive(true);
//        zanjan.setParentHub(tehran);
//        zanjan.setIsDeleted(false);
//        zanjan.setType(HubType.BRANCH);
//        zanjan.setId(2L);
//
//        qazvin =new Hub();
//        qazvin.setCode("A100B3");
//        qazvin.setParentHub(tehran);
//        qazvin.setName("qazvin");
//        qazvin.setIsActive(true);
//        qazvin.setIsDeleted(false);
//        qazvin.setType(HubType.BRANCH);
//        qazvin.setId(3L);
//
//
//        abhar =new Hub();
//        abhar.setCode("A100B3C2");
//        abhar.setParentHub(zanjan);
//        abhar.setName("abhar");
//        abhar.setIsActive(true);
//        abhar.setIsDeleted(false);
//        abhar.setType(HubType.BRANCH);
//        abhar.setId(4L);
//
//
//        tehran.setHubs(List.of(zanjan,abhar));
//        zanjan.setHubs(List.of(abhar));
//
//
//
//    }


//    @Test
//    @DisplayName("gen hub code when there is no parent exist")
//    void tehran() {
//      //  given(hubRepository.findTopByOrderByIdDesc()).willReturn(hub);
//        given(hubRepository.count()).willReturn(0L);
//        // then - verify the output
//        String code=service.genCode(null);
//        System.out.println(code);
//        assertThat(code).isEqualTo(tehran.getCode());
//    }
//
//    @Test
//    @DisplayName("zanjan has  one child")
//    void zanjan() {
//        given(hubRepository.findTopByCode(zanjan.getParentHub().getCode())).willReturn(tehran); //set father
//        given(hubRepository.count()).willReturn(3L); //all hub counts
//        assertThat(tehran).isNotNull();
//        // then - verify the output
//        String code=service.genCode(zanjan.getParentHub().getCode());
//        System.out.println(code);
//     //   assertThat(code).isEqualTo(zanjan.getCode());
//    }
//
//    @Test
//    @DisplayName("qazvin has not child")
//    void qazvin() {
//        given(hubRepository.findTopByCode(qazvin.getParentHub().getCode())).willReturn(tehran);
//        given(hubRepository.count()).willReturn(3L); //all hub counts
//        assertThat(tehran).isNotNull();
//        // then - verify the output
//        String code=service.genCode(qazvin.getParentHub().getCode());
//        System.out.println(code);
//        assertThat(code).isEqualTo(qazvin.getCode());
//    }
//
//    @Test
//    @DisplayName("abhar is a child")
//    void abhar() {
//        given(hubRepository.findTopByCode(abhar.getParentHub().getCode())).willReturn(zanjan);
//        given(hubRepository.count()).willReturn(3L); //all hub counts
//        assertThat(zanjan).isNotNull();
//        // then - verify the output
//        String code=service.genCode(abhar.getParentHub().getCode());
//        System.out.println(code);
//        assertThat(code).isEqualTo(abhar.getCode());
//    }


//}
*/
