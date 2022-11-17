package Live.Commerce.controller;

import Live.Commerce.domain.Member;
import Live.Commerce.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm.html";
    }

    @PostMapping("/members/new")
    public String SaveForm(Member member) {
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String memberList(Model model){
        List<Member> memberList = memberService.findAll();
        model.addAttribute("members", memberList);
        return "members/memberList";
    }

}