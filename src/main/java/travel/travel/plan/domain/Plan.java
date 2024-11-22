package travel.travel.plan.domain;

import lombok.*;
import travel.travel.common.domain.BaseEntity;
import travel.travel.member.domain.Member;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name= "plan")
public class Plan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_Id")
    private Long planId;

    @Column(length = 100)
    private String title;

    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDate startDate;
    private LocalDate endDate;

    private Long scheduleOrder;
    private Long destinationId;


    /*Place place;*/

}
