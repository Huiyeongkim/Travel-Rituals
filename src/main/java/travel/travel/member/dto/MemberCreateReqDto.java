package travel.travel.member.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import travel.travel.member.domain.Member;
import travel.travel.member.domain.Role;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberCreateReqDto {
    private String name;
    @NotEmpty(message = "email은 필수값입니다.")
    private String email;
    @NotEmpty(message = "password는 필수값입니다.")
    @Size(min = 8, message = "password의 최소 길이는 8입니다.")
    private String password;
    private Role role = Role.USER;

    public Member toEntity(String password) {
        return Member.builder()
                .name(this.name)
                .email(this.email)
                .password(password)
                .role(this.role)
                .delYn("N")
                .build();
    }
}
/*.orderList(new ArrayList<>())*/