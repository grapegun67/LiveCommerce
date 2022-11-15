package Live.Commerce.service;

import Live.Commerce.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberTest {

    @Autowired private MemberService memberService;
    @Autowired private EntityManager em;

    @Test
    public void memberSaveTest() {
        //given
        Member member = new Member();
        member.setName("hello");
        member.setAge("33");
        member.setSex("f");

        Long memberId = memberService.join(member);

        //when
        Member findUser = memberService.findOne(memberId);

        //then
        Assertions.assertThat(memberId).isEqualTo(findUser.getId());
    }

}
