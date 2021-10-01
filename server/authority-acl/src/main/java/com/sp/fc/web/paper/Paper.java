package com.sp.fc.web.paper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Paper {

    @Id @GeneratedValue
    private Long id;

    private String title;
    private String tutorId;
//    private List<String> studentIds;
    private State state;

    public static enum State {
        PREPARE,    // 출제중
        READY,      // 시험시작
        END         // 시험종료
    }
}
