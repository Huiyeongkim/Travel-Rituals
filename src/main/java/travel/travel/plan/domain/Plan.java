package travel.travel.plan.domain;

import lombok.*;
import travel.travel.common.domain.BaseEntity;
import travel.travel.member.domain.Member;
import travel.travel.plan.dto.PlanResDto;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id")
    private Destination destination;

    public PlanResDto fromEntity() {
        return PlanResDto.builder()
                .planId(this.planId)
                .title(this.title)
                .content(this.content)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .destinationId(destination.getDestinationId())
                .destinationName(destination.getDestinationName())
                .build();
    }

    public void updatePlan(Plan plan) {
        this.title = plan.getTitle();
        this.content = plan.getContent();
        this.startDate = plan.getStartDate();
        this.endDate = plan.getEndDate();
        this.scheduleOrder = plan.getScheduleOrder();
    }
}
